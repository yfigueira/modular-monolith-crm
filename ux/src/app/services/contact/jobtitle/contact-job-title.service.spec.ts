import { TestBed } from '@angular/core/testing';

import { ContactJobTitleService } from './contact-job-title.service';

describe('ContactJobTitleService', () => {
  let service: ContactJobTitleService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(ContactJobTitleService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
