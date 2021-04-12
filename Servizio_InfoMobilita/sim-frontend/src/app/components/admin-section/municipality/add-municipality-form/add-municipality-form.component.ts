import {Component, Inject, OnInit} from '@angular/core';
import {FormControl, FormGroup, Validators} from '@angular/forms';
import {Router} from '@angular/router';
import { Observable, throwError } from 'rxjs';
import {Municipality} from '../../../../model/municipality.model';
import {MunicipalityService} from '../../../../services/municipality.service';

@Component({
  selector: 'app-add-municipality-form',
  templateUrl: './add-municipality-form.component.html',
  styleUrls: ['./add-municipality-form.component.css']
})
export class AddMunicipalityFormComponent implements OnInit {

  private municipality: Municipality;
  private imageFile: File;

  private addMunicipalityForm: FormGroup;
  private canRender: boolean = false;

  constructor(
    @Inject(MunicipalityService) private _municipalityService: MunicipalityService,
    private router: Router) {
  }

  ngOnInit() {
    this.canRender = false;
    this.municipality = new Municipality();
    this.addMunicipalityForm = new FormGroup({
      name: new FormControl(this.municipality.name, [Validators.required, Validators.maxLength(30), Validators.minLength(2)]),
      lat: new FormControl(this.municipality.lat, [Validators.required]),
      lon: new FormControl(this.municipality.lon, [Validators.required])
    });
    this.canRender = true;
  }

  //POST Request for add new municipality
  protected createMunicipality(): void {
    this.municipality.name = this.addMunicipalityForm.value['name'];
    this.municipality.lat = this.addMunicipalityForm.value['lat'];
    this.municipality.lon = this.addMunicipalityForm.value['lon'];

    this._municipalityService.createMunicipality(this.municipality).subscribe( municipality => {
      this.municipality = municipality;
      if (this.imageFile) {
        this._municipalityService.setMunicipalityImage(this.municipality.id, this.imageFile).subscribe( () => {
          this.router.navigate(['/main/municipality']);
         });
      } else {
                  this.router.navigate(['/main/municipality']);
      }
    },
    error => {
        console.error('Error saving municipality!');
        return throwError(error);
    });
  }

  // ERROR message name
  getErrorMessageName() {
    return this.addMunicipalityForm.get('name').hasError('required') ? 'Campo obbligatorio' :
      this.addMunicipalityForm.get('name').hasError('maxlength') ? 'Il nome non può eccedere i 30 caratteri' :
        this.addMunicipalityForm.get('name').hasError('minlength') ? 'Il nome deve essere di almeno 2 caratteri' :
          '';
  }
  // ERROR message latitude
  getErrorMessageLat() {
    return this.addMunicipalityForm.get('lat').hasError('required') ? 'Campo obbligatorio' : '';
  }
  // ERROR message longitude
  getErrorMessageLon() {
    return this.addMunicipalityForm.get('lon').hasError('required') ? 'Campo obbligatorio' : '';
  }

  // Go to the previous page
  private goBack(): void {
    this.router.navigate(['/main/municipality']);
  }

  public onFileChange(event: any) {
    this.imageFile = event.target.files[0];
  }
}
