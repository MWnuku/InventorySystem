import { Component } from '@angular/core';
import { MaterialModule } from '../shared/modules/material/material.module';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { RouterModule } from '@angular/router';
import { CommonModule } from '@angular/common';
import { AssetEditComponent } from '../asset-edit/asset-edit.component';
import { Asset } from '../common/models/asset';
import { AssetsService } from '../common/services/assets.service';

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

  constructor(private assetService: AssetsService) {}

  ngOnInit(): void {
    this.getAssets();
  }

  getAssets(): void {
    this.assetService.getAssets().subscribe((assets: Asset[]) => {
      this.assets = assets;
      console.log('asety', this.assets);
    });
  }

  get filteredAssets() {
    return this.assets.filter(asset =>
      (asset.name && asset.name.toLowerCase().includes(this.filterName.toLowerCase())) &&
      (asset.inventoryNumber && asset.inventoryNumber.toString().includes(this.filterInventoryNumber.toLowerCase())) &&
      (asset.room && asset.room.symbol.toLowerCase().includes(this.filterRoom.toLowerCase()))
    );
  }

  addAsset(): void {
    const newAsset: Asset = {
      id: this.assets.length + 1, // Temporary ID, adjust as needed
      person: undefined,          // Use `undefined` instead of `null`
      inventoryNumber: undefined,
      name: '',
      value: 0,
      date: new Date(),
      status: undefined,
      room: undefined,
      inventoryField: undefined,
      type: undefined
    };
    this.assetService.addAsset(newAsset).subscribe(
      (addedAsset) => {
        this.assets.push(addedAsset); // Add the returned asset with ID to the list
        this.selectedAsset = addedAsset;
        console.log('Asset added:', addedAsset);
      },
      (error) => {
        console.error('Error adding asset:', error);
      }
    );
  }


  deleteAsset(id: number): void {
    if (confirm('Are you sure you want to delete this asset?')) {
      this.assetService.deleteAsset(id).subscribe(() => {
        console.log(`Asset with id ${id} deleted`);
        this.assets = this.assets.filter(asset => asset.id !== id); // Update the UI after deletion
      });
    }
  }


  // Method to duplicate an asset
  duplicateAsset(asset: Asset): void {
    const duplicatedAsset: Asset = {
      ...asset,
      id: 0,                     // Set ID to 0 or omit it so the server can assign a new ID
      name: `${asset.name} (Kopia)`,
      date: new Date()
    };

    this.assetService.addAsset(duplicatedAsset).subscribe(
      (addedAsset) => {
        this.assets.push(addedAsset); // Add the returned asset with ID to the list
        console.log('Asset duplicated:', addedAsset);
      },
      (error) => {
        console.error('Error duplicating asset:', error);
      }
    );
  }



  editAsset(asset: Asset) {
    this.selectedAsset = asset;
  }
}
