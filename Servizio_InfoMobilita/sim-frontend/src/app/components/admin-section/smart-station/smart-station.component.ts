import { MediaMatcher } from '@angular/cdk/layout';
import { Component, Inject, OnInit, ViewChild, ChangeDetectorRef, OnDestroy } from '@angular/core';
import { SmartStationService } from '../../../services/smart-station.service';
import { Observable, throwError } from 'rxjs';
import { SmartStation } from '../../../model/smart-station.model';
import { MatSort } from '@angular/material/sort';
import { MatTableDataSource } from '@angular/material/table';
import {Router} from '@angular/router';
import {HttpErrorResponse} from '@angular/common/http';
import {JwtStorageService} from '../../../services/jwt-storage.service';
import {DeleteDialogComponent} from '../delete-dialog/delete-dialog.component';
import {MatDialog} from '@angular/material';
import {TitleService} from "../../../services/title.service";

@Component({
  selector: 'app-smart-station',
  templateUrl: './smart-station.component.html',
  styleUrls: ['./smart-station.component.css']
})
export class SmartStationComponent implements OnInit {

  smartStations: SmartStation[] = [];

  displayedColumns: string[] = ['name', 'lat', 'lon', 'municipality', 'enabled', 'service', 'actions'];
  dataSource: MatTableDataSource<SmartStation>;

  filteredSmartStations: SmartStation[];

  mobileQuery: MediaQueryList;
  canRender: boolean;
  isMobile: boolean;

  private confirm: any;
  private deleteID: string;

  @ViewChild(MatSort, {static: false}) set content(sort: MatSort) {
    if (this.canRender) {
      this.dataSource.sort = sort;
    }
  }

  constructor(
    @Inject(SmartStationService) private _smartStationService: SmartStationService,
    @Inject(JwtStorageService) private _jwtService: JwtStorageService,
    private router: Router,
    private dialog: MatDialog,
    private titleService: TitleService,
    ) {}

  ngOnInit() {
    this.checkSize(window.innerWidth);
    this.canRender = false;
    this.getSmartStations();
    this.titleService.setTitle("Smart Stations");
  }

  //FILTER smart station by filterValue
  applyFilter(filterValue: string) {
    this.filteredSmartStations = this.smartStations;
    filterValue = filterValue.trim().toLowerCase();
    if (filterValue) {
      if (!this.isMobile) {
        this.dataSource.filter = filterValue;
      } else {
        this.filteredSmartStations = this.smartStations.filter( smartStation => {
          return smartStation.name.trim().toLowerCase().includes(filterValue) ||
                  smartStation.municipality.name.trim().toLowerCase().includes(filterValue) ||
                  smartStation.lat.toString().trim().toLowerCase().includes(filterValue) ||
                  smartStation.lon.toString().trim().toLowerCase().includes(filterValue);
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
        this.deleteSmartStation(this.deleteID);
      }
    });
  }
  //GET smart station for ADMIN or SUPER_ADMIN
  protected getSmartStations(): void {
    if (this._jwtService.getRole() === 'ADMIN') {
      this._smartStationService.getSmartStationForAdmin().subscribe( smartStations => {
          console.log(smartStations);
          this.smartStations = smartStations;
          this.filteredSmartStations = smartStations;
          this.dataSource = new MatTableDataSource(this.smartStations);
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
      this._smartStationService.getAllSmartStation().subscribe(
        data => {
          this.smartStations = data;
          this.filteredSmartStations = data;
          this.dataSource = new MatTableDataSource(this.smartStations);
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
  }

  //PATCH enabled variable
  protected patchEnabled(id, enabled): void {
    const smartStation = {
      "id": id,
      "enabled": !enabled
    };
    this._smartStationService.patchSmartStation(smartStation).subscribe(
      data => {
        this.getSmartStations();
        return true;
      },
      error => {
        console.error("Error saving smart station!");
        return throwError(error);
      }
    );
  }

  // DELETE smart station
  protected deleteSmartStation(smartStationId): void {
    this._smartStationService.deleteSmartStation(smartStationId).subscribe(
      data => {
        // refresh the list
        this.getSmartStations();
        return true;
        },
      error => {
        console.error("Error deleting smart station!");
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
