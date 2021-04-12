import { Location } from '@angular/common';
import { TitleService } from './../../../services/title.service';
import { Router } from '@angular/router';
import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-go-back-button',
  templateUrl: './go-back-button.component.html',
  styleUrls: ['./go-back-button.component.css']
})
export class GoBackButtonComponent implements OnInit {

  private previousPageUrl: string;

  constructor(
    private router: Router,
    private titleService: TitleService,
    private location: Location
  ) { }

  ngOnInit() {
    const currentUrl = this.router.url;
    if (currentUrl !== '/main/home') {
      this.previousPageUrl = currentUrl.substring(0, currentUrl.lastIndexOf('/'));

    } else {
      this.previousPageUrl = '';
    }
  }

  onClick() {
    this.titleService.removeLastHopFromTitle();
    this.location.back();
  }

}
