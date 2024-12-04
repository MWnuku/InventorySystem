import { bootstrapApplication } from '@angular/platform-browser';
import { appConfig } from './app/app.config';
import { AppComponent } from './app/app.component';
import {
  provideHttpClient, withInterceptors
} from '@angular/common/http';
import {
  provideRouter
} from '@angular/router';
import {routes} from './app/app.routes';
import {
  provideAnimations
} from '@angular/platform-browser/animations';
import {
  AuthGuard
} from './app/core/guard/auth.guard';

bootstrapApplication(AppComponent,{
  providers: [
    provideHttpClient(),
    provideRouter(routes),
    provideAnimations(),
    {provide: AuthGuard, useClass: AuthGuard},
  ]}
)
  .catch((err) => console.error(err));

