import { TitleService } from '../../../services/title.service';
import { Router } from '@angular/router';
import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-title',
  templateUrl: './title.component.html',
  styleUrls: ['./title.component.css']
})
export class TitleComponent implements OnInit {

  private previousPageUrl: string;
  private isMobile = false;

  constructor(
    private router: Router,
    private titleService: TitleService
  ) {}

  ngOnInit() {
    this.checkSize(window.innerWidth);

    const currentUrl = this.router.url;
    if (currentUrl !== '/main/home') {
      this.previousPageUrl = currentUrl.substring(0, currentUrl.lastIndexOf('/'));

    } else {
      this.titleService.setTitle('Home');
      this.previousPageUrl = '';
    }
  }

  //Triggers every times windows is resized
  onResize(event) {
    this.checkSize(event.target.innerWidth);
  }

  //Define columns number of cards grid
  checkSize(size: number): void {
    if (size < 481) {
      this.isMobile = true;
    } else {
      this.isMobile = false;
    }
  }

}
