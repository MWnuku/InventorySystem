import { Routes } from '@angular/router';
import {
  ProfileComponent
} from './profile/profile.component';
import {
  HomeComponent
} from './home/home.component';
import {
  LoginComponent
} from './login/login.component';
import {AuthGuard} from './core/guard/auth.guard';

export const routes: Routes = [
  { path: 'login', component: LoginComponent },
  { path: 'profile', component: ProfileComponent, canActivate: [AuthGuard]},
  { path: '', component: HomeComponent, canActivate: [AuthGuard] },
];
