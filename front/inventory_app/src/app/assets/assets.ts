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
  CommonModule, Location
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
  templateUrl: './assets.html',
  styleUrls: ['./assets.css']
})
export class Assets {
  displayedColumns: string[] = [
    'id', 'name', 'inventoryNumber',
    'value', 'date', 'status', 'room', 'type', 'adnotations', 'action'
  ];

  assets: Asset[] = [];
  filterSymbol: string = '';
  filterName: string = '';
  filterInventoryNumber: string = '';
  filterRoom: string = '';
  selectedAsset: Asset | null = null;
  isEditing: boolean = false;
  inventoryFields: InventoryField[] = [];

  constructor(private assetService: AssetsService, private location: Location) {}

  ngOnInit(): void {
    this.getAssets();
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

  get filteredAssets() {
    return this.assets.filter(asset => {
      const nameMatch = this.filterName
        ? asset.name?.toLowerCase().includes(this.filterName.toLowerCase())
        : true;

      const inventoryMatch = this.filterInventoryNumber
        ? asset.inventoryNumber?.toString().includes(this.filterInventoryNumber.toLowerCase())
        : true;

      const roomMatch = this.filterRoom
        ? asset.room?.symbol?.toLowerCase().includes(this.filterRoom.toLowerCase())
        : true;

      return nameMatch && inventoryMatch && roomMatch;
    });
  }


  getAssets(): void {
    this.assetService.getAssets().subscribe(
      (assets: Asset[]) => {
        this.assets = assets.map(asset => {
          if (typeof asset.date === 'string') {
            const [day, month, year] = asset.date.split('-').map(Number);
            asset.date = new Date(year, month - 1, day);
          }
          return asset;
        });
        console.log('Assets fetched and processed:', this.assets);
      },
      (error) => console.error('Error fetching assets:', error)
    );
  }

  addAsset(): void {
    const newAsset: Asset = {
      id: 0,
      name: '',
      inventoryNumber: undefined,
      value: 0,
      date: new Date(),
      adnotations: '',
      status: AssetStatus.Active,
      room: {
        id: 0,
        asset: {} as Asset,
        building: '',
        symbol: '',
        dateFrom: new Date(),
        dateTo: null,
      },
      inventoryField: { id: null },
      type: TypeEnum.Computer,
    };

    this.editAsset(newAsset);
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
      id: 0,
      name: `${asset.name} (copy)`,
      date: asset.date ? new Date(asset.date) : new Date(),
      type: asset.type,
      room: asset.room,
      inventoryField: asset.inventoryField,
      status: asset.status,
      value: asset.value,
      adnotations: asset.adnotations,
    };

    this.editAsset(duplicatedAsset);

    this.isEditing = true;
  }

  deleteAsset(id: number): void {
    if (confirm('Are you sure you want to delete this asset?')) {
      this.assetService.deleteAsset(id).subscribe(() => {
        console.log(`Asset with id ${id} deleted`);
        this.assets = this.assets.filter(asset => asset.id !== id);
      });
    }
  }
  goBack(): void {
    this.location.back();
  }
}
