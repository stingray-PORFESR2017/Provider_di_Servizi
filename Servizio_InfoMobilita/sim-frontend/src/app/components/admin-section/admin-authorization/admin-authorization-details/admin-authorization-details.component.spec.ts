import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { AdminAuthorizationDetailsComponent } from './admin-authorization-details.component';

describe('AdminAuthorizationDetailsComponent', () => {
  let component: AdminAuthorizationDetailsComponent;
  let fixture: ComponentFixture<AdminAuthorizationDetailsComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ AdminAuthorizationDetailsComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(AdminAuthorizationDetailsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
