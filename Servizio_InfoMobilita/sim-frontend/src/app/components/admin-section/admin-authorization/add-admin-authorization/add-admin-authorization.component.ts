import {Component, Inject, OnInit} from '@angular/core';
import {User} from '../../../../model/user.model';
import {Municipality} from '../../../../model/municipality.model';
import {InfomobilityService} from '../../../../model/infomobility-service.model';
import {AdminAuthorization} from '../../../../model/admin-authorization.model';
import {FormControl, FormGroup, Validators} from '@angular/forms';
import {AdminAuthorizationService} from '../../../../services/admin-authorization.service';
import {UsersService} from '../../../../services/users.service';
import {MunicipalityService} from '../../../../services/municipality.service';
import {InfomobilityServiceService} from '../../../../services/infomobility-service.service';
import {Router} from '@angular/router';
import {Observable} from 'rxjs';

@Component({
  selector: 'app-add-admin-authorization',
  templateUrl: './add-admin-authorization.component.html',
  styleUrls: ['./add-admin-authorization.component.css']
})
export class AddAdminAuthorizationComponent implements OnInit {

  private userID: string;
  private users: User[] = [];
  private user: User;
  private municipalities: Municipality[] = [];
  private infomobilities: InfomobilityService[] = [];
  private adminAuth = new AdminAuthorization(null,null,null,null,null);
  private canRender : boolean = false;
  private disabled: boolean = false;

  addAdminAuthorizationForm : FormGroup;

  constructor(@Inject(AdminAuthorizationService) private _adminAuthService: AdminAuthorizationService, @Inject(UsersService) private _usersService: UsersService, @Inject(MunicipalityService) private _municipalityService: MunicipalityService, @Inject(InfomobilityServiceService) private _infomobilitySService: InfomobilityServiceService, private router: Router){
  }

  ngOnInit() {
    this.canRender = false;
    const url = this.router.url;
    const parseUrl = this.router.parseUrl(url);
    this.userID = parseUrl.root.children.primary.segments[3].path;
    this.getAllUsers();
  }
  protected disable(): void {
    if(this.disabled == false){
      this.addAdminAuthorizationForm.get('expire_date').disable();
    }
    else {
      this.addAdminAuthorizationForm.get('expire_date').enable();
    }
  }
  protected getAllUsers(): void {
    this._usersService.getAllUsers().subscribe(
      data => {
        this.users = data;
        this.user = this.users.find(u => u.id == this.userID);
        this.getAllMunicipality();
      },
      err => {
        console.error("Error saving users!");
        return Observable.throw(err);
      })
  }
  protected getAllInfomobility(): void {
    this._infomobilitySService.getAllInfomobilityService().subscribe(
      data => {
        this.infomobilities = data;
        this.addAdminAuthorizationForm = new FormGroup({
          municipality_id : new FormControl(null, [Validators.required]),
          info_id : new FormControl(null, [Validators.required]),
          expire_date : new FormControl({value: null , disabled: this.disabled},[Validators.required])
        });
        this.canRender = true;
      },
      err => {
        console.error("Error saving infomobilities!");
        return Observable.throw(err);
      })
  }
  protected getAllMunicipality(): void {
    this._municipalityService.getAllMunicipality().subscribe(
      data => {
        this.municipalities = data;
        this.getAllInfomobility();
      },
      err => {
        console.error("Error saving municipalities!");
        return Observable.throw(err);
      })
  }

  getErrorMessageMunicipality() {
    return this.addAdminAuthorizationForm.get('municipality_id').hasError('required') ? 'You must enter a value' :
      '';
  }
  getErrorMessageInfomobility() {
    return this.addAdminAuthorizationForm.get('info_id').hasError('required') ? 'You must enter a value' :
      '';
  }
  getErrorMessageExpireDate() {
    return this.addAdminAuthorizationForm.get('expire_date').hasError('required') ? 'You must enter a date or check the box' :
      '';
  }

  protected createAdminAuthorization(): void {
    this.adminAuth.userAccount = this.user;
    this.adminAuth.municipality = this.municipalities.find(s => s.id == this.addAdminAuthorizationForm.value['municipality_id']);
    const isp_app = this.infomobilities.find(s => s.id == this.addAdminAuthorizationForm.value['info_id']);
    const isp = {"id": isp_app.id, "name": isp_app.name};
    this.adminAuth.isp = isp;
    if(this.disabled == true){
      this.adminAuth.expire_date = null;
    }
    else{
      this.adminAuth.expire_date = this.addAdminAuthorizationForm.value['expire_date'];
    }
    this._adminAuthService.createAdminAuthorization(this.adminAuth).subscribe(
      data => {
        this.router.navigate(['main/users/adminAuthorization/', this.userID]);
        return true;
      },
      error => {
        console.error("Error saving admin authorization!");
        return Observable.throw(error);
      }
    );
  }

  //Go to the previous page
  private goBack(): void {
    this.router.navigate(['/main/users/adminAuthorization/', this.userID]);
  }
}
