import {
  Component,
  Input
} from '@angular/core';
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

@Component({
  selector: 'app-asset-edit',
  standalone: true,
  imports: [MaterialModule, ReactiveFormsModule, RouterModule, CommonModule, FormsModule],
  templateUrl: './asset-edit.component.html',
  styleUrl: './asset-edit.component.css'
})
export class AssetEditComponent {
  @Input() asset: any;

  saveAsset() {
    console.log('Zapisano asset:', this.asset);
  }
}
