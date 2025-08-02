import { TestBed } from '@angular/core/testing';

import { LeadJobTitleService } from './lead-job-title.service';

describe('JobtitleService', () => {
  let service: LeadJobTitleService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(LeadJobTitleService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
