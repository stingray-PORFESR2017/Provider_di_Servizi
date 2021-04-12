import {Component, Inject, OnInit} from '@angular/core';
import {ActivatedRoute, Router} from '@angular/router';
import { Location } from '@angular/common';

import { MunicipalityService } from './../../../services/municipality.service';
import { Municipality } from './../../../model/municipality.model';
import { TitleService } from './../../../services/title.service';
import {AmbientalData, SmartStation} from '../../../model/smart-station.model';
import {SmartStationService} from '../../../services/smart-station.service';

@Component({
  selector: 'app-municipality-page',
  templateUrl: './municipality-page.component.html',
  styleUrls: ['./municipality-page.component.css']
})
export class MunicipalityPageComponent implements OnInit {

  private municipalityID: string;

  private smartStations: SmartStation[] = [];
  private ambientalData: AmbientalData;

  private canRender: boolean;
  private cols: number;
  private searchText: string;


  constructor(
    @Inject(SmartStationService) private _smartStationService: SmartStationService,
    private route: ActivatedRoute,
    private location: Location,
    private router: Router,
    private municipalityService: MunicipalityService,
    public  titleService: TitleService
    ) {}

  ngOnInit() {
    this.canRender = false;
    this.checkSize(window.innerWidth);
    this.municipalityID = this.route.snapshot.paramMap.get('id');
    this.getSmartStationByMunicipality();
  }


  // GET request for all smart station in a selected municipality and store them
  protected getSmartStationByMunicipality(): void {
    this._smartStationService.getSmartStationByMunicipality(this.municipalityID).subscribe(
      data => {
        this.smartStations = data;
        this.getAllAmbientalData();
        this.canRender = true;
      },
      err => {
        console.log('Error loading smart station');
      },
    );
  }

  protected getAllAmbientalData(): void {
    this.smartStations.forEach( smartStation => {
      console.log(smartStation);
      this._smartStationService.getSmartStationAmbientalData(smartStation.id).subscribe( data => {
        this.ambientalData = data;
        // Maybe you can do simply smartStation.tempEst = ... ?
        this.smartStations[this.smartStations.indexOf(smartStation)].tempEst = this.ambientalData.cmadAnalogInfo.tempEst;
        // See consideration above
        this.smartStations[this.smartStations.indexOf(smartStation)].tempSuolo = this.ambientalData.cmadAnalogInfo.tempSuolo;
      },
      error => {
        console.log('Error loading ambiental data');
      },
      );
    });

  }

  //Triggers every times windows is resized
  onResize(event) {
    this.checkSize(event.target.innerWidth);
  }

  //Define columns number of cards grid
  checkSize(size: number): void {
    if (size > 2260) {
      this.cols = 5;
    }
    else if ( size > 1460 && size <= 2260) {
      this.cols = 4;
    }
    else if ( size > 1120 && size <= 1460) {
      this.cols = 3;
    }
    else if (size > 600 && size <= 1120) {
      this.cols = 2;
    }
    else {
      this.cols = 1;
    }
  }

  //Go to the previous page
  private goBack(): void {
    this.location.back();
    this.router.navigate(['main/home']);
  }
}
