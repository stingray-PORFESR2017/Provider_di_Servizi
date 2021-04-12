import {Component, Inject, OnInit} from '@angular/core';
import {FormControl, FormGroup, Validators} from '@angular/forms';
import {ActivatedRoute, Router} from '@angular/router';
import {HttpErrorResponse} from '@angular/common/http';
import { Observable, throwError } from 'rxjs';
import {MunicipalityService} from '../../../../services/municipality.service';
import {Municipality} from '../../../../model/municipality.model';

@Component({
  selector: 'app-municipality-details',
  templateUrl: './municipality-details.component.html',
  styleUrls: ['./municipality-details.component.css']
})
export class MunicipalityDetailsComponent implements OnInit {

  private municipality: Municipality;
  private municipalityID: string;
  private imageFile: File;

  private municipalityForm: FormGroup;
  private canRender: boolean = false;

  constructor(
    @Inject(MunicipalityService) private _municipalityService: MunicipalityService,
    private route: ActivatedRoute,
    private router: Router) { }

  ngOnInit() {
    this.canRender = false;
    this.route.params.subscribe(params => {
      this.municipalityID = params['id'];
      this.getMunicipalityById();
    });
  }

  //GET Request for selected municipality
  protected getMunicipalityById(): void {
    this._municipalityService.getMunicipalityByID(this.municipalityID).subscribe(data => {
        this.municipality = data;
        this.municipalityForm = new FormGroup({
          name : new FormControl(this.municipality.name,
             [Validators.required, Validators.maxLength(30), Validators.minLength(2)]),
          lat : new FormControl(this.municipality.lat, [Validators.required]),
          lon : new FormControl(this.municipality.lon, [Validators.required]),
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
    );
  }

  //POST Request for update municipality
  protected updateMunicipality(): void {
    this.municipality.name = this.municipalityForm.value['name'];
    this.municipality.lat = this.municipalityForm.value['lat'];
    this.municipality.lon = this.municipalityForm.value['lon'];
    this._municipalityService.updateMunicipality(this.municipality).subscribe( data => {
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
      }
    );
  }

  // ERROR message name
  getErrorMessageName() {
    return this.municipalityForm.get('name').hasError('required') ? 'Campo obbligatorio' :
      this.municipalityForm.get('name').hasError('maxlength') ? 'Il nome non può eccedere i 30 caratteri' :
        this.municipalityForm.get('name').hasError('minlength') ? 'Il nome deve essere di almeno 2 caratteri' :
          '';
  }
  // ERROR message latitude
  getErrorMessageLat() {
    return this.municipalityForm.get('lat').hasError('required') ? 'Campo obbligatorio' : '';
  }
  // ERROR message longitude
  getErrorMessageLon() {
    return this.municipalityForm.get('lon').hasError('required') ? 'Campo obbligatorio' : '';
  }

  // Go to the previous page
  private goBack(): void {
    this.router.navigate(['/main/municipality']);
  }

  public onFileChange(event: any) {
    this.imageFile = event.target.files[0];
  }
}
