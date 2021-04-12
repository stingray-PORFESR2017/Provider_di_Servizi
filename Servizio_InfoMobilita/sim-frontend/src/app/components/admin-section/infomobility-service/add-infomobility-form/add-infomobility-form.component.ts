import { MobilityType } from '../../../utils/mobility-type.enum';
import { Component, Inject, OnInit } from '@angular/core';
import {InfomobilityServiceService} from '../../../../services/infomobility-service.service';
import {InfomobilityService} from '../../../../model/infomobility-service.model';
import {FormControl, FormGroup, Validators} from '@angular/forms';
import { Observable, throwError } from 'rxjs';
import {SmartStationService} from '../../../../services/smart-station.service';
import {Router} from '@angular/router';
import {SmartStation} from '../../../../model/smart-station.model';
import {SmartStationLite} from '../../../../model/smart-station-lite.model';
import {JwtStorageService} from '../../../../services/jwt-storage.service';
import { Location } from '@angular/common';

@Component({
  selector: 'app-add-infomobility-form',
  templateUrl: './add-infomobility-form.component.html',
  styleUrls: ['./add-infomobility-form.component.css']
})
export class AddInfomobilityFormComponent implements OnInit {

  private mobilityTypes: string[] = ['BIKE', 'BUS', 'CAR', 'E_SCOOTER', 'METRO', 'TAXI', 'TRAIN', 'TRAM', 'POLY'];
  private infomobilityService = new InfomobilityService(null, null, false, null, null, null, null);
  private smartStations: SmartStation[];
  private addInfomobilityServiceForm: FormGroup;

  private canRender: boolean = false;
  private imageFile: File;

  constructor(
    @Inject(InfomobilityServiceService) private _infomobilitySService: InfomobilityServiceService,
    @Inject(SmartStationService) private _smartService : SmartStationService,
    @Inject(JwtStorageService) private _jwtService: JwtStorageService,
    private router: Router,
    private location: Location) { }

  ngOnInit() {
    this.canRender = false;
    if (this._jwtService.getRole() === 'SUPER_ADMIN') {
      this.getSmartStations();
    } else {
      this.router.navigate(['/main/infomobilityService']);
    }
  }

  // GET Request smart stations for a new infomobility
  protected getSmartStations(): void {
    this._smartService.getAllSmartStation().subscribe( data => {
        this.smartStations = data;
        this.addInfomobilityServiceForm = new FormGroup({
          name : new FormControl(null, [Validators.required, Validators.maxLength(30), Validators.minLength(2)]),
          mobilityTypes: new FormControl(null),
          serviceProviderType: new FormControl(null, [Validators.required]),
          serviceProviderTypeContent: new FormControl(null),
          smartStations: new FormControl(null)
        });
        this.canRender = true;
      },
      err => console.error(err),
    );
  }

  // POST Request for a new infomobility
  protected createInfomobilityService(): void {
    this.infomobilityService.name = this.addInfomobilityServiceForm.value['name'];
    this.infomobilityService.enabled = false;
    this.infomobilityService.mobilityTypes = this.addInfomobilityServiceForm.value['mobilityTypes'];
    this.infomobilityService.serviceProviderType = this.addInfomobilityServiceForm.value['serviceProviderType'];
    this.infomobilityService.serviceProviderTypeContent = this.addInfomobilityServiceForm.value['serviceProviderTypeContent'];
    if (this.addInfomobilityServiceForm.value['smartStations'] != null) {
      const liteSmartStations = [];
      for (const ss of this.addInfomobilityServiceForm.value['smartStations']) {
        const liteSmartStation = new SmartStationLite(ss.id, ss.name);
        liteSmartStations.push(liteSmartStation);
      }
      this.infomobilityService.smartStations = liteSmartStations;
    } else {
      this.infomobilityService.smartStations = null;
    }
    this._infomobilitySService.createInfomobilityService(this.infomobilityService).subscribe( infomobilityService => {
      this.infomobilityService = infomobilityService;
      if (this.imageFile) {
        this._infomobilitySService.setInfomobilityServiceImage(this.infomobilityService.id, this.imageFile).subscribe( () => {
          this.router.navigate(['/main/infomobilityService']);
        });
      } else {
        this.router.navigate(['/main/infomobilityService']);
      }
    },
    error => {
        console.error('Error saving infomobility-service!');
        return throwError(error);
    });
  }

  // ERROR message name
  getErrorMessageName() {
    return this.addInfomobilityServiceForm.get('name').hasError('required') ? 'Campo obbligatorio' :
      this.addInfomobilityServiceForm.get('name').hasError('maxlength') ? 'Il nome non può eccedere i 30 caratteri' :
        this.addInfomobilityServiceForm.get('name').hasError('minlength') ? 'Il nome deve essere di almeno 2 caratteri' : '';
  }
  // ERROR message service provider type
  getErrorMessageServiceProviderType() {
    return this.addInfomobilityServiceForm.get('serviceProviderType').hasError('required') ? 'Campo obbligatorio' : '';
  }

  // Go to the previous page
  private goBack(): void {
    this.location.back();
  }

  public onFileChange(event: any) {
    this.imageFile = event.target.files[0];
  }
}
