import { Component } from '@angular/core';
import {Router} from '@angular/router';
import {
  InventoryFieldService
} from '../common/services/inventory-field.service';
import {
  MatCard,
  MatCardContent,
  MatCardHeader,
  MatCardModule
} from '@angular/material/card';
import {
  MatCell,
  MatCellDef,
  MatColumnDef,
  MatHeaderCell,
  MatHeaderCellDef,
  MatHeaderRow,
  MatHeaderRowDef, MatRow,
  MatRowDef,
  MatTable, MatTableModule
} from '@angular/material/table';
import {
  MatButton, MatButtonModule
} from '@angular/material/button';
import {
  MatIconModule
} from '@angular/material/icon';
import {
  MatInputModule
} from '@angular/material/input';
import {
  MatFormFieldModule
} from '@angular/material/form-field';

@Component({
  selector: 'app-inventory-field',
  standalone: true,
  imports: [
    MatCardModule,
    MatFormFieldModule,
    MatInputModule,
    MatIconModule,
    MatButtonModule,
    MatTableModule,
    MatCardContent,
    MatCardHeader,
    MatCard,
    MatTable,
    MatHeaderCell,
    MatColumnDef,
    MatCell,
    MatHeaderCellDef,
    MatCellDef,
    MatButton,
    MatHeaderRow,
    MatHeaderRowDef,
    MatRowDef,
    MatRow
  ],
  templateUrl: './inventory-field.component.html',
  styleUrl: './inventory-field.component.css'
})
export class InventoryFieldComponent {
  inventoryFields: any[] = [];
  displayedColumns: string[] = ['id', 'number', 'person', 'action'];

  constructor(
    private inventoryFieldService: InventoryFieldService,
    private router: Router
  ) {}

  ngOnInit(): void {
    this.fetchInventoryFields();
  }

  fetchInventoryFields(): void {
    this.inventoryFieldService.getInventoryFields().subscribe(fields => {
      this.inventoryFields = fields;
    });
  }

  viewAssets(inventoryFieldId: number): void {
    this.router.navigate(['/assets']);
  }
}
