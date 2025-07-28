import { ComponentFixture, TestBed } from '@angular/core/testing';

import { NewActivityFormComponent } from './new-activity-form.component';

describe('NewActivityFormComponent', () => {
  let component: NewActivityFormComponent;
  let fixture: ComponentFixture<NewActivityFormComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [NewActivityFormComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(NewActivityFormComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
