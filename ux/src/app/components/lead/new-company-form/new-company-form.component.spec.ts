import { ComponentFixture, TestBed } from '@angular/core/testing';

import { NewCompanyFormComponent } from './new-company-form.component';

describe('NewCompanyFormComponent', () => {
  let component: NewCompanyFormComponent;
  let fixture: ComponentFixture<NewCompanyFormComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [NewCompanyFormComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(NewCompanyFormComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
