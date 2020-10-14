import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class TitleService {

  private shortTitle: string;
  private title: string;

  constructor() {}

  public appendToTitle(partialTitle: string) {
    this.title = `${this.title} > ${partialTitle}`;
    this.shortTitle = `> ${partialTitle}`;
    this.storeTitles();
  }

  public removeLastHopFromTitle() {
    this.title = this.title.substring(0, this.title.lastIndexOf('>'));
    this.shortTitle = this.title.substring(this.title.lastIndexOf('>') !== -1 ? this.title.lastIndexOf('>') : 0, this.title.length - 1);
    this.storeTitles();
  }

  public getTitle() {
    return this.title ? this.title : this.getTitleFromStorage() ;
  }

  public getShortTitle() {
    return this.shortTitle ? this.shortTitle : this.getShortTitleFromStorage();
  }

  public setTitle(title: string) {
    this.title = title;
    this.shortTitle = title;
    this.storeTitles();
  }

  private getTitleFromStorage() {
    return localStorage.getItem('title');
  }

  private getShortTitleFromStorage() {
    return localStorage.getItem('shorttitle');
  }

  private storeTitles() {
    localStorage.setItem('title', this.title);
    localStorage.setItem('shorttitle', this.shortTitle);
  }
}
