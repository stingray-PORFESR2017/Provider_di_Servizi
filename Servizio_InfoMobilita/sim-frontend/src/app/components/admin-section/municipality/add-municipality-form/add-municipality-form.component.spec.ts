import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { AddMunicipalityFormComponent } from './add-municipality-form.component';

describe('AddMunicipalityFormComponent', () => {
  let component: AddMunicipalityFormComponent;
  let fixture: ComponentFixture<AddMunicipalityFormComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ AddMunicipalityFormComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(AddMunicipalityFormComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
