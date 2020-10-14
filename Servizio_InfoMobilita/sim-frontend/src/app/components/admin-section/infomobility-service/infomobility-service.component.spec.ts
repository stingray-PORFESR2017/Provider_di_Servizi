import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { InfomobilityServiceComponent } from './infomobility-service.component';

describe('InfomobilityServiceComponent', () => {
  let component: InfomobilityServiceComponent;
  let fixture: ComponentFixture<InfomobilityServiceComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ InfomobilityServiceComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(InfomobilityServiceComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
