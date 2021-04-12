import {Component, Inject, OnInit} from '@angular/core';
import {UsersService} from '../../../../services/users.service';
import {User} from '../../../../model/user.model';
import {FormControl, FormGroup, Validators} from '@angular/forms';
import { Observable, throwError } from 'rxjs';
import {HttpErrorResponse} from '@angular/common/http';
import {Router} from '@angular/router';

@Component({
  selector: 'app-add-user-form',
  templateUrl: './add-user-form.component.html',
  styleUrls: ['./add-user-form.component.css']
})
export class AddUserFormComponent implements OnInit {

  user = new User(null, null, null, null, null, null);
  addUserForm: FormGroup;

  canRender: boolean = false;
  hidePassword: boolean;

  constructor(
    @Inject(UsersService) private _usersService: UsersService,
    private router: Router) {}

  ngOnInit() {
    this.canRender = false;
    this.hidePassword = true;
    this.addUserForm = new FormGroup({
      name : new FormControl(null, [Validators.required, Validators.maxLength(30), Validators.minLength(2)]),
      surname : new FormControl(null, [Validators.required, Validators.maxLength(30), Validators.minLength(2)]),
      email : new FormControl(null, [Validators.required, Validators.email]),
      password : new FormControl(null, [Validators.required]),
      userRole : new FormControl(null, Validators.required),
    });
    this.canRender = true;
  }

  // POST Request for add a user
  protected createUser(): void {
    this.user.name = this.addUserForm.value['name'];
    this.user.surname = this.addUserForm.value['surname'];
    this.user.email  = this.addUserForm.value['email'];
    this.user.password = this.addUserForm.value['password'];
    this.user.userRole = this.addUserForm.value['userRole'];
    this._usersService.createUser(this.user).subscribe(
      data => {
        this.router.navigate(['/main/users']);
        return true;
      },
      err => {
        if (err instanceof HttpErrorResponse) {
          if (err.status === 401) {
            this.router.navigate(['/main/home']);
          }
          if (err.status === 403) {
            this.router.navigate(['/main/home']);
          }
        }
        console.error("Error saving user!");
        return throwError(err);
      }
    );
  }

  //ERROR message for name
  getErrorMessageName() {
    return this.addUserForm.get('name').hasError('required') ? 'You must enter a value' :
      this.addUserForm.get('name').hasError('maxlength') ? 'Name cannot have more than 30 characters' :
        this.addUserForm.get('name').hasError('minlength') ? 'Name must have at least 2 characters' :
          '';
  }
  //ERROR message for surname
  getErrorMessageSurname() {
    return this.addUserForm.get('surname').hasError('required') ? 'You must enter a value' :
      this.addUserForm.get('surname').hasError('maxlength') ? 'Surname cannot have more than 30 characters' :
        this.addUserForm.get('surname').hasError('minlength') ? 'Surname must have at least 3 characters' :
          '';
  }
  //ERROR message for email
  getErrorMessageEmail() {
    return this.addUserForm.get('email').hasError('required') ? 'You must enter a value' :
      this.addUserForm.get('email').hasError('email') ? 'Not a valid email' :
        '';
  }
  //ERROR message for user role
  getErrorMessageUserRole() {
    return this.addUserForm.get('userRole').hasError('required') ? 'You must enter a value' :
      '';
  }
  //ERROR message for password
  getErrorMessagePassword() {
    return this.addUserForm.get('password').hasError('required') ? 'You must enter a value' :
      '';
  }
}
