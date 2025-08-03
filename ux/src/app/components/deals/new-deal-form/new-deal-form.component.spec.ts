import { ComponentFixture, TestBed } from '@angular/core/testing';

import { NewDealFormComponent } from './new-deal-form.component';

describe('NewDealFormComponent', () => {
  let component: NewDealFormComponent;
  let fixture: ComponentFixture<NewDealFormComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [NewDealFormComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(NewDealFormComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
