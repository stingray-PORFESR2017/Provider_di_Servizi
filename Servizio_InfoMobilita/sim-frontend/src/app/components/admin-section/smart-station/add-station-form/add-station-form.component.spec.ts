import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { AddStationFormComponent } from './add-station-form.component';

describe('AddStationFormComponent', () => {
  let component: AddStationFormComponent;
  let fixture: ComponentFixture<AddStationFormComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ AddStationFormComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(AddStationFormComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
