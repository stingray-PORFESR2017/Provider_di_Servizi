import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { HttpClientModule, HTTP_INTERCEPTORS } from '@angular/common/http';

import { MDBBootstrapModule } from 'angular-bootstrap-md';

// ANIMATION
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';

// MATERIAL DESIGN
import {MAT_DATE_LOCALE} from '@angular/material/core';

import { MatCardModule } from '@angular/material/card';
import { MatIconModule } from '@angular/material/icon';
import { MatInputModule } from '@angular/material/input';
import { MatListModule } from '@angular/material/list';
import { MatToolbarModule } from '@angular/material/toolbar';
import { MatSidenavModule } from '@angular/material/sidenav';
import {
  MatButtonModule, MatCheckboxModule, MatDatepickerModule, MatDialogModule, MatExpansionModule, MatGridListModule, MatNativeDateModule,
  MatOptionModule,
  MatPaginatorModule, MatProgressSpinnerModule,
  MatSelectModule,
  MatSlideToggleModule,
  MatSortModule,
  MatTableModule,
  MatSnackBarModule,
  MatButtonToggleModule
} from '@angular/material';

import { AppRoutingModule } from './app.routing';

import {FilterPipe} from './pipes/filter.pipe';

// COMPONENTS
import { AddAdminAuthorizationComponent } from './components/admin-section/admin-authorization/add-admin-authorization/add-admin-authorization.component';
import { AddInfomobilityFormComponent } from './components/admin-section/infomobility-service/add-infomobility-form/add-infomobility-form.component';
import { AddMunicipalityFormComponent } from './components/admin-section/municipality/add-municipality-form/add-municipality-form.component';
import { AddStationFormComponent } from './components/admin-section/smart-station/add-station-form/add-station-form.component';
import { AddUserFormComponent } from './components/admin-section/users/add-user-form/add-user-form.component';
import { AppComponent } from './components/app.component';
import { AdminAuthorizationComponent } from './components/admin-section/admin-authorization/admin-authorization.component';
import { AdminAuthorizationDetailsComponent } from './components/admin-section/admin-authorization/admin-authorization-details/admin-authorization-details.component';
import { DeleteDialogComponent } from './components/admin-section/delete-dialog/delete-dialog.component';
import { GoBackButtonComponent } from './components/utils/go-back-button/go-back-button.component';
import { HomeComponent } from './components/home/home.component';
import { InfomobilityServiceComponent } from './components/admin-section/infomobility-service/infomobility-service.component';
import { InfomobilityServiceDetailsComponent } from './components/admin-section/infomobility-service/infomobility-service-details/infomobility-service-details.component';
import { InfoTrainsPageComponent } from './components/home/info-trains-page/info-trains-page.component';
import { LoginFormComponent } from './components/login-form/login-form.component';
import { MainComponent } from './components/main/main.component';
import { MunicipalityComponent } from './components/admin-section/municipality/municipality.component';
import { MunicipalityDetailsComponent } from './components/admin-section/municipality/municipality-details/municipality-details.component';
import { MunicipalityPageComponent } from './components/home/municipality-page/municipality-page.component';
import { SmartStationComponent } from './components/admin-section/smart-station/smart-station.component';
import { SmartStationDetailsComponent } from './components/admin-section/smart-station/smart-station-details/smart-station-details.component';
import { SmartStationPageComponent } from './components/home/smart-station-page/smart-station-page.component';
import { UsersComponent } from './components/admin-section/users/users.component';
import { UserDetailsComponent } from './components/admin-section/users/user-details/user-details.component';


// SERVICES
import {AuthGuard} from './helpers/authguard';

import {AdminAuthorizationService} from './services/admin-authorization.service';
import {AuthenticationService} from './services/authentication.service';
import { InfomobilityServiceService } from './services/infomobility-service.service';
import { MunicipalityService } from './services/municipality.service';
import { SmartStationService } from './services/smart-station.service';
import { TokenInterceptorService } from './services/token-interceptor.service';
import { UsersService } from './services/users.service';
import { TitleComponent } from './components/utils/title/title.component';
import { AddButtonComponent } from './components/utils/add-button/add-button.component';
import { LatitudePipe } from './pipes/latitude.pipe';
import { LongitudePipe } from './pipes/longitude.pipe';
import { EmbeddedIframePageComponent } from './components/home/embedded-iframe-page/embedded-iframe-page.component';
import { MapComponent } from './components/utils/map/map.component';
import { InfoVehiclesPageComponent } from './components/home/info-vehicles-page/info-vehicles-page.component';
import { DatePipe } from '@angular/common';





@NgModule({
  declarations: [
    AdminAuthorizationComponent,
    AdminAuthorizationDetailsComponent,
    AppComponent,
    AddAdminAuthorizationComponent,
    AddInfomobilityFormComponent,
    AddMunicipalityFormComponent,
    AddStationFormComponent,
    AddUserFormComponent,
    DeleteDialogComponent,
    FilterPipe,
    GoBackButtonComponent,
    HomeComponent,
    InfomobilityServiceComponent,
    InfomobilityServiceDetailsComponent,
    InfoTrainsPageComponent,
    LoginFormComponent,
    MainComponent,
    MunicipalityComponent,
    MunicipalityDetailsComponent,
    MunicipalityPageComponent,
    SmartStationComponent,
    SmartStationDetailsComponent,
    SmartStationPageComponent,
    UsersComponent,
    UserDetailsComponent,
    TitleComponent,
    AddButtonComponent,
    LatitudePipe,
    LongitudePipe,
    EmbeddedIframePageComponent,
    MapComponent,
    InfoVehiclesPageComponent
  ],
  imports: [
    AppRoutingModule,
    BrowserModule,
    FormsModule,
    HttpClientModule,
    BrowserAnimationsModule,
    MatButtonModule,
    MatButtonToggleModule,
    MatCardModule,
    MatCheckboxModule,
    MatDatepickerModule,
    MatDialogModule,
    MatExpansionModule,
    MatGridListModule,
    MatIconModule,
    MatInputModule,
    MatListModule,
    MatNativeDateModule,
    MatOptionModule,
    MatPaginatorModule,
    MatProgressSpinnerModule,
    MatSelectModule,
    MatSidenavModule,
    MatSlideToggleModule,
    MatSnackBarModule,
    MatSortModule,
    MatTableModule,
    MatToolbarModule,
    MDBBootstrapModule.forRoot(),
    ReactiveFormsModule
  ],
  entryComponents: [
    DeleteDialogComponent
  ],
  providers: [
    AdminAuthorizationService,
    AuthenticationService,
    AuthGuard,
    InfomobilityServiceService,
    MatDatepickerModule,  // Is this really needed here?
    MunicipalityService,
    SmartStationService,
    UsersService,
    DatePipe,
    LatitudePipe,
    LongitudePipe,
    {
      provide: HTTP_INTERCEPTORS,
      useClass: TokenInterceptorService,
      multi: true
    },
    {
      provide: MAT_DATE_LOCALE,
      useValue: 'en-GB'
    }
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
