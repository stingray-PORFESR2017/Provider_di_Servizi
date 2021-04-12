import {Inject, Injectable} from '@angular/core';
import {HttpInterceptor, HttpRequest, HttpHandler, HttpEvent} from '@angular/common/http';
import {Observable} from 'rxjs';
import {JwtStorageService} from './jwt-storage.service';

@Injectable({
  providedIn: 'root'
})
export class TokenInterceptorService implements HttpInterceptor {

  constructor(@Inject(JwtStorageService) private _jwtStorageService: JwtStorageService) {
  }

  // Guarantees that every http request to an API sets the header field "Authorization" with the token in local storage
  intercept(req: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {
      let tokenizeReq = req.clone({
        setHeaders: {
          Authorization: `Bearer ${this._jwtStorageService.read()}`
        }
      });
      return next.handle(tokenizeReq);
    }
}
