import {Component, Inject, OnInit} from '@angular/core';
import {SmartStationService} from '../../../../services/smart-station.service';
import {SmartStation} from '../../../../model/smart-station.model';
import {FormControl, FormGroup, Validators} from '@angular/forms';
import {Observable, throwError} from 'rxjs';
import {InfomobilityServiceService} from '../../../../services/infomobility-service.service';
import {InfomobilityService} from '../../../../model/infomobility-service.model';
import {HttpErrorResponse} from '@angular/common/http';
import {Router} from '@angular/router';
import {Municipality} from '../../../../model/municipality.model';
import {MunicipalityService} from '../../../../services/municipality.service';
import {InfomobilityServiceLite} from '../../../../model/infomobility-service-lite.model';
import {JwtStorageService} from '../../../../services/jwt-storage.service';
import {Location} from '@angular/common';

@Component({
  selector: 'app-add-station-form',
  templateUrl: './add-station-form.component.html',
  styleUrls: ['./add-station-form.component.css'],
  providers: [SmartStationService, InfomobilityServiceService]
})
export class AddStationFormComponent implements OnInit {

  private smartStation: SmartStation;

  private municipalities: Municipality[] = [];
  private infomobilityFull: InfomobilityService[] = [];
  private infomobility: InfomobilityServiceLite[] = [];

  private canRender: boolean = false;
  private imageFile: File;

  addSmartStationForm: FormGroup;

  constructor(
    @Inject(SmartStationService) private _smartStationService: SmartStationService,
    @Inject(InfomobilityServiceService) private _infomobilitySService: InfomobilityServiceService,
    @Inject(MunicipalityService) private _municipalityService: MunicipalityService,
    @Inject(JwtStorageService) private _jwtService: JwtStorageService,
    private router: Router,
    private location: Location
  ) {
  }

  ngOnInit() {
    this.canRender = false;
    this.smartStation = new SmartStation();
    this.getMunicipality();
    this.getAllInfomobility();
  }

  // GET Request municipality for ADMIN or SUPER_ADMIN
  protected getMunicipality(): void {
    if (this._jwtService.getRole() === 'ADMIN') {
      this._municipalityService.getFilterMunicipality().subscribe(
        data => {
          this.municipalities = data;
        },
        err => console.error(err),
      );
    } else {
      this._municipalityService.getAllMunicipality().subscribe(
        data => {
          this.municipalities = data;
        },
        err => console.error(err),
      );
    }
  }

  // GET Request infomobility
  protected getAllInfomobility(): void {
    this._infomobilitySService.getAllInfomobilityService().subscribe(data => {
        this.infomobilityFull = data;
        this.addSmartStationForm = new FormGroup({
          name: new FormControl(this.smartStation.name, [Validators.required, Validators.maxLength(30), Validators.minLength(2)]),
          cmadMacAddress: new FormControl(this.smartStation.cmadMacAddress),
          externalPlaceId: new FormControl(this.smartStation.externalPlaceId),
          lat: new FormControl(this.smartStation.lat, [Validators.required]),
          lon: new FormControl(this.smartStation.lon, [Validators.required]),
          municipality: new FormControl(this.smartStation.municipality, [Validators.required]),
          infomobility: new FormControl(this.smartStation.infomobilityServices)
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
      }
    );
  }

  // POST Request for new smart station
  protected createSmartStation(): void {
    this.smartStation.name = this.addSmartStationForm.value['name'];
    this.smartStation.cmadMacAddress = this.addSmartStationForm.value['cmadMacAddress'];
    this.smartStation.externalPlaceId = this.addSmartStationForm.value['externalPlaceId'];
    this.smartStation.lat = this.addSmartStationForm.value['lat'];
    this.smartStation.lon = this.addSmartStationForm.value['lon'];
    this.smartStation.enabled = false;
    this.smartStation.municipality = this.addSmartStationForm.value['municipality'];
    if (this.addSmartStationForm.value['infomobility'] != null) {
      for (let i = 0; i < this.addSmartStationForm.value['infomobility'].length; i++) {
        const app = {
          "id": this.addSmartStationForm.value['infomobility'][i].id,
          "name": this.addSmartStationForm.value['infomobility'][i].name
        };
        this.infomobility[i] = app;
      }
      this.smartStation.infomobilityServices = this.infomobility;
    } else {
      this.smartStation.infomobilityServices = null;
    }
    this._smartStationService.createSmartStation(this.smartStation).subscribe(smartStation => {
        this.smartStation = smartStation;
        if (this.imageFile) {
          this._smartStationService.setSmartStationImage(this.smartStation.id, this.imageFile).subscribe(() => {
            this.router.navigate(['/main/smartStation']);
          });
        } else {
          this.router.navigate(['/main/smartStation']);
        }
      },
      error => {
        console.error('Error saving smart station!');
        return throwError(error);
      });
  }

  // ERROR message name
  getErrorMessageName() {
    return this.addSmartStationForm.get('name').hasError('required') ? 'Campo obbligatorio' :
      this.addSmartStationForm.get('name').hasError('maxlength') ? 'Il nome non può eccedere i 30 caratteri' :
        this.addSmartStationForm.get('name').hasError('minlength') ? 'Il nome deve essere di almeno 2 caratteri' : '';
  }

  // ERROR message latitude
  getErrorMessageLat() {
    return this.addSmartStationForm.get('lat').hasError('required') ? 'Campo obbligatorio' : '';
  }

  // ERROR message longitude
  getErrorMessageLon() {
    return this.addSmartStationForm.get('lon').hasError('required') ? 'Campo obbligatorio' : '';
  }

  // ERROR message municipality
  getErrorMessageMunicipality() {
    return this.addSmartStationForm.get('municipality').hasError('required') ? 'Campo obbligatorio' : '';
  }

  // Go to the previous page
  private goBack(): void {
    this.location.back();
  }

  public onFileChange(event: any) {
    this.imageFile = event.target.files[0];
  }
}
