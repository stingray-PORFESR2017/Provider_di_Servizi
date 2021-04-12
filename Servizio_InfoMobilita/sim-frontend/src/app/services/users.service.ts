import { environment } from './../../environments/environment';
import { Injectable, Inject } from '@angular/core';
import {HttpClient, HttpHeaders} from '@angular/common/http';
import {User} from '../model/user.model';
import {Observable} from 'rxjs';


const httpOptions = {
  headers: new HttpHeaders({ 'Content-Type': 'application/json' })
};

@Injectable()
export class UsersService {

  private url = `${environment.backendUrl}/users`;

  constructor(@Inject(HttpClient) protected http: HttpClient) { }

  // send a GET request to the API to retrieve requested user
  public getUserByID(userID): Observable<User> {
    return this.http.get<User>(`${this.url}/${userID}`);
  }

  // send a GET request to the API to retrieve a list of users
  public getAllUsers(): Observable<User[]> {
    return this.http.get<User[]>(this.url);
  }

  // send a POST request to the API to create a new data object
  public createUser(user): Observable<User> {
    const body = JSON.stringify(user);
    return this.http.post<User>(this.url, body, httpOptions);
  }

  // send a PUT request to the API to update a data object
  public updateUser(user): Observable<User> {
    const body = JSON.stringify(user);
    return this.http.put<User>(`${this.url}/${user.id}`, body, httpOptions);
  }

  // send a DELETE request to the API to delete a data object
  public deleteUser(userId): Observable<User> {
    return this.http.delete<User>(`${this.url}/${userId}`);
  }
}
