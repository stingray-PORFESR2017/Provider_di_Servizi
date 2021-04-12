import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { EmbeddedIframePageComponent } from './embedded-iframe-page.component';

describe('EmbeddedIframePageComponent', () => {
  let component: EmbeddedIframePageComponent;
  let fixture: ComponentFixture<EmbeddedIframePageComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ EmbeddedIframePageComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(EmbeddedIframePageComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
