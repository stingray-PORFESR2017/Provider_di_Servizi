import {Component, Inject, OnInit} from '@angular/core';
import {ActivatedRoute, Router} from '@angular/router';
import {UsersService} from '../../../../services/users.service';
import {User} from '../../../../model/user.model';
import {FormControl, FormGroup, Validators} from '@angular/forms';
import { Observable, throwError } from 'rxjs';
import {HttpErrorResponse} from '@angular/common/http';

@Component({
  selector: 'app-user-details',
  templateUrl: './user-details.component.html',
  styleUrls: ['./user-details.component.css']
})
export class UserDetailsComponent implements OnInit {

  private  userId: string;
  private sub: any;
  private user = new User(null, null, null, null, null, null);

  canRender: boolean = false;
  hidePassword: boolean;

  private show: boolean = true;
  userForm: FormGroup;

  constructor(
    @Inject(UsersService) private _usersService: UsersService,
    private route: ActivatedRoute,
    private router: Router
    ) {}

  ngOnInit() {
    this.hidePassword = true;
    this.sub = this.route.params.subscribe(params => {
      this.userId = params['id'];
      this.getUserById();
    });
  }

  // GET Request for user selected
  protected getUserById(): void {
    this._usersService.getUserByID(this.userId).subscribe(
      data => {
        this.user = data;
        this.userForm = new FormGroup({
          name: new FormControl(this.user.name, [Validators.required, Validators.maxLength(30), Validators.minLength(2)]),
          surname: new FormControl(this.user.surname, [Validators.required, Validators.maxLength(30), Validators.minLength(2)]),
          email: new FormControl(this.user.email, [Validators.required, Validators.email]),
          password: new FormControl(this.user.password),
          userRole: new FormControl(this.user.userRole),
        });
        this.canRender = true;
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
      },
      () => {},
    );
  }

  //PUT Request for update user
  protected updateUser(): void{
    this.user.name = this.userForm.value['name'];
    this.user.surname = this.userForm.value['surname'];
    this.user.email  = this.userForm.value['email'];
    this.user.password = this.userForm.value['password'];
    this.user.userRole = this.userForm.value['userRole'];
    this._usersService.updateUser(this.user).subscribe(
      data => {
        this.router.navigate(['/main/users']);
        return true;
      },
      error => {
        console.error("Error saving user!");
        return throwError(error);
      }
    );
  }

  //SHOW password field
  protected pswField(): void {
    this.show = !this.show;
  }
  // Error message for name
  getErrorMessageName() {
    return this.userForm.get('name').hasError('required') ? 'You must enter a value' :
      this.userForm.get('name').hasError('maxlength') ? 'Name cannot have more than 30 characters' :
        this.userForm.get('name').hasError('minlength') ? 'Name must have at least 2 characters' :
          '';
  }
  // Error message for surname
  getErrorMessageSurname() {
    return this.userForm.get('surname').hasError('required') ? 'You must enter a value' :
      this.userForm.get('surname').hasError('maxlength') ? 'Surname cannot have more than 30 characters' :
        this.userForm.get('surname').hasError('minlength') ? 'Surname must have at least 3 characters' :
          '';
  }
  // Error message for email
  getErrorMessageEmail() {
    return this.userForm.get('email').hasError('required') ? 'You must enter a value' :
      this.userForm.get('email').hasError('email') ? 'Not a valid email' :
        '';
  }
}
