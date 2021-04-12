import { Location } from './../model/location.model';
import { Injectable, Inject } from '@angular/core';
import {HttpClient, HttpHeaders} from '@angular/common/http';

import { Observable } from 'rxjs';

import { environment } from './../../environments/environment';
import {AmbientalData, SmartStation} from '../model/smart-station.model';

const httpOptions = {
  headers: new HttpHeaders({ 'Content-Type': 'application/json' })
};

@Injectable()
export class SmartStationService {

  private url = `${environment.backendUrl}/smart-stations`;


  constructor(@Inject(HttpClient) protected http: HttpClient) { }

  public getUrl(): string {
    return this.url;
  }

  // send a GET request to the API for retrieve a smart station by ID
  public getSmartStationByID(smartID): Observable<SmartStation> {
    return this.http.get<SmartStation>(`${this.url}/${smartID}`);
  }

  public getSmartStationLocationByID(smartID: string): Observable<Location> {
    return this.http.get<Location>(`${this.url}/${smartID}/location`);
  }

  // send a GET request to the API for retrieve a list of smart station by municipality ID
  public getSmartStationAmbientalData(smartStationID): Observable<AmbientalData> {
    return this.http.get<AmbientalData>(`${this.url}/ambientalData/${smartStationID}`);
  }

  // send a GET request to the API for retrieve a list of smart station by municipality ID
  public getSmartStationByMunicipality(municipalityID): Observable<SmartStation[]> {
    return this.http.get<SmartStation[]>(`${this.url}/municipality/${municipalityID}`);
  }

  // send a GET request to the API for retrieve a list of smart station associable by the ADMIN
  public getFilterSmartStation(infoID): Observable<SmartStation[]> {
    return this.http.get<SmartStation[]>(`${this.url}/associable-infomobility-services/${infoID}`);
  }

  // send a GET request to the API for retrieve a list of smart station authorized for ADMIN
  public getSmartStationForAdmin(): Observable<SmartStation[]> {
    return this.http.get<SmartStation[]>(`${this.url}/getAllAuthorized`);
  }

  // send a GET request to the API for retrieve a list of smart station for SUPER_ADMIN
  public getAllSmartStation(): Observable<SmartStation[]> {
    return this.http.get<SmartStation[]>(this.url);
  }

  // send a POST request to the API to create a new data object
  public createSmartStation(smartStation): Observable<SmartStation> {
    const body = JSON.stringify(smartStation);
    return this.http.post<SmartStation>(this.url, body, httpOptions);
  }

  // send a PATCH request to the API to update a specific field of an object
  public patchSmartStation(smartStation): Observable<SmartStation> {
    return this.http.patch<SmartStation>(`${this.url}/${smartStation.id}?enable=${smartStation.enabled}`, httpOptions);
  }
  // send a PUT request to the API to update a data object
  public updateSmartStation(smartStation): Observable<SmartStation> {
    const body = JSON.stringify(smartStation);
    return this.http.put<SmartStation>(`${this.url}/${smartStation.id}`, body, httpOptions);
  }

  // send a DELETE request to the API to delete a data object
  public deleteSmartStation(smartStationId): Observable<SmartStation> {
    return this.http.delete<SmartStation>(`${this.url}/${smartStationId}`);
  }

  public setSmartStationImage(smartStationID: string, image: File) {
    const options = {
      headers: new HttpHeaders({ 'Content-Type': image.type })
    };
    return this.http.post(`${this.url}/${smartStationID}/picture`, image, options);
  }

}
