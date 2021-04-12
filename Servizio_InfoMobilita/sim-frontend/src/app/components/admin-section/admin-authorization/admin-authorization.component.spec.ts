import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { AdminAuthorizationComponent } from './admin-authorization.component';

describe('AdminAuthorizationComponent', () => {
  let component: AdminAuthorizationComponent;
  let fixture: ComponentFixture<AdminAuthorizationComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ AdminAuthorizationComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(AdminAuthorizationComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
