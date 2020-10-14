import { Location } from './../../../model/location.model';
import {DatePipe, DOCUMENT} from '@angular/common';
import { LongitudePipe } from './../../../pipes/longitude.pipe';
import { LatitudePipe } from './../../../pipes/latitude.pipe';
import { Vehicle } from './../../../model/vehicle.model';
import {Component, OnInit, AfterViewInit, Input, Inject, HostListener} from '@angular/core';

import * as L from 'leaflet';

@Component({
  selector: 'app-map',
  templateUrl: './map.component.html',
  styleUrls: ['./map.component.css']
})
export class MapComponent implements AfterViewInit {

  @Input() mapCenterLocation: Location;
  @Input() vehicles: Vehicle[];

  private map: L.map;

  constructor(
    @Inject(DOCUMENT) private document: Document,
    private datePipe: DatePipe,
    private latitudePipe: LatitudePipe,
    private longitudePipe: LongitudePipe
  ) { }

  ngAfterViewInit(): void {
    this.onResize(null);
    this.initMap();
    this.makeVehiclesMarkers(this.map);
  }

  private initMap(): void {
    this.map = L.map('map', {
      center: this.mapCenterLocation.lat && this.mapCenterLocation.lon ? [ this.mapCenterLocation.lat, this.mapCenterLocation.lon] : [41.9,12.5],
      zoom: 18
    });

    const tiles = L.tileLayer('https://{s}.tile.openstreetmap.org/{z}/{x}/{y}.png', {
        maxZoom: 19,
        attribution: '&copy; <a href="http://www.openstreetmap.org/copyright">OpenStreetMap</a>'
    });

    tiles.addTo(this.map);

  }

  private makeVehiclesMarkers(map: L.map): void {
    if ( !this.vehicles || this.vehicles.length === 0 ) {
      return;
    }
    const vehicleIcon = L.icon({
      iconUrl: this.vehicles[0].mobilityType ? `assets/mobility-types-icons/${this.vehicles[0].mobilityType.toLowerCase()}.png` : 'assets/map-icons/room.png' ,
      iconSize: [41, 41],
      iconAnchor: [12, 41],
      popupAnchor: [1, -34],
      tooltipAnchor: [16, -28]
    });

    if ( this.vehicles.length === 1 ) {
      const stopIcon = L.icon({
        iconUrl: 'assets/map-icons/room.png' ,
        iconSize: [41, 41],
        iconAnchor: [12, 41],
        popupAnchor: [1, -34],
        tooltipAnchor: [16, -28]
      });

      const endIcon = L.icon({
        iconUrl: 'assets/map-icons/golf_course.png' ,
        iconSize: [41, 41],
        iconAnchor: [12, 41],
        popupAnchor: [1, -34],
        tooltipAnchor: [16, -28]
      });

      const vehicle = this.vehicles[0];
      let pathLocations = [];
      const startMarker = L.marker ( L.latLng({lat: vehicle.currentLocation.lat, lng: vehicle.currentLocation.lon}), {icon: vehicleIcon });
      pathLocations.push(L.latLng({lat: vehicle.currentLocation.lat, lng: vehicle.currentLocation.lon}));
      startMarker.bindPopup(this.makeVehiclePopup(vehicle));
      startMarker.addTo(map);

      for ( const stop of vehicle.upcomingStops.slice(0, vehicle.upcomingStops.length - 1)) {
        let stopMarker = L.marker ( L.latLng({lat: stop.lat, lng: stop.lon}), {icon: stopIcon });
        pathLocations.push(L.latLng(L.latLng({lat: stop.lat, lng: stop.lon})));
        stopMarker.bindPopup(this.makePathPopup(stop));
        stopMarker.addTo(map);
      }

      const endMarker = L.marker (
        L.latLng({
          lat: vehicle.upcomingStops[vehicle.upcomingStops.length - 1].lat,
          lng: vehicle.upcomingStops[vehicle.upcomingStops.length - 1].lon}),
          {icon: endIcon }
        );
      pathLocations.push(
        L.latLng({
          lat: vehicle.upcomingStops[vehicle.upcomingStops.length - 1].lat,
          lng: vehicle.upcomingStops[vehicle.upcomingStops.length - 1].lon
        }));

      endMarker.bindPopup(this.makePathPopup(vehicle.upcomingStops[vehicle.upcomingStops.length - 1 ]));
      endMarker.addTo(map);
      const polyline = L.polyline(pathLocations, {color: 'red'}).addTo(map);
    } else {
      for ( const v of this.vehicles) {
        let marker = L.marker ( L.latLng({lat: v.currentLocation.lat, lng: v.currentLocation.lon}), {icon: vehicleIcon });
        marker.bindPopup(this.makeVehiclePopup(v));
        marker.addTo(map);
      }

    }
  }

