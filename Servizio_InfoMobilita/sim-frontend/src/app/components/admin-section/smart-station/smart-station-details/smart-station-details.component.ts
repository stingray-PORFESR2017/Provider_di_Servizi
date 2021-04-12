import {Component, Inject, OnInit} from '@angular/core';
import {ActivatedRoute, Router} from '@angular/router';
import {SmartStation} from '../../../../model/smart-station.model';
import {SmartStationService} from '../../../../services/smart-station.service';
import {FormControl, FormGroup, Validators} from '@angular/forms';
import { Observable, throwError } from 'rxjs';
import {InfomobilityServiceService} from '../../../../services/infomobility-service.service';
import {InfomobilityService} from '../../../../model/infomobility-service.model';
import {HttpErrorResponse} from '@angular/common/http';
import {MunicipalityService} from '../../../../services/municipality.service';
import {Municipality} from '../../../../model/municipality.model';
import {InfomobilityServiceLite} from '../../../../model/infomobility-service-lite.model';
import {JwtStorageService} from '../../../../services/jwt-storage.service';

@Component({
  selector: 'app-smart-station-details',
  templateUrl: './smart-station-details.component.html',
  styleUrls: ['./smart-station-details.component.css']
})
export class SmartStationDetailsComponent implements OnInit {

  private smartStationID: string;
  private municipalities: Municipality[];

  // availableInfomobilityServices == SERVIZI DISPONIBILI -->  Tutti se SUPER-ADMIN, quelli abilitati sulla stazione se utente ADMIN
  private availableInfomobilityServices: InfomobilityServiceLite[];

  private infomobilityServices: InfomobilityServiceLite[] = [];
  private selectedEnabledInfomobilityServices: InfomobilityServiceLite[];
  private selectedDisabledInfomobilityServices: InfomobilityServiceLite[];
  private infoM: InfomobilityServiceLite[] = [];

  private smartStation: SmartStation;
  private smartStationForm: FormGroup;
  private canRender: boolean = false;
  private imageFile: File;

  constructor(
    @Inject(SmartStationService) private _smartStationService: SmartStationService,
    @Inject(InfomobilityServiceService) private _infomobilitySService : InfomobilityServiceService,
    @Inject(MunicipalityService) private _municipalityService: MunicipalityService,
    @Inject(JwtStorageService) private _jwtService: JwtStorageService,
    private route: ActivatedRoute,
    private router: Router) { }

  ngOnInit() {
    this.canRender = false;
    this.route.params.subscribe(params => {
      this.smartStationID = params.id;
      this.getMunicipality();
      this.getInfomobility();
    });
  }

  // GET Request municipality for ADMIN or SUPER_ADMIN
  protected getMunicipality(): void {
    const obs = this._jwtService.getRole() === 'ADMIN' ?
            this._municipalityService.getFilterMunicipality() : this._municipalityService.getAllMunicipality();
    obs.subscribe(
      municipalities => this.municipalities = municipalities,
      err => console.error(err)
    );
  }

  // GET Request infomobility for ADMIN or SUPER_ADMIN
  protected getInfomobility(): void {
    this.availableInfomobilityServices = [];
    const obs = this._jwtService.getRole() === 'ADMIN' ?
          this._infomobilitySService.getFilterInfomobilityService(this.smartStationID) :
          this._infomobilitySService.getAllInfomobilityService();
    obs.subscribe( infomobilityServices => {
      infomobilityServices.forEach(
        service => this.availableInfomobilityServices.push(new InfomobilityServiceLite(service.id, service.name))
        );
      this.getSmartStationById();
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
    });
  }

  // GET REquest for selected smart station
  protected getSmartStationById(): void {
    this._smartStationService.getSmartStationByID(this.smartStationID).subscribe(smartStation => {
        this.smartStation = smartStation;
        this.splitAvailableServicesIntoEnabledDisabled();
        this.smartStationForm = new FormGroup({
          name : new FormControl(this.smartStation.name, [Validators.required, Validators.maxLength(30), Validators.minLength(2)]),
          cmadMacAddress : new FormControl(this.smartStation.cmadMacAddress),
          externalPlaceId : new FormControl(this.smartStation.externalPlaceId),
          lat : new FormControl(this.smartStation.lat, [Validators.required]),
          lon : new FormControl(this.smartStation.lon, [Validators.required]),
          municipality : new FormControl(this.smartStation.municipality.id, [Validators.required]),
          infomobility : new FormControl(this.selectedEnabledInfomobilityServices)
        });
        this.checkSelected();
        this.canRender = true;
      },
      err => console.error(err)
    );
  }

