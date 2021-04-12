import { InfomobilityServiceLite } from './../model/infomobility-service-lite.model';
import { Vehicle } from './../model/vehicle.model';
import { environment } from './../../environments/environment';
import { Injectable, Inject} from '@angular/core';
import {HttpClient, HttpHeaders} from '@angular/common/http';
import { InfomobilityService } from '../model/infomobility-service.model';
import { Observable } from 'rxjs';

const httpOptions = {
  headers: new HttpHeaders({ 'Content-Type': 'application/json' })
};

@Injectable()
export class InfomobilityServiceService {

  private url = `${environment.backendUrl}/infomobility-services`;

  constructor(@Inject(HttpClient) private http: HttpClient) { }

  public getUrl(): string {
    return this.url;
  }

  // send a GET request to the API to retrieve a list of available vehicles of an isp in a smart station
  public getAvailableVehicles(infomobilityServiceProviderID: string, smartStationID: string): Observable<Vehicle[]> {
    return this.http.get<Vehicle[]>(`${this.url}/${infomobilityServiceProviderID}/vehicles?ssuuid=${smartStationID}`);
  }

  // send a GET request to the API to retrieve a list of all infomobility by smart station ID
  public getInfomobilityBySmartStation(smartID): Observable<InfomobilityService[]> {
    return this.http.get<InfomobilityService[]>(`${this.url}/smart-station/${smartID}`);
  }

  // send a GET request to the API to retrieve a list of infomobility that can be associated to a smart station
  public getFilterInfomobilityService(smartID): Observable<InfomobilityServiceLite[]> {
    return this.http.get<InfomobilityService[]>(`${this.url}/associable-smart-stations/${smartID}`);
  }

  // send a GET request to the API to retrieve a list of all infomobility authorized for ADMIN
  public getInfomobilityServiceForAdmin(): Observable<InfomobilityService[]> {
    return this.http.get<InfomobilityService[]>(`${this.url}/getAllAuthorized`);
  }

  // send a GET request to the API to retrieve an infomobility by ID
  public getInfomobilityServiceByID(infomobilityID): Observable<InfomobilityService> {
    return this.http.get<InfomobilityService>(`${this.url}/${infomobilityID}`);
  }

  // send a GET request to the API to retrieve a list of all infomobility for SUPER_ADMIN
  public getAllInfomobilityService(): Observable<InfomobilityService[]> {
    return this.http.get<InfomobilityService[]>(this.url);
  }

  // send a POST request to the API to create a new data object
  public createInfomobilityService(infomobilityService): Observable<InfomobilityService> {
    const body = JSON.stringify(infomobilityService);
    return this.http.post<InfomobilityService>(this.url, body, httpOptions);
  }

  // send a PATCH request to the API to update a specific field of an object
  public patchInfomobilityService(infomobilityService): Observable<InfomobilityService>{
    // const body = JSON.stringify(infomobilityService);
    // return this.http.patch<InfomobilityService>('http://localhost:4201/infomobilityService/' + infomobilityService.id, body, httpOptions);
    return this.http.patch<InfomobilityService>(`${this.url}/${infomobilityService.id}?enable=${infomobilityService.enabled}`, httpOptions);
  }

  // send a PUT request to the API to update a data object
  public updateInfomobilityService(infomobilityService): Observable<InfomobilityService> {
    const body = JSON.stringify(infomobilityService);
    return this.http.put<InfomobilityService>(`${this.url}/${infomobilityService.id}`, body, httpOptions);
  }

  // send a DELETE request to the API to delete a data object
  public deleteInfomobilityService(infomobilityServiceId): Observable<InfomobilityService> {
    return this.http.delete<InfomobilityService>(`${this.url}/${infomobilityServiceId}`);
  }

  public setInfomobilityServiceImage(infomobilityServiceID: string, image: File) {
    const options = {
      headers: new HttpHeaders({ 'Content-Type': image.type })
    };
    return this.http.post(`${this.url}/${infomobilityServiceID}/picture`, image, options);
  }


}
