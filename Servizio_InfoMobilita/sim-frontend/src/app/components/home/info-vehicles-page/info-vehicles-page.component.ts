import { MapService } from './../../../services/map.service';
import { trigger, state, transition, style, animate } from '@angular/animations';
import { MediaMatcher } from '@angular/cdk/layout';
import { MobilityType } from './../../utils/mobility-type.enum';
import { MatTableDataSource } from '@angular/material';
import { Location } from './../../../model/location.model';
import { SmartStationService } from './../../../services/smart-station.service';
import { InfomobilityServiceService } from './../../../services/infomobility-service.service';
import { Router, ActivatedRoute } from '@angular/router';
import { Vehicle } from './../../../model/vehicle.model';
import { Component, OnInit, Inject, ChangeDetectorRef } from '@angular/core';


@Component({
  selector: 'app-info-vehicles-page',
  templateUrl: './info-vehicles-page.component.html',
  styleUrls: ['./info-vehicles-page.component.css'],
  animations: [
    trigger('detailExpand', [
      state('collapsed', style({height: '0px', minHeight: '0'})),
      state('expanded', style({height: '*'})),
      transition('expanded <=> collapsed', animate('225ms cubic-bezier(0.4, 0.0, 0.2, 1)')),
    ]),
  ],
})
export class InfoVehiclesPageComponent implements OnInit {

  singleVehicle: Vehicle[];
  vehicles: Vehicle[];

  private dataSource: MatTableDataSource<Vehicle>;
  displayedColumns: string[];
  expandedVehicle: Vehicle | null;

  private municipalityID: string;
  private smartStationID: string;
  private infomobilityServiceProviderID: string;

  smartStationLocation: Location;

  mapCenterLocation: Location;

  mobileQuery: MediaQueryList;
  canRender: boolean;
  isMobile: boolean;
  private _mobileQueryListener: () => void;

  constructor(
    @Inject(InfomobilityServiceService) private _infomobilityService: InfomobilityServiceService,
    private mapService: MapService,
    private _smartStationService: SmartStationService,
    private activatedRoute: ActivatedRoute,
    private router: Router,
    changeDetectorRef: ChangeDetectorRef,
    media: MediaMatcher
  ) {
      this.mobileQuery = media.matchMedia('(max-width: 768px)');
      this._mobileQueryListener = () => changeDetectorRef.detectChanges();
      this.mobileQuery.addEventListener('change', this._mobileQueryListener);
   }

  ngOnInit() {
    this.canRender = false;
    this.mapService.closeMap();
    const url = this.router.url;
    const parseUrl = this.router.parseUrl(url);
    this.municipalityID = parseUrl.root.children.primary.segments[2].path;
    this.smartStationID = parseUrl.root.children.primary.segments[3].path;
    this.infomobilityServiceProviderID = parseUrl.root.children.primary.segments[5].path;
    this.getAvailableVehicles();
    this.getSmartStationLocation();
    this.checkSize(window.innerWidth);
  }

  public onMapButtonClick(mapCenterLocation?: Location) {
    this.mapService.openMap();
    if (!mapCenterLocation) {
      this.mapCenterLocation = this.smartStationLocation;
    } else {
      this.mapCenterLocation = mapCenterLocation;
    }
  }

  public onVehicleMapButtonClick(vehicle: Vehicle) {
    this.singleVehicle = new Array();
    this.singleVehicle.push(vehicle);
    this.onMapButtonClick(vehicle.currentLocation);
  }

  // GET request for all available vehicles (trains departures in this case)
  protected getAvailableVehicles(): void {
    this._infomobilityService.getAvailableVehicles(this.infomobilityServiceProviderID, this.smartStationID).subscribe( vehicles => {
        this.vehicles = vehicles;
        this.initColumns();
        this.dataSource = new MatTableDataSource(this.vehicles);
        this.canRender = true;
      },
      err => console.error(err),
    );
  }

  private getSmartStationLocation(): void {
    this._smartStationService.getSmartStationLocationByID(this.smartStationID).subscribe( location => {
      this.smartStationLocation = location;
    });
  }

  private initColumns() {
    if ( ['BIKE', 'CAR', 'E_SCOOTER', 'TAXI'].includes(this.vehicles[0].mobilityType) ) {
      this.displayedColumns = ['Tipo', 'Numero', 'Locazione', 'Costo orario', 'Trazione', 'Azioni'];
    } else {
      this.displayedColumns = ['Tipo', 'Linea', 'Fermata', 'Orario', 'Ritardo', 'Trazione', 'Azioni'];
    }
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
