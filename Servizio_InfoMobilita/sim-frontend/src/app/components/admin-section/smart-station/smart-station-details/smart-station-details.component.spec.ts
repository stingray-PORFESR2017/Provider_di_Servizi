import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { SmartStationDetailsComponent } from './smart-station-details.component';

describe('SmartStationDetailsComponent', () => {
  let component: SmartStationDetailsComponent;
  let fixture: ComponentFixture<SmartStationDetailsComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ SmartStationDetailsComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(SmartStationDetailsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
