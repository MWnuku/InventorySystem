import { Component, EventEmitter, Input, OnInit, Output } from '@angular/core';
import { FormBuilder, FormGroup, ReactiveFormsModule, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { NgForOf, CommonModule } from '@angular/common';
import { AssetsService } from '../common/services/assets.service';
import { AuthService } from '../common/services/auth.service';
import { InventoryFieldService } from '../common/services/inventory-field.service';
import { format } from 'date-fns';
import { Person } from '../common/models/person';
import { AssetStatus } from '../common/models/asset_status';
import { TypeEnum } from '../common/enumes/typeEnum';
import { InventoryField } from '../common/models/inventory-field';
import { Room } from '../common/models/room';
import { Asset } from '../common/models/asset';
import { MatCardModule } from '@angular/material/card';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatInputModule } from '@angular/material/input';
import { MatSelectModule } from '@angular/material/select';
import { MatButtonModule } from '@angular/material/button';
import { MatOptionModule } from '@angular/material/core';

@Component({
  selector: 'app-asset-edit',
  templateUrl: './asset-edit.component.html',
  styleUrls: ['./asset-edit.component.css'],
  standalone: true,
  imports: [
    CommonModule,
    ReactiveFormsModule,
    MatCardModule,
    MatFormFieldModule,
    MatInputModule,
    MatSelectModule,
    MatButtonModule,
    MatOptionModule,
    NgForOf,
  ],
})
export class AssetEditComponent implements OnInit {
  @Input() asset: any;
  @Output() close = new EventEmitter<void>();

  persons: Person[] = [];
  typeOptions = Object.values(TypeEnum);
  statusOptions = Object.values(AssetStatus);
  inventoryFields: InventoryField[] = [];
  rooms: Room[] = [];
  currentUser: number | null | undefined;
  selectedInventoryFieldId!: number;

  assetForm: FormGroup;

  constructor(
    private fb: FormBuilder,
    private assetService: AssetsService,
    private authService: AuthService,
    private router: Router
  ) {
    const today = format(new Date(), 'dd-MM-yyyy');
    this.assetForm = this.fb.group({
      inventoryNumber: [null, Validators.required],
      name: [null, Validators.required],
      acquisitionDate: [today, Validators.required],
      inventoryFieldId: [{ value: null, disabled: true }, Validators.required],
      personId: [{ value: null, disabled: true }, Validators.required],
      roomId: [null, Validators.required],
      adnotations: [null],
      value: [null],
      status: [AssetStatus.Active, Validators.required],
      type: [null],
    });
  }


  ngOnInit(): void {
    this.selectedInventoryFieldId = Number(sessionStorage.getItem('selectedInventoryFieldId'));
    this.currentUser = this.authService.getLoggedInUserId();
    this.assetForm.patchValue({
      personId: this.currentUser,
    });

    if (this.asset) {
      this.assetForm.patchValue({
        inventoryNumber: this.asset.inventoryNumber,
        name: this.asset.name,
        acquisitionDate: this.asset.date ? format(new Date(this.asset.date), 'dd-MM-yyyy') : '',
        adnotations: this.asset.adnotations,
        value: this.asset.value,
        status: this.asset.status,
        type: this.asset.type,
        roomId: this.asset.room?.id,
        inventoryFieldId: this.asset.inventoryField?.id,
      });
    }

    this.getInventoryFields();
    this.getPersons();
    this.getRooms();
  }


  getRooms(): void {
    this.assetService.getRooms().subscribe(
      (rooms: Room[]) => {
        this.rooms = rooms;
      },
      (error) => console.error('Error fetching rooms:', error)
    );
  }

  getPersons(): void {
    this.assetService.getPersons().subscribe((persons: Person[]) => {
      this.persons = persons;
    });
  }

  getInventoryFields(): void {
    this.assetService.getInventoryFields().subscribe(
      (fields: InventoryField[]) => {
        this.inventoryFields = fields;
      },
      (error) => console.error('Error fetching inventory fields:', error)
    );
  }

  saveAsset(): void {
    if (this.assetForm.valid) {
      const formData = this.assetForm.value;
      const assetPayload: Asset = {
        id: this.asset?.id || null,
        person: { id: this.currentUser },
        inventoryNumber: formData.inventoryNumber,
        name: formData.name,
        date: formData.acquisitionDate,
        adnotations: formData.adnotations,
        value: formData.value,
        status: formData.status,
        type: formData.type,
        room: this.rooms.find(room => room.id === formData.roomId),
        inventoryField: { id: this.selectedInventoryFieldId },
      };
      if (this.asset?.id) {
        this.assetService.updateAsset(assetPayload).subscribe(
          () => this.close.emit(),
          (error) => console.error('Error updating asset:', error)
        );
      } else {
        this.assetService.addAsset(assetPayload).subscribe(
          () => this.close.emit(),
          (error) => console.error('Error adding asset:', error)
        );
      }
    } else {
      console.error('Form is invalid:', this.assetForm);
    }
  }

  goBack(): void {
    this.router.navigate(['/assets']);
  }

  cancel(): void {
    this.close.emit();
  }
}
