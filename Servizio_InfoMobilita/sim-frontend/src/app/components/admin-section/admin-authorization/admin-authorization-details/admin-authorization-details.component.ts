import {Component, Inject, OnInit} from '@angular/core';
import {FormControl, FormGroup, Validators} from '@angular/forms';
import {ActivatedRoute, Router} from '@angular/router';
import {HttpErrorResponse} from '@angular/common/http';
import {Observable} from 'rxjs';
import {AdminAuthorization} from '../../../../model/admin-authorization.model';
import {AdminAuthorizationService} from '../../../../services/admin-authorization.service';
import {MunicipalityService} from '../../../../services/municipality.service';
import {InfomobilityServiceService} from '../../../../services/infomobility-service.service';
import {Municipality} from '../../../../model/municipality.model';
import {InfomobilityService} from '../../../../model/infomobility-service.model';

@Component({
  selector: 'app-admin-authorization-details',
  templateUrl: './admin-authorization-details.component.html',
  styleUrls: ['./admin-authorization-details.component.css']
})
export class AdminAuthorizationDetailsComponent implements OnInit {

  private authId: string;
  private userID: string;
  private municipalities: Municipality[] = [];
  private infomobilities: InfomobilityService[] = [];
  private adminAuth = new AdminAuthorization(null,null,null,null,null);
  private canRender : boolean = false;
  private disabled: boolean;

  adminAuthorizationForm : FormGroup;

  constructor(@Inject(AdminAuthorizationService) private _adminAuthService: AdminAuthorizationService, @Inject(MunicipalityService) private _municipalityService: MunicipalityService, @Inject(InfomobilityServiceService) private _infomobilitySService: InfomobilityServiceService, private route: ActivatedRoute, private router: Router){}

  ngOnInit() {
    const url = this.router.url;
    const parseUrl = this.router.parseUrl(url);
    this.userID = parseUrl.root.children.primary.segments[3].path;
    this.authId = parseUrl.root.children.primary.segments[4].path;

    this.getAllInfomobility();
    this.getAllMunicipality();
    this.getAdminAuthById();
  }

  protected disable(): void {
    if(this.disabled == false){
      this.adminAuthorizationForm.get('expire_date').disable();
    }
    else {
      this.adminAuthorizationForm.get('expire_date').enable();
    }
  }

  protected getAdminAuthById(): void {
    this._adminAuthService.getAdminAuthorizationByID(this.authId).subscribe(
      data => { this.adminAuth = data;
        if(this.adminAuth.expire_date != null){
          this.disabled = false;
          this.adminAuth.expire_date = new Date(this.adminAuth.expire_date);
        }
        else {
          this.disabled = true;
          this.adminAuth.expire_date = null;
        }
        this.adminAuthorizationForm = new FormGroup({
          municipality_id : new FormControl(this.adminAuth.municipality.id, [Validators.required]),
          info_id : new FormControl(this.adminAuth.isp.id, [Validators.required]),
          expire_date : new FormControl({value: this.adminAuth.expire_date, disabled: this.disabled},[Validators.required])
        });
        this.canRender = true;
      },
      err => {
        if (err instanceof HttpErrorResponse) {
          if(err.status === 401) {
            this.router.navigate(['/main/home']);
          }
          if(err.status === 403) {
            this.router.navigate(['/main/home']);
          }
        }
      },
    );
  }

  protected getAllInfomobility(): void {
    this._infomobilitySService.getAllInfomobilityService().subscribe(
      data => {
        this.infomobilities = data;
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
      },
      err => {
        console.error("Error saving municipalities!");
        return Observable.throw(err);
      })
  }

  getErrorMessageMunicipality() {
    return this.adminAuthorizationForm.get('municipality_id').hasError('required') ? 'You must enter a value' :
          '';
  }
  getErrorMessageInfomobility() {
    return this.adminAuthorizationForm.get('info_id').hasError('required') ? 'You must enter a value' :
        '';
  }
  getErrorMessageExpireDate() {
    return this.adminAuthorizationForm.get('expire_date').hasError('required') ? 'You must enter a date or check the box' :
      '';
  }

  protected updateAdminAuthorization(): void{
    this.adminAuth.municipality = this.municipalities.find(s => s.id == this.adminAuthorizationForm.value['municipality_id']);
    const isp_app = this.infomobilities.find(s => s.id == this.adminAuthorizationForm.value['info_id']);
    const isp = {"id": isp_app.id, "name": isp_app.name};
    this.adminAuth.isp = isp;
    if(this.disabled == true){
      this.adminAuth.expire_date = null;
    }
    else{
      this.adminAuth.expire_date = this.adminAuthorizationForm.value['expire_date'];
    }
    this._adminAuthService.updateAdminAuthorization(this.adminAuth).subscribe(
      data => {
        this.router.navigate(['/main/users/adminAuthorization/', this.userID]);
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
