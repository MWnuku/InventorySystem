import {
  ActivatedRouteSnapshot, CanActivate,
  CanActivateFn,
  Router,
  RouterStateSnapshot
} from '@angular/router';
import {
  AuthService
} from '../../common/services/auth.service';

export class AuthGuard implements CanActivate {
  constructor(private authService: AuthService, private router: Router) {}

  canActivate(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): boolean {
    const roles = route.data['roles'] as Array<string>;
    const isLoggedIn = this.authService.hasToken();


    if (!isLoggedIn) {
      this.router.navigate(['/login']);
      return false;
    }



    return true;
  }
}

