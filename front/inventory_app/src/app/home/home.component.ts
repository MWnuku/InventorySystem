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
    'id', 'symbol', 'name', 'inventoryNumber', 'person', 'adnotations',
    'status', 'rooms', 'acquisitions', 'changes', 'deletions', 'inventoryField', 'type', 'action'
  ];
  assets: Asset[] = [];
  filterSymbol: string = '';
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
      asset.symbol.toLowerCase().includes(this.filterSymbol.toLowerCase())
    );
  }

  // Getter for the rooms
  getRooms(asset: Asset): string {
    return asset.rooms?.map(room => room.symbol).join(', ') || 'Brak danych';
  }

  editAsset(asset: Asset) {
    this.selectedAsset = asset;
  }
}
