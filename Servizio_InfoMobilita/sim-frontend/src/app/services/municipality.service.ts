import { environment } from './../../environments/environment';
import { Injectable, Inject } from '@angular/core';
import {HttpClient, HttpHeaders} from '@angular/common/http';
import { Observable } from 'rxjs';
import {Municipality} from '../model/municipality.model';


const httpOptions = {
  headers: new HttpHeaders({ 'Content-Type': 'application/json' })
};

@Injectable()
export class MunicipalityService {

  private url = `${environment.backendUrl}/municipality`;


  constructor(@Inject(HttpClient) protected http: HttpClient) {}

  public getUrl(): string {
    return this.url;
  }

  // send a GET request to the API for retrieve a list of municipality authorized for ADMIN
  public getFilterMunicipality(): Observable<Municipality[]> {
    return this.http.get<Municipality[]>(`${this.url}/getAllAuthorized`);
  }

  // send a GET request to the API for retrieve a municipality by ID
  public getMunicipalityByID(municipalityID): Observable<Municipality> {
    return this.http.get<Municipality>(`${this.url}/${municipalityID}`);
  }

  // send a GET request to the API for retrieve a list of municipality for SUPER_ADMIN
  public getAllMunicipality(): Observable<Municipality[]> {
    return this.http.get<Municipality[]>(this.url);
  }

  // send a GET request to the API for retrieve a list of municipality for SUPER_ADMIN
  public getAllNotEmptyMunicipality(): Observable<Municipality[]> {
    return this.http.get<Municipality[]>(`${this.url}/notempty`);
  }

  // send a POST request to the API to create a new data object
  public createMunicipality(municipality): Observable<Municipality> {
    const body = JSON.stringify(municipality);
    return this.http.post<Municipality>(this.url, body, httpOptions);

  }

  // send a PUT request to the API to update a data object
  public updateMunicipality(municipality): Observable<Municipality> {
    const body = JSON.stringify(municipality);
    return this.http.put<Municipality>(`${this.url}/${municipality.id}`, body, httpOptions);
  }

  // send a DELETE request to the API to delete a data object
  public deleteMunicipality(municipalityId): Observable<Municipality> {
    return this.http.delete<Municipality>(`${this.url}/${municipalityId}`);
  }

  public setMunicipalityImage(municipalityID: string, image: File) {
    const options = {
      headers: new HttpHeaders({ 'Content-Type': image.type })
    };
    return this.http.post(`${this.url}/${municipalityID}/picture`, image, options);
  }


}
