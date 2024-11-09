import { Component, Input, OnInit } from '@angular/core';
import {
  FormBuilder,
  FormGroup,
  Validators,
  FormArray,
  ReactiveFormsModule, FormsModule
} from '@angular/forms';
import {AssetsService} from '../common/services/assets.service';
import {
  MaterialModule
} from '../shared/modules/material/material.module';
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
  styleUrls: ['./asset-edit.component.css']
})
export class AssetEditComponent implements OnInit {
  @Input() asset: any;
  assetForm: FormGroup;

  constructor(private fb: FormBuilder, private assetService: AssetsService) {
    this.assetForm = this.fb.group({
      symbol: ['', [Validators.required]],
      name: ['', [Validators.required]],
      acquisitionDate: ['', [Validators.required]],
      adnotations: [''],
      status: ['', [Validators.required]],
      rooms: this.fb.array([]),
      inventoryField: ['', [Validators.required]],
      type: [''],
    });
  }

  ngOnInit(): void {

    this.assetForm = this.fb.group({
      symbol: [this.asset?.symbol, [Validators.required]],
      name: [this.asset?.name, [Validators.required]],
      acquisitionDate: [this.asset?.acquisitions[0]?.date, [Validators.required]],
      adnotations: [this.asset?.adnotations],
      status: [this.asset?.status, [Validators.required]],
      rooms: this.fb.array(this.asset?.rooms?.map((room: any) => this.fb.group({
        name: [room?.name, [Validators.required]],
      }))),
      inventoryField: [this.asset?.inventoryField, [Validators.required]],
      type: [this.asset?.type],
    });
  }

  // Dynamically get form array of rooms
  get rooms() {
    return (this.assetForm.get('rooms') as FormArray);
  }

  saveAsset() {
    if (this.assetForm.valid) {
      // Zapisz dane z formularza
      console.log('Zapisano asset:', this.assetForm.value);

      // Wysłanie danych na backend (przykład z serwisem do aktualizacji)
      this.assetService.updateAsset(this.assetForm.value).subscribe(
        (updatedAsset) => {
          console.log('Zaktualizowano asset:', updatedAsset);
          // Możesz dodać odpowiednią logikę po zapisaniu (np. komunikat o sukcesie)
        },
        (error) => {
          console.error('Błąd przy zapisywaniu assetu:', error);
          // Możesz dodać odpowiednią logikę po błędzie
        }
      );
    }
  }
}
