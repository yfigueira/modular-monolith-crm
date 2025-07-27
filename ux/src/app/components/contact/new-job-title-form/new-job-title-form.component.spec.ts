import { ComponentFixture, TestBed } from '@angular/core/testing';

import { NewJobTitleFormComponent } from './new-job-title-form.component';

describe('NewJobTitleFormComponent', () => {
  let component: NewJobTitleFormComponent;
  let fixture: ComponentFixture<NewJobTitleFormComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [NewJobTitleFormComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(NewJobTitleFormComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
