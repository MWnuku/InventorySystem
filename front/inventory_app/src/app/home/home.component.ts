import { Component } from '@angular/core';
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
import {AssetEditComponent} from '../asset-edit/asset-edit.component';
import {
  Asset
} from '../common/models/asset';
import {
  AssetsService
} from '../common/services/assets.service';

@Component({
  selector: 'app-home',
  standalone: true,
  imports: [],
  templateUrl: './home.component.html',
  styleUrl: './home.component.css'
})
export class HomeComponent {

}
