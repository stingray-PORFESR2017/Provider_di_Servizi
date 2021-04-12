import { TitleService } from './../../services/title.service';
import { throwError } from 'rxjs';
import {Component, Inject, OnInit} from '@angular/core';
import {Municipality} from '../../model/municipality.model';
import {MunicipalityService} from '../../services/municipality.service';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit {

  municipalities: Municipality[] = [];
  private searchText: string;
  private cols: number;

  constructor(
    @Inject(MunicipalityService) private _municipalityService: MunicipalityService,
    public titleService: TitleService
    ) {}

  ngOnInit(): void {
    this.checkSize(window.innerWidth);
    this.getAllMunicipality();
    this.titleService.setTitle('Home');
  }

  // query for all municipality and store them
  protected getAllMunicipality(): void {
    this._municipalityService.getAllNotEmptyMunicipality().subscribe( data => {
        this.municipalities = data;
      },
      err => {
        console.log('Error loading municipality');
        throwError(err);
      },
    );
  }

  //Triggers every times windows is resized
  onResize(event) {
    this.checkSize(event.target.innerWidth);
  }

  //Define columns number of cards grid
  checkSize(size: number): void {
    if (size > 2260) {
      this.cols = 5;
    } else if ( size > 1460 && size <= 2260) {
      this.cols = 4;
    } else if ( size > 1120 && size <= 1460) {
      this.cols = 3;
    } else if (size > 600 && size <= 1120) {
      this.cols = 2;
    } else {
      this.cols = 1;
    }
  }
}
