import { ComponentFixture, TestBed } from '@angular/core/testing';

import { InventoryFieldComponent } from './inventoryField.component';

describe('HomeComponent', () => {
  let component: InventoryFieldComponent;
  let fixture: ComponentFixture<InventoryFieldComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [InventoryFieldComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(InventoryFieldComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
