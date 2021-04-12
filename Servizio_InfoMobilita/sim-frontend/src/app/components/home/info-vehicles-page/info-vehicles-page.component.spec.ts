import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { InfoVehiclesPageComponent } from './info-vehicles-page.component';

describe('InfoVehiclesPageComponent', () => {
  let component: InfoVehiclesPageComponent;
  let fixture: ComponentFixture<InfoVehiclesPageComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ InfoVehiclesPageComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(InfoVehiclesPageComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
