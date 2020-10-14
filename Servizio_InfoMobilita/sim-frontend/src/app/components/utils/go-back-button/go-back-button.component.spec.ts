import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { GoBackButtonComponent } from './go-back-button.component';

describe('GoBackButtonComponent', () => {
  let component: GoBackButtonComponent;
  let fixture: ComponentFixture<GoBackButtonComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ GoBackButtonComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(GoBackButtonComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
