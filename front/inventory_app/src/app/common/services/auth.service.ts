import {
  inject,
  Injectable
} from '@angular/core';
import {environment} from '../../environment/environment';
import {
  BehaviorSubject,
  Observable,
  tap
} from 'rxjs';
import {
  HttpClient, HttpHeaders
} from '@angular/common/http';
import {Router} from '@angular/router';

@Injectable({
  providedIn: 'root'
})
export class AuthService {

  private url = environment.apiUrl + '/auth';
   loggedInStatus = new BehaviorSubject<boolean>(this.hasToken());

  constructor(private http: HttpClient,
              private router: Router) { }
  login(credentials: { username: string; password: string }): Observable<any> {
    const headers = new HttpHeaders({
      'Content-Type': 'application/json; charset=utf-8'
    })
    return this.http.post<any>(`${this.url}/login`, credentials, {headers}).pipe(
      tap((response: any) => {


        sessionStorage.setItem('access_token', response.token);

        const decodedToken = this.decodeJwtToken(response.token);
        sessionStorage.setItem('user_role', decodedToken.role);
        sessionStorage.setItem('userId', decodedToken.id);

        this.loggedInStatus.next(true);
      })
    );
  }
  get isLoggedIn(): Observable<boolean> {
    return this.loggedInStatus.asObservable();
  }
  public hasToken(): boolean {
    return !!sessionStorage.getItem('access_token');
  }
  logout(): void {
    sessionStorage.removeItem('access_token');
    sessionStorage.removeItem('user_role');
    this.loggedInStatus.next(false); // Update login status
    this.router.navigate(['/login']);
  }
  getLoggedInUserId(): number | null {
    const userId = sessionStorage.getItem('userId');
    return userId ? +userId : null; // Return userId as a number or null if not found
  }

  private decodeJwtToken(token: string): any {
    const parts = token.split('.');
    if (parts.length !== 3) {
      throw new Error('Invalid JWT token');
    }

    const payloadBase64 = parts[1];

    const normalizedBase64 = payloadBase64.replace(/-/g, '+').replace(/_/g, '/');

    const decodedPayload = atob(normalizedBase64);

    return JSON.parse(decodedPayload);
  }
}
