import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { AddAdminAuthorizationComponent } from './add-admin-authorization.component';

describe('AddAdminAuthorizationComponent', () => {
  let component: AddAdminAuthorizationComponent;
  let fixture: ComponentFixture<AddAdminAuthorizationComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ AddAdminAuthorizationComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(AddAdminAuthorizationComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
