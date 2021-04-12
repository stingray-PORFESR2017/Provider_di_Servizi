import {Component, ElementRef, HostListener, Inject, OnInit} from '@angular/core';
import {ActivatedRoute} from "@angular/router";
import {DOCUMENT} from "@angular/common";
import { Location } from '@angular/common';

@Component({
  selector: 'app-embedded-iframe-page',
  templateUrl: './embedded-iframe-page.component.html',
  styleUrls: ['./embedded-iframe-page.component.css']
})
export class EmbeddedIframePageComponent implements OnInit {

  private uri: string;
  private innerWidth: number;
  private innerHeight: number;

  private frame: any;

  constructor(
    @Inject(DOCUMENT) private document: Document,
    private route: ActivatedRoute,
    private elementRef: ElementRef,
    private location: Location
  ) {
  }

  ngOnInit() {
    this.route.queryParams.subscribe(params => {
      this.uri = params['uri'];
    });
    this.frame = this.document.getElementById('frame');
    this.frame.src = this.uri;
    this.frame.allowFullscreen = true;
    this.onResize(null);
  }

  @HostListener('window:resize', ['$event'])
  onResize(event) {
    const  mainToolbar = this.document.getElementById('main-toolbar');
    this.innerWidth = window.innerWidth;
    this.innerHeight = window.innerHeight;
    this.frame.style.width = (innerWidth) + 'px';
    this.frame.style.height = (this.innerHeight - mainToolbar.clientHeight) + 'px';
  }

}
