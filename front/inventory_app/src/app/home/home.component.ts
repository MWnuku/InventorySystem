import {Component} from '@angular/core';
import {
  MaterialModule
} from '../shared/modules/material/material.module';
import {
  FormsModule,
  ReactiveFormsModule
} from '@angular/forms';
import {
  RouterModule
} from '@angular/router';
import {
  CommonModule
} from '@angular/common';
import {
  AssetEditComponent
} from '../asset-edit/asset-edit.component';
import {
  Asset
} from '../common/models/asset';
import {
  AssetsService
} from '../common/services/assets.service';
import {
  Person
} from '../common/models/person';
import {
  AssetStatus
} from '../common/models/asset_status';
import {
  TypeEnum
} from '../common/enumes/typeEnum';
import {
  InventoryField
} from '../common/models/inventory-field';

@Component({
  selector: 'app-home',
  standalone: true,
  imports: [MaterialModule, ReactiveFormsModule, RouterModule, CommonModule, FormsModule, AssetEditComponent],
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent {
  displayedColumns: string[] = [
    'id', 'inventoryField', 'name', 'inventoryNumber', 'person',
    'value', 'date', 'status', 'room', 'type', 'adnotations', 'action'
  ];

  assets: Asset[] = [];
  filterSymbol: string = '';
  filterName: string = '';
  filterInventoryNumber: string = '';
  filterRoom: string = '';
  selectedAsset: Asset | null = null;
  isEditing: boolean = false;
  persons: Person[] = []; // List of persons fetched from the backend
  inventoryFields: InventoryField[] = [];

  constructor(private assetService: AssetsService) {}

  ngOnInit(): void {
    this.getAssets();
    this.getPersons(); // Fetch persons for dropdown
    this.getInventoryFields();
  }

  getInventoryFields(): void {
    console.log('Inventory Fields for Dropdown:', this.inventoryFields);

    this.assetService.getInventoryFields().subscribe(
      (fields: InventoryField[]) => {
        this.inventoryFields = fields; // Store the fetched fields
        console.log('Fetched Inventory Fields:', this.inventoryFields); // Debug log
      },
      (error) => console.error('Error fetching inventory fields:', error)
    );
  }



  getPersons(): void {
    this.assetService.getPersons().subscribe((persons: Person[]) => {
      this.persons = persons;
    });
  }

  get filteredAssets() {
    return this.assets.filter(asset => {
      const nameMatch = asset.name?.toLowerCase().includes(this.filterName.toLowerCase());
      const inventoryMatch = asset.inventoryNumber?.toString().includes(this.filterInventoryNumber.toLowerCase());
      const roomMatch = asset.room?.symbol?.toLowerCase().includes(this.filterRoom.toLowerCase());
      return nameMatch || inventoryMatch || roomMatch;
    });
  }

  getAssets(): void {
    this.assetService.getAssets().subscribe(
      (assets: Asset[]) => {
        this.assets = assets; // Populate the assets array
        console.log('Assets fetched:', this.assets); // Debug log
      },
      (error) => console.error('Error fetching assets:', error)
    );
  }


  addAsset(): void {
    const newAsset: Asset = {
      id: 0,
      name: '',
      inventoryNumber: undefined, // Use undefined for optional number fields
      value: 0,
      date: new Date(),
      adnotations: '',
      status: AssetStatus.Active, // Use enum for status
      room: {
        id: 0,
        asset: {} as Asset, // Provide default or placeholder asset
        building: '',
        symbol: '',
        dateFrom: new Date(),
        dateTo: null, // Allow null for dateTo
      },
      person: { id: null }, // Use undefined for optional fields
      inventoryField: { id: null },
      type: TypeEnum.Computer, // Allow null for type
    };

    this.editAsset(newAsset); // Reuse the edit logic
  }

  editAsset(asset: Asset): void {
    this.selectedAsset = asset;
    this.isEditing = true;
  }

  onEditClose(): void {
    this.isEditing = false;
    this.selectedAsset = null;
    this.getAssets();
  }

  duplicateAsset(asset: Asset): void {
    const duplicatedAsset: Asset = {
      ...asset,
      id: 0, // Backend should assign a new ID
      name: `${asset.name} (Copy)`,
      date: new Date(),
    };
    this.editAsset(duplicatedAsset); // Reuse the edit logic to open the form
    this.isEditing = true;
  }

  deleteAsset(id: number): void {
    if (confirm('Are you sure you want to delete this asset?')) {
      this.assetService.deleteAsset(id).subscribe(() => {
        console.log(`Asset with id ${id} deleted`);
        this.assets = this.assets.filter(asset => asset.id !== id); // Update the UI after deletion
      });
    }
  }
}
