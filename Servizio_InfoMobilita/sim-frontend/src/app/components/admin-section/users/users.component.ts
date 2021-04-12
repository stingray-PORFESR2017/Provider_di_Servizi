import { Component, Inject, OnInit, ViewChild, ChangeDetectorRef } from '@angular/core';
import {HttpErrorResponse} from '@angular/common/http';
import { MediaMatcher } from '@angular/cdk/layout';
import {Router} from '@angular/router';

import {throwError } from 'rxjs';

import {MatDialog} from '@angular/material';
import { MatSort } from '@angular/material/sort';
import { MatTableDataSource } from '@angular/material/table';

import {DeleteDialogComponent} from '../delete-dialog/delete-dialog.component';
import {JwtStorageService} from '../../../services/jwt-storage.service';
import {TitleService} from "../../../services/title.service";
import { User } from './../../../model/user.model';
import { UsersService } from '../../../services/users.service';

@Component({
  selector: 'app-users',
  templateUrl: './users.component.html',
  styleUrls: ['./users.component.css']
})
export class UsersComponent implements OnInit {

  users: User[];

  displayedColumns: string[] = ['name', 'surname', 'email', 'userRole', 'actions'];
  dataSource: MatTableDataSource<User>;

  filteredUsers: User[];

  mobileQuery: MediaQueryList;
  canRender: boolean;
  isMobile: boolean;
  private _mobileQueryListener: () => void;


  private deleteID: string;
  private confirm: boolean = false;

  @ViewChild(MatSort, {static: false}) set content (sort: MatSort) {
    if (this.canRender) {
      this.dataSource.sort = sort;
    }
  }

  constructor(
    @Inject(UsersService) private _usersService: UsersService,
    @Inject(JwtStorageService) private _jwtService: JwtStorageService,
    private router: Router,
    private dialog: MatDialog,
    changeDetectorRef: ChangeDetectorRef,
    media: MediaMatcher,
    private titleService: TitleService
    ) {
      this.mobileQuery = media.matchMedia('(max-width: 481px)');
      this._mobileQueryListener = () => changeDetectorRef.detectChanges();
      this.mobileQuery.addEventListener('change', this._mobileQueryListener);
    }

  ngOnInit() {
    this.checkSize(window.innerWidth);
    this.canRender = false;
    this.getAllUsers();
    this.titleService.setTitle('Utenti');
  }

  // Filter users by filterValue
  applyFilter(filterValue: string) {
    this.filteredUsers = this.users;
    filterValue = filterValue.trim().toLowerCase();
    if (filterValue) {
      if (!this.isMobile) {
        this.dataSource.filter = filterValue;
      } else {
        this.filteredUsers = this.users.filter( infoService => {
          return infoService.name.trim().toLowerCase().includes(filterValue) ||
                  infoService.surname.trim().toLowerCase().includes(filterValue) ||
                  infoService.email.trim().toLowerCase().includes(filterValue) ||
                  infoService.userRole.trim().toLowerCase().includes(filterValue);
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
      if (this.confirm === true){
        this.deleteUser(this.deleteID);
      }
    });
  }

  // GET Request for all users
  protected getAllUsers(): void {
    this._usersService.getAllUsers().subscribe( data => {
      this.users = data;
      this.filteredUsers = data;
      this.dataSource = new MatTableDataSource(this.users);
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

  //DELETE Request for delete user
  protected deleteUser(userId): void {
      this._usersService.deleteUser(userId).subscribe(
        data => {
          // refresh the list
          this.getAllUsers();
          return true;
        },
        error => {
          console.error('Error deleting user!');
          return throwError(error);
        }
      );
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
