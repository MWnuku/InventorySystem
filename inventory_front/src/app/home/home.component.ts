import { Component } from '@angular/core';
import {
  MatFormFieldModule
} from '@angular/material/form-field';
import {
  MatSelectModule
} from '@angular/material/select';
import {
  MatInputModule
} from '@angular/material/input';
import {
  MatIcon
} from '@angular/material/icon';

@Component({
  selector: 'app-home',
  standalone: true,
  imports: [MatFormFieldModule, MatInputModule, MatSelectModule, MatIcon],
  templateUrl: './home.component.html',
  styleUrl: './home.component.css',
})
export class HomeComponent {

}
