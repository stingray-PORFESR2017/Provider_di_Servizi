import { SmartStationService } from './../../../services/smart-station.service';
import { SmartStation } from './../../../model/smart-station.model';
import { TitleService } from './../../../services/title.service';
import { Location } from '@angular/common';
import {Component, Inject, OnInit} from '@angular/core';
import {InfomobilityService} from '../../../model/infomobility-service.model';
import {InfomobilityServiceService} from '../../../services/infomobility-service.service';
import {ActivatedRoute, Router} from '@angular/router';

@Component({
  selector: 'app-smart-station-page',
  templateUrl: './smart-station-page.component.html',
  styleUrls: ['./smart-station-page.component.css']
})
export class SmartStationPageComponent implements OnInit {

  private smartStationID: string;

  private municipalityID: string;
  private canRender: boolean;
  private searchText: string;
  private infomobilities: InfomobilityService[] = [];
  private cols: number;

  constructor(
    @Inject(InfomobilityServiceService) private _infomobilityService: InfomobilityServiceService,
    private route: ActivatedRoute,
    private location: Location,
    private router: Router,
    private smartStationService: SmartStationService,
    private titleService: TitleService) { }

  ngOnInit() {
    this.canRender = false;
    const url = this.router.url;
    const parseUrl = this.router.parseUrl(url);
    this.municipalityID = parseUrl.root.children.primary.segments[2].path;
    this.checkSize(window.innerWidth);
    this.smartStationID = this.route.snapshot.paramMap.get('id');
    this.getInfomobilityBySmartStation();
  }


  // GET request for all infomobility service in a selected smart station and store them
  protected getInfomobilityBySmartStation(): void {
    this._infomobilityService.getInfomobilityBySmartStation(this.smartStationID).subscribe(
      data => {
        this.infomobilities = data;
        this.canRender = true;
      },
      err => {console.log('Error loading infomobility')},
    );
  }

  //Triggers every times windows is resized
  onResize(event) {
    this.checkSize(event.target.innerWidth);
  }

  //Define columns number of cards grid
  checkSize(size): void {
    if(size > 2260) {
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

  public onCardClick(infomobilityService: InfomobilityService) {
    if (infomobilityService.serviceProviderType === 'REDIRECTABLE' ) {
      window.open(infomobilityService.serviceProviderTypeContent, '_blank');
    } else if ( infomobilityService.serviceProviderType === 'API') {
      this.titleService.appendToTitle(infomobilityService.name);
      if (infomobilityService.mobilityTypes.includes('TRAIN')){
        this.router.navigate([`/main/home/${this.municipalityID}/${this.smartStationID}/trains/${infomobilityService.id}`]);
      } else {
        this.router.navigate([`/main/home/${this.municipalityID}/${this.smartStationID}/vehicles/${infomobilityService.id}`]);
      }
    } else if (infomobilityService.serviceProviderType === 'EMBEDDABLE') {
      this.router.navigate([`/main/embed`], { queryParams: { uri: infomobilityService.serviceProviderTypeContent } });
    }
  }

  getBackgroundImage(i: InfomobilityService): string {
    return 'url(assets/service-logos/' + i.name.toLowerCase().replace(' ','_') + '.png)';
  }
}
