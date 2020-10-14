import { MediaMatcher } from '@angular/cdk/layout';
import { Component, Inject, OnInit, ViewChild, ChangeDetectorRef } from '@angular/core';
import {MatDialog, MatSort, MatTableDataSource} from '@angular/material';
import {AdminAuthorization} from '../../../model/admin-authorization.model';
import {AdminAuthorizationService} from '../../../services/admin-authorization.service';
import {ActivatedRoute, Router} from '@angular/router';
import {HttpErrorResponse} from '@angular/common/http';
import { Observable, throwError } from 'rxjs';
import {DeleteDialogComponent} from '../delete-dialog/delete-dialog.component';
import {UsersService} from '../../../services/users.service';
import {User} from '../../../model/user.model';

@Component({
  selector: 'app-admin-authorization',
  templateUrl: './admin-authorization.component.html',
  styleUrls: ['./admin-authorization.component.css']
})
export class AdminAuthorizationComponent implements OnInit {

  private authorizations: AdminAuthorization[] = [];
  private user: User;

  filteredAuthorizations: AdminAuthorization[];

  displayedColumns: string[] = ['municipality', 'isp', 'expire_date', 'actions'];
  dataSource: MatTableDataSource<AdminAuthorization>;

  private sub: any;
  private userID: string;
  private deleteID: string;
  private confirm: boolean = false;

  mobileQuery: MediaQueryList;
  canRender: boolean;
  isMobile: boolean;
  private _mobileQueryListener: () => void;

  @ViewChild(MatSort, {static: false}) set content(sort: MatSort) {
    if (this.canRender) {
      this.dataSource.sort = sort;
    }
  }

  constructor(
    @Inject(AdminAuthorizationService) private _adminAuthService: AdminAuthorizationService,
    @Inject(UsersService) private _userService: UsersService,
    private router: Router,
    private route: ActivatedRoute,
    private dialog: MatDialog,
    changeDetectorRef: ChangeDetectorRef,
    media: MediaMatcher
    ) {
      this.mobileQuery = media.matchMedia('(max-width: 481px)');
      this._mobileQueryListener = () => changeDetectorRef.detectChanges();
      this.mobileQuery.addEventListener('change', this._mobileQueryListener);
    }

  ngOnInit() {
    this.checkSize(window.innerWidth);
    this.canRender = false;
    this.sub = this.route.params.subscribe(params => {
      this.userID = params['id'];
      this.getUser();
    });
  }

  applyFilter(filterValue: string) {
    this.filteredAuthorizations = this.authorizations;
    filterValue = filterValue.trim().toLowerCase();
    if (filterValue) {
      if (!this.isMobile) {
        this.dataSource.filter = filterValue;
      } else {
        this.filteredAuthorizations = this.authorizations.filter( authorization => {
          return authorization.municipalityName.trim().toLowerCase().includes(filterValue) ||
                  authorization.isp.name.trim().toLowerCase().includes(filterValue) ||
                  authorization.expire_date.toString().trim().toLowerCase().includes(filterValue);
        });
      }
    }
  }

  //Open dialog for deleting element
  openDialog(id,name): void {
    this.deleteID = id;
    const dialogRef = this.dialog.open(DeleteDialogComponent, {
      width: '300px',
      data: {
        id: id,
        name: name
      }
    });

    dialogRef.afterClosed().subscribe(result => {
      this.confirm = result;
      if (this.confirm === true) {
        this.deleteAdminAuthorization(this.deleteID);
      }
    });
  }

  //GET Request for retrieve user
  protected getUser(): void {
    this._userService.getUserByID(this.userID).subscribe(data => {
        this.user = data;
        this.getAdminAuthorizationByUserID();
      },
    );
  }
  //GET Request for authorization of selected ADMIN
  protected getAdminAuthorizationByUserID(): void {
    this._adminAuthService.getAdminAuthorizationsByUserID(this.userID).subscribe( data => {
        this.authorizations = data;
        this.filteredAuthorizations = data;
        this.formatDate();
        this.addMunicipalityNameField();
        this.dataSource = new MatTableDataSource(this.authorizations);
        this.canRender = true;
      },
      err => {
        if (err instanceof HttpErrorResponse) {
          if (err.status === 401) {
            this.router.navigate(['/main/home']);
          }
          if (err.status === 403) {
            this.router.navigate(['/main/home']);
          }
        }
      },
    );

  }

  //CHANGE format of date field
  protected formatDate(): void {
    for (var i=0; i<this.authorizations.length; i++){
      if (this.authorizations[i].expire_date != null) {
        const app = new Date(this.authorizations[i].expire_date);
        const app2 = app.getMonth() + 1;
        const data =  app.getDate() + '/' + app2 + '/' + app.getFullYear();
        this.authorizations[i].expire_date = data;
      } else {
        this.authorizations[i].expire_date = 'No time limit';
      }
    }
  }

  //ADD a field municipalityName for helping filtering
  private addMunicipalityNameField(): void {
    for (var i=0; i<this.authorizations.length; i++) {
      this.authorizations[i].municipalityName = this.authorizations[i].municipality.name;
    }
  }

  //DELETE Request for delete an authorization
  protected deleteAdminAuthorization(adminAuthId): void {
      this._adminAuthService.deleteAdminAuthorization(adminAuthId).subscribe(
        data => {
          // refresh the list
          this.authorizations = [];
          this.getAdminAuthorizationByUserID();
        },
        error => {
          console.error("Error deleting admin authorization!");
          return throwError(error);
        }
      );
  }

  //Go to the previous page
  private goBack(): void {
    this.router.navigate(['main/users']);
  }

  public onResize(event) {
    this.checkSize(event.target.innerWidth);
  }

  private checkSize(size: number): void {
    if (size < 481) {
      this.isMobile = true;
    } else {
      this.isMobile = false;
    }
  }
}
