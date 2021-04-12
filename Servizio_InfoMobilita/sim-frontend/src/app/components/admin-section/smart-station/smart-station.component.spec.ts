import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { SmartStationComponent } from './smart-station.component';

describe('SmartStationComponent', () => {
  let component: SmartStationComponent;
  let fixture: ComponentFixture<SmartStationComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ SmartStationComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(SmartStationComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
