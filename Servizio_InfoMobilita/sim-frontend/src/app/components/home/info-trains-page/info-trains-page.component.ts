import { MediaMatcher } from '@angular/cdk/layout';
import { Vehicle } from './../../../model/vehicle.model';
import { MatSort } from '@angular/material/sort';
import { MatTableDataSource } from '@angular/material/table';
import { Component, Inject, OnInit, ViewChild, ChangeDetectorRef } from '@angular/core';
import {InfomobilityServiceService} from '../../../services/infomobility-service.service';
import {ActivatedRoute, Router} from '@angular/router';
import {Departures} from '../../../model/departures.model';
import { trigger, state, style, transition, animate } from '@angular/animations';

@Component({
  selector: 'app-info-trains-page',
  templateUrl: './info-trains-page.component.html',
  styleUrls: ['./info-trains-page.component.css'],
  animations: [
    trigger('detailExpand', [
      state('collapsed', style({height: '0px', minHeight: '0'})),
      state('expanded', style({height: '*'})),
      transition('expanded <=> collapsed', animate('225ms cubic-bezier(0.4, 0.0, 0.2, 1)')),
    ]),
  ],

})
export class InfoTrainsPageComponent implements OnInit {

  private municipalityID: string;
  private infomobilityServiceProviderID: string;
  private smartStationID: string;

  private vehicles: Vehicle[];
  private dataSource: MatTableDataSource<Vehicle>;
  expandedTrain: Vehicle | null;

  displayedColumns: string[] = ['Treno', 'Binario', 'Partenza', 'Ritardo', 'Arrivo', 'Azioni'];


  @ViewChild(MatSort, {static: false}) sort: MatSort;

  mobileQuery: MediaQueryList;
  canRender: boolean;
  isMobile: boolean;
  private _mobileQueryListener: () => void;
  searchText: string;

  constructor(
    @Inject(InfomobilityServiceService) private _infomobilityService: InfomobilityServiceService,
    private activatedRoute: ActivatedRoute,
    private route: Router,
    changeDetectorRef: ChangeDetectorRef,
    media: MediaMatcher
    ) {
      this.mobileQuery = media.matchMedia('(max-width: 768px)');
      this._mobileQueryListener = () => changeDetectorRef.detectChanges();
      this.mobileQuery.addEventListener('change', this._mobileQueryListener);
    }

  ngOnInit() {
    this.canRender = false;
    const url = this.route.url;
    const parseUrl = this.route.parseUrl(url);
    this.municipalityID = parseUrl.root.children.primary.segments[2].path;
    this.smartStationID = parseUrl.root.children.primary.segments[3].path;
    this.infomobilityServiceProviderID = parseUrl.root.children.primary.segments[5].path;
    this.getAvailableVehicles();
    this.checkSize(window.innerWidth);
  }

  // GET request for all available vehicles (trains departures in this case)
  protected getAvailableVehicles(): void {
    this._infomobilityService.getAvailableVehicles(this.infomobilityServiceProviderID, this.smartStationID).subscribe( vehicles => {
      console.log(vehicles);
        this.vehicles = vehicles;
        this.dataSource = new MatTableDataSource(this.vehicles);
        this.dataSource.sort = this.sort;
        this.canRender = true;
      },
      err => console.error(err),
    );
  }

  // Go to the previous page
  private goBack(): void {
    this.route.navigate(['..'], {relativeTo: this.activatedRoute});
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
