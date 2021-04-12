import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { AddInfomobilityFormComponent } from './add-infomobility-form.component';

describe('AddInfomobilityFormComponent', () => {
  let component: AddInfomobilityFormComponent;
  let fixture: ComponentFixture<AddInfomobilityFormComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ AddInfomobilityFormComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(AddInfomobilityFormComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
