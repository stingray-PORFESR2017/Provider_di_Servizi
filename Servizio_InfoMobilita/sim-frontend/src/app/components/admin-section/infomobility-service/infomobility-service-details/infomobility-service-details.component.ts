import { Component, Inject, OnInit } from '@angular/core';
import {ActivatedRoute, Router} from '@angular/router';
import {InfomobilityServiceService} from '../../../../services/infomobility-service.service';
import {FormControl, FormGroup, Validators} from '@angular/forms';
import { Observable, throwError } from 'rxjs';
import {InfomobilityService} from '../../../../model/infomobility-service.model';
import {SmartStationService} from '../../../../services/smart-station.service';
import {SmartStation} from '../../../../model/smart-station.model';
import {HttpErrorResponse} from '@angular/common/http';
import {SmartStationLite} from '../../../../model/smart-station-lite.model';
import {JwtStorageService} from '../../../../services/jwt-storage.service';
import { Location } from '@angular/common';

@Component({
  selector: 'app-infomobility-service-details',
  templateUrl: './infomobility-service-details.component.html',
  styleUrls: ['./infomobility-service-details.component.css']
})
export class InfomobilityServiceDetailsComponent implements OnInit {

  private mobilityTypes: string[] = ['BIKE', 'BUS', 'CAR', 'E_SCOOTER', 'TAXI', 'TRAIN', 'POLY'];
  private infomobilityServiceID: string;
  private infomobilityService = new InfomobilityService(null, null, null, null, null, null, null);
  private smartSelectEnabled: SmartStationLite[] = [];
  private smartSelectDisabled: SmartStationLite[] = [];
  private smartStations: SmartStation[] = [];
  private smartStationFilter: SmartStationLite[] = [];
  private infomobilityServiceForm: FormGroup;

  private canRender: boolean = false;
  private imageFile: File;

  constructor(
    @Inject(InfomobilityServiceService) private _infomobilitySService: InfomobilityServiceService,
    @Inject(SmartStationService) private _smartService: SmartStationService,
    @Inject(JwtStorageService) private _jwtService: JwtStorageService,
    private route: ActivatedRoute,
    private router: Router,
    private location: Location) { }

  ngOnInit() {
    this.canRender = false;
    this.route.params.subscribe(params => {
      this.infomobilityServiceID = params['id'];
      this.getSmartStations();
    });
  }

  //GET Request smart station for ADMIN or SUPER_ADMIN
  protected getSmartStations(): void {
    if (this._jwtService.getRole() === 'ADMIN') {
      this._smartService.getFilterSmartStation(this.infomobilityServiceID).subscribe(
        data => {
          this.smartStations = data;
          this.getInfomobilityServiceById();
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
      );
    } else {
      this._smartService.getAllSmartStation().subscribe( data => {
          this.smartStations = data;
          this.getInfomobilityServiceById();
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
      );
    }
  }

  // GET Request for selected infomobility
  protected getInfomobilityServiceById(): void {
    this._infomobilitySService.getInfomobilityServiceByID(this.infomobilityServiceID).subscribe(data => {
        this.infomobilityService = data;
        this.checkAdminSelected();
        this.infomobilityServiceForm = new FormGroup({
          name : new FormControl(this.infomobilityService.name, [Validators.required, Validators.maxLength(30),Validators.minLength(2)]),
          mobilityTypes: new FormControl((this.infomobilityService.mobilityTypes)),
          serviceProviderType : new FormControl(this.infomobilityService.serviceProviderType),
          serviceProviderTypeContent : new FormControl(this.infomobilityService.serviceProviderTypeContent),
          smartStations: new FormControl(this.smartSelectEnabled)
        });
        this.checkSelected();
        this.canRender = true;
      },
      err => console.error(err),
    );
  }

  //Define 2 arrays from the current smart station field of infomobility:
  // 1 for selected smart station authorized for ADMIN
  // 1 for selected smart station unauthorized for ADMIN (marked as disabled)
  protected checkAdminSelected(): void {
    if (this._jwtService.getRole() === 'ADMIN') {
      this.infomobilityService.smartStations.forEach(val => this.smartSelectDisabled.push(Object.assign({}, val)));
      if (this.smartSelectDisabled !== undefined) {
        for (const station of this.smartStations) {
          for (const disabledStation of this.smartSelectDisabled){
            if (disabledStation.id === station.id) {
              this.smartSelectEnabled.push(disabledStation);
              this.smartSelectDisabled.splice(this.smartSelectDisabled.indexOf(disabledStation), 1);
            }
          }
        }
      } else {
        this.smartSelectEnabled = this.infomobilityService.smartStations;
      }
    } else {
      this.smartSelectEnabled = this.infomobilityService.smartStations;
    }
  }

  //Define an array for smart stations not selected
  protected checkSelected(): void{
    this.smartStations.forEach(val => this.smartStationFilter.push(Object.assign({}, val)));
    if (this.smartStationFilter !== undefined) {
      for (const i of this.infomobilityService.smartStations){
        for (const j of this.smartStationFilter){
          if (j.id === i.id) {
            this.smartStationFilter.splice(this.smartStationFilter.indexOf(j), 1);
          }
        }
      }
    }
  }

  // POST Request for update infomobility
  protected updateInfomobilityService(): void {
    this.infomobilityService.name = this.infomobilityServiceForm.value['name'];
    this.infomobilityService.mobilityTypes = this.infomobilityServiceForm.value['mobilityTypes'];
    this.infomobilityService.serviceProviderType = this.infomobilityServiceForm.value['serviceProviderType'];
    this.infomobilityService.serviceProviderTypeContent = this.infomobilityServiceForm.value['serviceProviderTypeContent'];
    const liteSmartStations = [];
    for (const ss of this.infomobilityServiceForm.value['smartStations']) {
      const liteSmartStation = new SmartStationLite(ss.id, ss.name);
      liteSmartStations.push(liteSmartStation);
    }
    this.infomobilityService.smartStations = liteSmartStations.concat(this.smartSelectDisabled);
    this._infomobilitySService.updateInfomobilityService(this.infomobilityService).subscribe( () => {
      if (this.imageFile) {
        this._infomobilitySService.setInfomobilityServiceImage(this.infomobilityServiceID, this.imageFile).subscribe( () => {
          this.router.navigate(['/main/infomobilityService']);
        });
      } else {
        this.router.navigate(['/main/infomobilityService']);
      }
    },
    error => {
        console.error('Error saving infomobility service!');
        return throwError(error);
    });
  }

  // ERROR message name
  getErrorMessageName() {
    return this.infomobilityServiceForm.get('name').hasError('required') ? 'Campo obbligatorio' :
      this.infomobilityServiceForm.get('name').hasError('maxlength') ? 'Il nome non può eccedere i 30 caratteri' :
        this.infomobilityServiceForm.get('name').hasError('minlength') ? 'Il nome deve essere di almeno 2 caratteri' :
          '';
  }

  //Go to the previous page
  private goBack(): void {
    this.location.back();
  }

  public onFileChange(event: any) {
    this.imageFile = event.target.files[0];
  }
}
