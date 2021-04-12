import {Inject, Injectable} from '@angular/core';
import { Router, CanActivate} from '@angular/router';
import {JwtStorageService} from '../services/jwt-storage.service';


@Injectable()
export class AuthGuard implements CanActivate {

  constructor(
    @Inject(JwtStorageService) private _jwtStorageService: JwtStorageService,
    private router: Router
    ) {}

  // Block the access to page if a valid token is not present in local storage
  canActivate(): boolean {
    try {
      if (this._jwtStorageService.checkToken()) {
        return true;
      } else {
        this.router.navigate(['/main/home']);
        return false;
      }
    } catch (e) {
      this.router.navigate(['/main/home']);
      return false;
    }

  }
}
