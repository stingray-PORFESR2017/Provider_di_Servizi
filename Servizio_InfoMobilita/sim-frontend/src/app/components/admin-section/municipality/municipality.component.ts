import { MediaMatcher } from '@angular/cdk/layout';
import { Component, Inject, OnInit, ViewChild, ChangeDetectorRef, OnDestroy } from '@angular/core';
import {MatTableDataSource} from '@angular/material/table';
import {MatSort} from '@angular/material/sort';
import {Router} from '@angular/router';
import {MunicipalityService} from '../../../services/municipality.service';
import {HttpErrorResponse} from '@angular/common/http';
import {Municipality} from '../../../model/municipality.model';
import { Observable, throwError } from 'rxjs';
import {DeleteDialogComponent} from '../delete-dialog/delete-dialog.component';
import {MatDialog} from '@angular/material';
import {TitleService} from "../../../services/title.service";

@Component({
  selector: 'app-municipality',
  templateUrl: './municipality.component.html',
  styleUrls: ['./municipality.component.css']
})
export class MunicipalityComponent implements OnInit {

  municipalities: Municipality[] = [];

  displayedColumns: string[] = ['name', 'lat', 'lon', 'actions'];
  dataSource: MatTableDataSource<Municipality>;

  filteredMunicipalities: Municipality[];

  private deleteID: string;
  private confirm: boolean = false;

  canRender: boolean = false
  isMobile: boolean;


  @ViewChild(MatSort, {static: false}) set content(sort: MatSort) {
    if (this.canRender) {
      this.dataSource.sort = sort;
    }
  }

  constructor(
    @Inject(MunicipalityService) private _municipalityService: MunicipalityService,
    private router: Router,
    private dialog: MatDialog,
    private titleService: TitleService
    ) {}

  ngOnInit() {
    this.checkSize(window.innerWidth);
    this.canRender = false;
    this.getAllMunicipality();
    this.titleService.setTitle('Municipalità');
  }


  //FILTER smart station by filterValue
  applyFilter(filterValue: string) {
    this.filteredMunicipalities = this.municipalities;
    filterValue = filterValue.trim().toLowerCase();
    if (filterValue) {
      if (!this.isMobile) {
        this.dataSource.filter = filterValue;
      } else {
        this.filteredMunicipalities = this.municipalities.filter( municipality => {
          return municipality.name.trim().toLowerCase().includes(filterValue) ||
                 municipality.lat.toString().trim().toLowerCase().includes(filterValue) ||
                 municipality.lon.toString().trim().toLowerCase().includes(filterValue);
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
        this.deleteMunicipality(this.deleteID);
      }
    });
  }

  //GET Request for all mucipalities
  protected getAllMunicipality(): void {
    this._municipalityService.getAllMunicipality().subscribe( data => {
        this.municipalities = data;
        this.filteredMunicipalities = data;
        this.dataSource = new MatTableDataSource(this.municipalities);
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

  //DELETE Request for delete mucipalities
  protected deleteMunicipality(municipalityId): void {
      this._municipalityService.deleteMunicipality(municipalityId).subscribe(
        data => {
          // refresh the list
          this.getAllMunicipality();
          return true;
        },
        error => {
          console.error("Error deleting mucipalities!");
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
