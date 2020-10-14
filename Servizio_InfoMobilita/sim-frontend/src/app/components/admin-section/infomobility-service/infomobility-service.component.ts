import {Component, Inject, OnInit, ViewChild} from '@angular/core';
import {HttpErrorResponse} from '@angular/common/http';
import {Router} from '@angular/router';

import {throwError } from 'rxjs';

import {MatDialog} from '@angular/material';
import {MatTableDataSource} from '@angular/material/table';
import {MatSort} from '@angular/material/sort';

import {DeleteDialogComponent} from '../delete-dialog/delete-dialog.component';
import {InfomobilityService} from '../../../model/infomobility-service.model';
import {InfomobilityServiceService} from '../../../services/infomobility-service.service';
import {JwtStorageService} from '../../../services/jwt-storage.service';
import {TitleService} from "../../../services/title.service";



@Component({
  selector: 'app-infomobility-service',
  templateUrl: './infomobility-service.component.html',
  styleUrls: ['./infomobility-service.component.css']
})

export class InfomobilityServiceComponent implements OnInit {

  private deleteID: string;
  private confirm: boolean = false;
  infomobilityServices: InfomobilityService[];

  displayedColumns: string[] = ['name', 'service provider', 'enabled', 'mobilityTypes', 'stations', 'actions'];
  dataSource: MatTableDataSource<InfomobilityService>;

  filteredInfomobilityServices: InfomobilityService[];

  canRender: boolean;
  isMobile: boolean;

  @ViewChild(MatSort, {static: false}) set content(sort: MatSort) {
    if (this.canRender) {
      this.dataSource.sort = sort;
    }
  }

  constructor(
    @Inject(InfomobilityServiceService) private _infomobilityServiceService: InfomobilityServiceService,
    @Inject(JwtStorageService) private _jwtService: JwtStorageService,
    private router: Router,
    private dialog: MatDialog,
    private titleService: TitleService
    ) {}

  ngOnInit() {
    this.checkSize(window.innerWidth);
    this.canRender = false;
    this.getInfomobilityService();
    this.titleService.setTitle('Servizi di Infomobilità');
  }
  //FILTER infomobility by filterValue
  applyFilter(filterValue: string) {
    this.filteredInfomobilityServices = this.infomobilityServices;
    filterValue = filterValue.trim().toLowerCase();
    if (filterValue) {
      if (!this.isMobile) {
        this.dataSource.filter = filterValue;
      } else {
        this.filteredInfomobilityServices = this.infomobilityServices.filter( infoService => {
          return infoService.name.trim().toLowerCase().includes(filterValue) ||
                 infoService.serviceProviderType.trim().toLowerCase().includes(filterValue);
        });
      }
    }
  }

  //Open dialog for deleting element
  openDialog(id: string, name: string): void {
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
        this.deleteInfomobilityService(this.deleteID);
      }
    });
  }

  //GET Request infomobility for ADMIN or SUPER_ADMIN
  protected getInfomobilityService(): void {
    if (this._jwtService.getRole() === 'ADMIN') {
      this._infomobilityServiceService.getInfomobilityServiceForAdmin().subscribe(
        data => {
          this.infomobilityServices = data;
          this.filteredInfomobilityServices = data;
          this.dataSource = new MatTableDataSource(this.infomobilityServices);
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
    } else {
      this._infomobilityServiceService.getAllInfomobilityService().subscribe(
        data => {
          this.infomobilityServices = data;
          this.filteredInfomobilityServices = data;
          this.dataSource = new MatTableDataSource(this.infomobilityServices);
          this.canRender = true;
        },
        err =>{
          if (err instanceof HttpErrorResponse) {
            if(err.status === 401) {
              this.router.navigate(['/main/home']);
            }
            if(err.status === 403) {
              this.router.navigate(['/main/home']);
            }
          }
        },
      );
    }
  }

  //PATCH enabled variable
  protected patchEnabled(id: string, enabled: boolean): void {
    const infomobilityService = {
      'id': id,
      'enabled': !enabled
    };
    this._infomobilityServiceService.patchInfomobilityService(infomobilityService).subscribe(
      data => {
        this.getInfomobilityService();
        return true;
      },
      error => {
        console.error('Error saving smart station!');
        return throwError(error);
      }
    );
  }

  //DELETE infomobility service
  protected deleteInfomobilityService(infomobilityServiceId: string): void {
    this._infomobilityServiceService.deleteInfomobilityService(infomobilityServiceId).subscribe(
      data => {
        // refresh the list
          this.getInfomobilityService();
          return true;
        },
        error => {
          console.error('Error deleting infomobility service!');
          return throwError(error);
        }
        );
  }

  public onResize(event) {
    this.checkSize(event.target.innerWidth);
  }

  private checkSize(size: number): void {
    if (size < 768) {
      this.isMobile = true;
    } else {
      this.isMobile = false;
    }
  }
}
