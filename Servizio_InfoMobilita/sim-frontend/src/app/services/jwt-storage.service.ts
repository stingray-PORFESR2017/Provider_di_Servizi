import { Injectable } from '@angular/core';
import * as jwt_decode from 'jwt-decode';
import {Router} from '@angular/router';
import {isNotNullOrUndefined} from 'codelyzer/util/isNotNullOrUndefined';

@Injectable({
  providedIn: 'root'
})
export class JwtStorageService {
  static JWT_KEY = 'jwt';

  constructor(private router: Router) {
  }

  // STORE token in local storage of the browser
  store(jwt: string): void {
    localStorage.setItem(JwtStorageService.JWT_KEY, jwt);
  }

  // READ token in local storage of the browser
  read(): string {
    return localStorage.getItem(JwtStorageService.JWT_KEY);
  }

  // REMOVE toke in local storage of the browser
  remove(): void {
    localStorage.removeItem(JwtStorageService.JWT_KEY);
    this.router.navigate(['/main/home']);
  }

  // Decode and get ID of the current user
  getID(): string {
    const decoded = jwt_decode(this.read());
    return decoded['id'];
  }

  // Decode and get USERNAME of the current user
  getUsername(): string {
    const decoded = jwt_decode(this.read());
    return decoded['name'];
  }

  // Decode and get ROLE of the current user
  getRole(): string {
    const decoded = jwt_decode(this.read());
    return decoded ? decoded['userRole'] : undefined;
  }

  // CHECK if the token in local storage is correct
  checkToken(): boolean {
    const user = this.getUsername();
    const role = this.getRole();

    if (isNotNullOrUndefined(user) || isNotNullOrUndefined(role)) {
      return true;
    } else {
      return false;
    }
  }

  // Simple CHECK if a token is in the local storage
  isLoggedIn(): boolean {
    return !!localStorage.getItem(JwtStorageService.JWT_KEY);
  }
}