  private makeVehiclePopup(vehicle: Vehicle) {
    const serialNumberRow = vehicle.number ? `<div class="vehicle-serial-nbr">ID : ${vehicle.number} </div>` : '';

    const locationRow = vehicle.currentLocation.lat && vehicle.currentLocation.lon ?
      `<div class="vehicle-location>` +
      `<img src="src/assets/map-icons/explore.png">` +
      `<span>${this.latitudePipe.transform(vehicle.currentLocation.lat)}</span> ` +
      `<span>${this.longitudePipe.transform(vehicle.currentLocation.lon)}</span>` +
      `</div>` : '';

    const nominalDepartureTimeRow = vehicle.nominalDepartureTime ?
     `<div class="vehicle-departure-time">` +
     `<img src="/assets/map-icons/departure_board.png">` +
     `<span>${this.datePipe.transform(vehicle.nominalDepartureTime, 'HH:mm')}</span>` +
     `</div>` : '';

    const delayRow = vehicle.delay ?
    `<div class="vehicle-delay">` +
    `<img src="/assets/map-icons/update.png">` +
    `<span>${vehicle.delay}'</span>` +
    `</div>` : '';

    const descriptionRow = vehicle.description ? `<div class="vehicle-description> ${vehicle.description} </div>` : '';

    const hourlyCostRow = vehicle.hourlyCost ?
    `<div class="vehicle-hourly-cost>` +
    `<span>Costo orario:${vehicle.hourlyCost}€/h</span>` +
    `</div>` : '';

    const bookingLinkRow = vehicle.bookingLink ?
    `<div class="vehicle-booking-link>` +
    `<img src="/assets/map-icons/payment.png">` +
    `<span>${vehicle.bookingLink}</span>` +
    `</div>` : '';

    const powerTypeRow = vehicle.powerType ? `<div class="vehicle-power-type">${vehicle.powerType}</div>` : '';

    let stopListString = '';
    if ( vehicle.upcomingStops) {
      for ( const stop of vehicle.upcomingStops) {
        if ( stop.label ) {
          stopListString += `<mat-list-item role="listitem">${stop.label}</mat-list-item>`;

        }
      }
    }
    const stopsRow = vehicle.upcomingStops ?
    `<mat-accordion class="vehicle-stops>` +
    `<mat-expansion-panel>` +
    `<mat-expansion-panel-header>` +
    `<mat-panel-title>Fermate</mat-panel-title>` +
    `</mat-expansion-panel-header>` +
    `<mat-list role="list">` +
    stopListString +
    `</mat-list>` +
    `</mat-expansion-panel>` +
    `</mat-accordion>` : '';

    return  '' + serialNumberRow +
                locationRow +
                nominalDepartureTimeRow +
                delayRow +
                descriptionRow +
                hourlyCostRow +
                bookingLinkRow +
                powerTypeRow +
                stopsRow;

  }

  private makePathPopup(location: Location ) {
    const stopNameRow = `<h4 class="stop-name">${location.label} </h4>`;
    const locationRow = `<div class="stop-location>` +
      `<img src="src/assets/map-icons/explore.png">` +
      `<span>${this.latitudePipe.transform(location.lat)}</span> ` +
      `<span>${this.longitudePipe.transform(location.lon)}</span>` +
      `</div>`;

    return stopNameRow + locationRow;
  }

  private computeEuclideanDistance(pointA: Location, pointB: Location ): number {
    return Math.sqrt(Math.pow(pointB.lat - pointA.lat, 2) + Math.pow(pointB.lon - pointA.lon, 2));
  }

  @HostListener('window:resize', ['$event'])
  onResize(event) {
    const mapcontainer = this.document.getElementById('map-container');
    const  mainToolbar = this.document.getElementById('main-toolbar');
    const  mapbutton = this.document.getElementById('map-button');
    const innerHeight = window.innerHeight;
    mapcontainer.style.height = (innerHeight - mainToolbar.clientHeight - mapbutton.clientHeight  * 2.5) + 'px';
  }

}
