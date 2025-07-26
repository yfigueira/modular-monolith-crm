import { ComponentFixture, TestBed } from '@angular/core/testing';

import { NewAccountContactFormComponent } from './new-account-contact-form.component';

describe('NewAccountContactFormComponent', () => {
  let component: NewAccountContactFormComponent;
  let fixture: ComponentFixture<NewAccountContactFormComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [NewAccountContactFormComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(NewAccountContactFormComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
