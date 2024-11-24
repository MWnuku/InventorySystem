import {
  Component,
  EventEmitter,
  Input,
  OnInit, Output
} from '@angular/core';
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
import {
  EditAsset
} from '../common/models/asset-edit';
import {Observable} from 'rxjs';
import {
  Person
} from '../common/models/person';
import {
  Asset
} from '../common/models/asset';
import {
  AssetStatus
} from '../common/models/asset_status';
import {
  TypeEnum
} from '../common/enumes/typeEnum';
import {
  InventoryField
} from '../common/models/inventory-field';
import {
  Room
} from '../common/models/room';
import {
  AuthService
} from '../common/services/auth.service';
import {
  InventoryFieldService
} from '../common/services/inventory-field.service';

@Component({
  selector: 'app-asset-edit',
  standalone: true,
  imports: [MaterialModule, FormsModule, ReactiveFormsModule, RouterModule, CommonModule, FormsModule],
  templateUrl: './asset-edit.component.html',
  styleUrls: ['./asset-edit.component.css']
})
export class AssetEditComponent implements OnInit {
  @Input() asset: any;
  @Output() close = new EventEmitter<void>();
  // persons: Person[] = []; // List of persons fetched from the backendtypeOptions = Object.values(TypeEnum);
  typeOptions = Object.values(TypeEnum);
  statusOptions = Object.values(AssetStatus);
  inventoryFields: InventoryField[] =[];
  rooms: Room[] = [];
  currentUser: number | null | undefined;
  currentInventoryField: number | null | undefined;

  cancel(): void {
    this.close.emit();
  }

  assetForm: FormGroup;

  constructor(private fb: FormBuilder, private assetService: AssetsService, private authService: AuthService, private  inventoryService: InventoryFieldService) {
    this.assetForm = this.fb.group({
      inventoryNumber: [null, Validators.required],
      name: [null, Validators.required],
      acquisitionDate: [new Date().toISOString().split('T')[0], Validators.required],
      inventoryFieldId: [this.currentInventoryField, Validators.required],
      // personId: [this.currentUser, Validators.required],
      roomId: [null, Validators.required],
      adnotations: [null],
      value: [null],
      status: [AssetStatus.Active, Validators.required],
      type: [null],
    });


  }

  getRooms(): void {
    this.assetService.getRooms().subscribe(
      (rooms: Room[]) => {
        this.rooms = rooms;
        console.log('Fetched Rooms:', this.rooms);
      },
      (error) => console.error('Error fetching rooms:', error)
    );
  }


  ngOnInit(): void {
    if (this.asset) {
      // Editing: Pre-fill the form
      this.assetForm.patchValue({
        inventoryNumber: this.asset.inventoryNumber,
        name: this.asset.name,
        acquisitionDate: this.asset.date,
        adnotations: this.asset.adnotations,
        value: this.asset.value,
        status: this.asset.status,
        type: this.asset.type,
        // personId: this.asset.person?.id,
        roomId: this.asset.room?.id,
        inventoryFieldId: this.asset.inventoryField?.id,
      });
    }

    this.getInventoryFields();
    // this.getPersons();
    this.getRooms();
    this.currentUser = this.authService.getLoggedInUserId();
    this.currentInventoryField = this.inventoryService.getCurrentInventoryField();
  }



  // getPersons(): void {
  //   this.assetService.getPersons().subscribe((persons: Person[]) => {
  //     this.persons = persons;
  //   });
  // }

  getInventoryFields(): void {
    this.assetService.getInventoryFields().subscribe(
      (fields: InventoryField[]) => {
        this.inventoryFields = fields; // Store fields for the dropdown
        console.log('Fetched Inventory Fields:', this.inventoryFields);
      },
      (error) => console.error('Error fetching inventory fields:', error)
    );
  }

  saveAsset(): void {
    if (this.assetForm.valid) {
      const formData = this.assetForm.value;

      const selectedRoom = this.rooms.find(room => room.id === formData.roomId);
      const selectedInventoryField = this.inventoryFields.find(field => field.id === formData.inventoryFieldId);

      const assetPayload: Asset = {
        id: this.asset?.id || null, // If this is an edit, use the existing asset ID
        person: { id: formData.personId },
        inventoryNumber: formData.inventoryNumber,
        name: formData.name,
        date: formData.acquisitionDate,
        adnotations: formData.adnotations,
        value: formData.value,
        status: formData.status,
        type: formData.type,
        room: selectedRoom,
        inventoryField: {
          id: this.assetForm.value.inventoryFieldId,
        },
      };

      // Determine whether to call add or update endpoint
      if (this.asset?.id) {
        // Update asset
        this.assetService.updateAsset(assetPayload).subscribe(
          (updatedAsset) => {
            console.log('Asset updated successfully:', updatedAsset);
            this.close.emit(); // Notify parent component of success
          },
          (error) => console.error('Error updating asset:', error)
        );
      } else {
        // Add new asset
        this.assetService.addAsset(assetPayload).subscribe(
          (newAsset) => {
            console.log('Asset added successfully:', newAsset);
            this.close.emit(); // Notify parent component of success
          },
          (error) => console.error('Error adding asset:', error)
        );
      }
    } else {
      console.error('Form is invalid:', this.assetForm);
    }
  }

}
