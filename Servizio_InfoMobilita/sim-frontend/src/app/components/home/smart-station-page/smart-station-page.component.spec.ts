import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { SmartStationPageComponent } from './smart-station-page.component';

describe('SmartStationPageComponent', () => {
  let component: SmartStationPageComponent;
  let fixture: ComponentFixture<SmartStationPageComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ SmartStationPageComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(SmartStationPageComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
