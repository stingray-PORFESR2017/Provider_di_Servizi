import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { InfoTrainsPageComponent } from './info-trains-page.component';

describe('InfoTrainsPageComponent', () => {
  let component: InfoTrainsPageComponent;
  let fixture: ComponentFixture<InfoTrainsPageComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ InfoTrainsPageComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(InfoTrainsPageComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
