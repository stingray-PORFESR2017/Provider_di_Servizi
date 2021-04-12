import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class MapService {

  viewMap: boolean;

  constructor() { }

  public isMapActive() {
    return this.viewMap;
  }

  public openMap() {
    this.viewMap = true;
  }

  public closeMap() {
    this.viewMap = false;
  }
}
