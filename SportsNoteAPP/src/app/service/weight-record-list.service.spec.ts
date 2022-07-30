import { TestBed } from '@angular/core/testing';

import { WeightRecordListService } from './weight-record-list.service';

describe('WeightRecordListService', () => {
  let service: WeightRecordListService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(WeightRecordListService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
