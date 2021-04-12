
import { Injectable, Inject } from '@angular/core';
import {HttpClient, HttpHeaders} from '@angular/common/http';

import {Observable} from 'rxjs';

import { environment } from './../../environments/environment';
import {AdminAuthorization} from '../model/admin-authorization.model';



const httpOptions = {
  headers: new HttpHeaders({ 'Content-Type': 'application/json' })
};

@Injectable()
export class AdminAuthorizationService {

  private url = `${environment.backendUrl}/adminAuthorization`;

  constructor(@Inject(HttpClient) protected http: HttpClient) { }

  // send a GET request to the API to retrieve a list of authorization by userID
  public getAdminAuthorizationsByUserID(userID): Observable<AdminAuthorization[]> {
    return this.http.get<AdminAuthorization[]>(`${this.url}/user/${userID}`);
  }

  // send a GET request to the API to retrieve an auth by ID
  public getAdminAuthorizationByID(authID): Observable<AdminAuthorization> {
    return this.http.get<AdminAuthorization>(`${this.url}/${authID}`);
  }

  // send a GET request to the API to retrieve a list of object
  public getAllAdminAuthorization(): Observable<AdminAuthorization[]> {
    return this.http.get<AdminAuthorization[]>(this.url);
  }

  // send a POST request to the API to create a new data object
  public createAdminAuthorization(user): Observable<AdminAuthorization> {
    const body = JSON.stringify(user);
    return this.http.post<AdminAuthorization>(this.url, body, httpOptions);
  }

  // send a PUT request to the API to update a data object
  public updateAdminAuthorization(user): Observable<AdminAuthorization> {
    const body = JSON.stringify(user);
    return this.http.put<AdminAuthorization>(`${this.url}/${user.id}`, body, httpOptions);
  }

  // send a DELETE request to the API to delete a data object
  public deleteAdminAuthorization(userId): Observable<AdminAuthorization> {
    return this.http.delete<AdminAuthorization>(`${this.url}/${userId}`);
  }
}
