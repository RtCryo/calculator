/* import { Injectable } from '@angular/core';
import { ActivatedRouteSnapshot, CanActivate, RouterStateSnapshot, UrlTree, Router } from '@angular/router';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class AuthGuard implements CanActivate {

  constructor(
    private router: Router,
    //private authenticationService: AuthenticationService
  ) { }

  canActivate(
    route: ActivatedRouteSnapshot,
    state: RouterStateSnapshot) {
      const currentUser = this.authenticationService.currentUserValue;

      if (currentUser) {
        // logged in so return true
        return true;
      }

      this.router.navigate(['/login'], { queryParams: { returnUrl: state.url } });

      return false;
  }
  
}
 */