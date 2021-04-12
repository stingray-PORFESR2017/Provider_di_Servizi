import { environment } from './../../environments/environment';
import {Inject, Injectable} from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';

const httpOptions = {
  headers: new HttpHeaders({ 'Content-Type': 'application/json' })
};

@Injectable()
export class AuthenticationService {

  constructor(@Inject(HttpClient) protected http: HttpClient) { }

  private url = `${environment.backendUrl}/auth`;

  // send a POST request to the API with LOGIN Credentials
  public login(credentials): Observable<any>{
    const body = JSON.stringify(credentials);
    return this.http.post<any>(this.url, body, httpOptions);
  }

}