    protected splitAvailableServicesIntoEnabledDisabled(): void {
      if (this.smartStation.infomobilityServices && this.smartStation.infomobilityServices.length > 0) {
        this.selectedEnabledInfomobilityServices = [];
        this.selectedDisabledInfomobilityServices = [];
        for ( const availableService of this.availableInfomobilityServices) {
          let enabledOnSmartStation = false;
          for (const smartStationService of this.smartStation.infomobilityServices) {
            if ( smartStationService.id === availableService.id) {
              enabledOnSmartStation = true;
              break;
            }
          }
          if(enabledOnSmartStation) {
            this.selectedEnabledInfomobilityServices.push(availableService);
          } else {
            this.selectedDisabledInfomobilityServices.push(availableService);
          }
        }
        console.log("Selected enabled");
        console.log(this.selectedEnabledInfomobilityServices);
        console.log("Selected disabled");
        console.log(this.selectedDisabledInfomobilityServices);
      } else {
        this.selectedEnabledInfomobilityServices = [];
        this.selectedDisabledInfomobilityServices = [];
      }
    }

    // protected checkSuperAdminSelected(): void {
    //   this.selectedEnabledInfomobilityServices = this.smartStation.infomobilityServices;
    // }

    // Define an array for the other infomobility not selected
    protected checkSelected(): void {
      if (this.smartStation.infomobilityServices && this.smartStation.infomobilityServices.length > 0) {
        this.infomobilityServices = this.smartStation.infomobilityServices.filter( s => this.availableInfomobilityServices.includes(s));
      }
    }

  // POST Request for updating smart station
  protected updateSmartStation(): void {
    this.smartStation.name = this.smartStationForm.value.name;
    this.smartStation.cmadMacAddress = this.smartStationForm.value.cmadMacAddress;
    this.smartStation.externalPlaceId = this.smartStationForm.value.externalPlaceId;
    this.smartStation.lat = this.smartStationForm.value.lat;
    this.smartStation.lon = this.smartStationForm.value.lon;
    this.smartStation.municipality = this.municipalities.find(s => s.id === this.smartStationForm.value.municipality
);
    for (let i = 0; i < this.smartStationForm.value.infomobility.length; i++) {
      const app = {
        'id': this.smartStationForm.value.infomobility[i].id,
        'name': this.smartStationForm.value.infomobility[i].name
      };
      this.infoM[i] = app;
    }

    this.smartStation.infomobilityServices = this.infoM.concat(this.selectedDisabledInfomobilityServices); // ?

    this._smartStationService.updateSmartStation(this.smartStation).subscribe( () => {
      if (this.imageFile) {
        this._smartStationService.setSmartStationImage(this.smartStationID, this.imageFile).subscribe( () => {
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
    return this.smartStationForm.get('name').hasError('required') ? 'Campo obbligatorio' :
      this.smartStationForm.get('name').hasError('maxlength') ? 'Il nome non può eccedere i 30 caratteri' :
        this.smartStationForm.get('name').hasError('minlength') ? 'Il nome deve essere di almeno 2 caratteri' : '';
  }
  // ERROR message latitude
  getErrorMessageLat() {
    return this.smartStationForm.get('lat').hasError('required') ? 'Campo obbligatorio' : '';
  }
  // ERROR message longitude
  getErrorMessageLon() {
    return this.smartStationForm.get('lon').hasError('required') ? 'Campo obbligatorio' : '';
  }
  // ERROR message municipality
  getErrorMessageMunicipality() {
    return this.smartStationForm.get('municipality').hasError('required') ? 'Campo obbligatorio' : '';
  }

  // Go to the previous page
  private goBack(): void {
    this.router.navigate(['/main/smartStation']);
  }

  public onFileChange(event: any) {
    this.imageFile = event.target.files[0];
  }
}
