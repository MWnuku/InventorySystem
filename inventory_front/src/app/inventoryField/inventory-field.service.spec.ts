import { TestBed } from '@angular/core/testing';

import { InventoryFieldService } from './inventory-field.service';

describe('InventoryFieldService', () => {
  let service: InventoryFieldService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(InventoryFieldService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
