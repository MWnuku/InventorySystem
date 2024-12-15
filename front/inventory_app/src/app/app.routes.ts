import { Routes } from '@angular/router';
import {
  ProfileComponent
} from './profile/profile.component';
import {
  Assets
} from './assets/assets';
import {
  LoginComponent
} from './login/login.component';
import {AuthGuard} from './core/guard/auth.guard';
import {
  InventoryFieldComponent
} from './inventory_field/inventory-field.component';
import {
  RegisterComponent
} from './register/register.component';

export const routes: Routes = [
  { path: 'login', component: LoginComponent },
  { path: 'profile', component: ProfileComponent, canActivate: [AuthGuard] },
  { path: 'assets', component: Assets, canActivate: [AuthGuard] },
  { path: 'register', component: RegisterComponent },
  { path: '', component: InventoryFieldComponent, canActivate: [AuthGuard] },
  { path: '**', redirectTo: ''}
];
