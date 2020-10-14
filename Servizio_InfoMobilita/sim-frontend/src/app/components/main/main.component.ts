import { TitleService } from './../../services/title.service';
import {MediaMatcher} from '@angular/cdk/layout';
import { ChangeDetectorRef, Component, Inject, OnDestroy, OnInit, AfterContentChecked } from '@angular/core';
import {Router} from '@angular/router';
import {JwtStorageService} from '../../services/jwt-storage.service';
import {isNotNullOrUndefined} from 'codelyzer/util/isNotNullOrUndefined';



@Component({
  selector: 'app-main',
  templateUrl: './main.component.html',
  styleUrls: ['./main.component.css']
})
export class MainComponent implements OnInit, AfterContentChecked, OnDestroy {

  private title: string;
  private userRole: string;
  private userName: string;

  private isMobile: boolean;
  mobileQuery: MediaQueryList;
  private _mobileQueryListener: () => void;


  constructor(
    @Inject(JwtStorageService) private _jwtStorageService: JwtStorageService,
    private router: Router,
    changeDetectorRef: ChangeDetectorRef,
    media: MediaMatcher,
    private titleService: TitleService,
    ) {
    this.mobileQuery = media.matchMedia('(max-width: 768px)');
    this._mobileQueryListener = () => changeDetectorRef.detectChanges();
    this.mobileQuery.addEventListener('change', this._mobileQueryListener);
  }

  // store userName and userRole if logged
  ngOnInit(): void {
    this.checkSize(window.innerWidth);
    this.title = this.isMobile ? this.titleService.getShortTitle() : this.titleService.getTitle();
    try  {
      const user = this._jwtStorageService.getUsername();
      const role = this._jwtStorageService.getRole();

      if (isNotNullOrUndefined(user) || isNotNullOrUndefined(role)) {
        this.userRole = this._jwtStorageService.getRole();
        this.userName = this._jwtStorageService.getUsername();
      }
    } catch (e) {}
  }

  ngAfterContentChecked(): void {
    this.title = this.isMobile ? this.titleService.getShortTitle() : this.titleService.getTitle();
  }

  // reset userName and userRole to null and remove the token
  logout(): void {
    this.userName = null;
    this.userRole = null;
    this._jwtStorageService.remove();
  }

  ngOnDestroy(): void {
    this.mobileQuery.removeEventListener('change', this._mobileQueryListener);
  }

  //Triggers every times windows is resized
  onResize(event) {
    this.checkSize(event.target.innerWidth);
  }

  //Define columns number of cards grid
  checkSize(size: number): void {
    if (size < 768) {
      this.isMobile = true;
    } else {
      this.isMobile = false;
    }
  }
}
