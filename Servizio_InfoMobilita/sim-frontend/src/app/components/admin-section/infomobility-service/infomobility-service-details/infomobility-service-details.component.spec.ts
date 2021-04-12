import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { InfomobilityServiceDetailsComponent } from './infomobility-service-details.component';

describe('InfomobilityServiceDetailsComponent', () => {
  let component: InfomobilityServiceDetailsComponent;
  let fixture: ComponentFixture<InfomobilityServiceDetailsComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ InfomobilityServiceDetailsComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(InfomobilityServiceDetailsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
