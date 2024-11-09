import { Component } from '@angular/core';
import { ReactiveFormsModule, FormGroup, FormBuilder, Validators } from '@angular/forms';
import { MaterialModule } from '../shared/modules/material/material.module';
import {AuthService} from '../common/services/auth.service';
import { Router, RouterModule } from '@angular/router';
import {CommonModule} from '@angular/common';
import { HttpClient } from '@angular/common/http';

@Component({
  selector: 'app-login',
  standalone: true,
  imports: [MaterialModule, ReactiveFormsModule, RouterModule, CommonModule],
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent {
  loginForm: FormGroup;

  constructor(private fb: FormBuilder,
              private authService: AuthService,
              private router: Router,
              private http: HttpClient) {
    this.loginForm = this.fb.group({
      username: ['', [Validators.required, Validators.email]],
      password: ['', Validators.required]
    });
  }

  login() {
    if (this.loginForm.valid) {
      const credentials = this.loginForm.value;
      this.authService.login(credentials)
        .subscribe({
            next: (response) =>{
              this.router.navigate(['/'])
            },
            error: (error) =>{

            }
          }
        )
      console.log('Zalogowano pomyślnie', this.loginForm.value);

    } else {
      console.log('Formularz zawiera błędy');
    }
  }
}
