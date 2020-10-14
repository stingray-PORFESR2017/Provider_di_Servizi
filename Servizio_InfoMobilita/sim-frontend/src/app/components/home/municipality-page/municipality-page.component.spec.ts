import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { MunicipalityPageComponent } from './municipality-page.component';

describe('MunicipalityPageComponent', () => {
  let component: MunicipalityPageComponent;
  let fixture: ComponentFixture<MunicipalityPageComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ MunicipalityPageComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(MunicipalityPageComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
