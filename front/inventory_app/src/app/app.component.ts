import { Component } from '@angular/core';
import {
  Router,
  RouterModule,
  RouterOutlet
} from '@angular/router';
import {HttpClientModule} from '@angular/common/http';
import {
  MaterialModule
} from './shared/modules/material/material.module';
import {
  FormsModule,
  ReactiveFormsModule
} from '@angular/forms';
import {
  CommonModule
} from '@angular/common';
import {
  AssetEditComponent
} from './asset-edit/asset-edit.component';
import {
  AuthService
} from './common/services/auth.service';
import {Subscription} from 'rxjs';

@Component({
  selector: 'app-root',
  standalone: true,
  imports: [RouterOutlet, HttpClientModule, MaterialModule, ReactiveFormsModule, RouterModule, CommonModule, FormsModule, AssetEditComponent],
  templateUrl: './app.component.html',
  styleUrl: './app.component.css'
})
export class AppComponent {
  title = 'inventory_app';
  isLoggedIn = false;
  private loginStatusSubscription: Subscription;
  constructor(private authService: AuthService,
              private router: Router,) {
    this.loginStatusSubscription = this.authService.isLoggedIn.subscribe((status: boolean) => {
      this.isLoggedIn = status;
    });
  }

  logout() {
    this.authService.logout();
    this.router.navigate(['/login']);
    this.isLoggedIn = false;
  }
  ngOnDestroy(){
    if (this.loginStatusSubscription) {
      this.loginStatusSubscription.unsubscribe();
    }
  }
}
