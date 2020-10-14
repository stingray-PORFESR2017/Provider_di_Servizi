import {Component, OnInit, Inject} from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import {AuthenticationService} from '../../services/authentication.service';
import {Observable, throwError} from 'rxjs';
import {Router} from '@angular/router';
import {JwtStorageService} from '../../services/jwt-storage.service';
import { MatSnackBar } from '@angular/material';
import { stringify } from 'querystring';

@Component({
  selector: 'app-login-form',
  templateUrl: './login-form.component.html',
  styleUrls: ['./login-form.component.css']
})

export class LoginFormComponent implements OnInit {

  mobileView: boolean;
  hidePassword: boolean;
  loginForm: FormGroup;

  constructor(
    @Inject(AuthenticationService) protected _authService: AuthenticationService,
    @Inject(JwtStorageService) protected _jwtStorageService: JwtStorageService,
    private router: Router,
    private snackBar: MatSnackBar
    ) {}

  ngOnInit() {
    this.checkSize(window.innerWidth);
    this.hidePassword = true;
    this.loginForm = new FormGroup({
      email : new FormControl('', [Validators.email, Validators.required]),
      password : new FormControl('', [Validators.required])
    });
  }

  // PUT request to check credientials, store token if OK and redirect to homepage
  protected LogIn(): void {
    let credentials = {
      "email": this.loginForm.value['email'],
      "password": this.loginForm.value['password']
    };
    this._authService.login(credentials).subscribe(
      res => {
        this._jwtStorageService.store(res.token.split(' ')[1]);
        this.openSnackBar('Login effettuato con successo!', 'success-snack-bar');
        //this._jwtStorageService.store(res.token);
        this.router.navigate(['/main/home']);
      },
      error => {
        this.openSnackBar('Email o password non valide!', 'fail-snack-bar');
        return throwError(error);
      }
    );
  }

  // Triggers every times windows is resized
  onResize(event) {
    this.checkSize(event.target.innerWidth);
  }

  checkSize(size): void {
    if (size < 768) {
      this.mobileView = true;
    } else {
      this.mobileView = false;
    }
  }

  openSnackBar(message: string, className: string, action?: string) {
    this.snackBar.open(message, action, {
      duration: 5000,
      panelClass: className
    });
  }

  onKeyDown($event: KeyboardEvent) {
    if ($event.key === 'Enter') {
      this.LogIn();
    }
  }
}
