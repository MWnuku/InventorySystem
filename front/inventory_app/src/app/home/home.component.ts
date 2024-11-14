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
    'id', 'inventoryField', 'name', 'inventoryNumber', 'person', 'status',
    'value', 'date', 'room', 'type', 'adnotations', 'action'
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
      asset.symbol.toLowerCase().includes(this.filterSymbol.toLowerCase()) &&
      asset.name.toLowerCase().includes(this.filterName.toLowerCase()) &&
      asset.inventoryNumber.toString().includes(this.filterInventoryNumber.toLowerCase()) &&
      asset.room.symbol.toLowerCase().includes(this.filterRoom.toLowerCase())
    );
  }

  editAsset(asset: Asset) {
    this.selectedAsset = asset;
  }
}
