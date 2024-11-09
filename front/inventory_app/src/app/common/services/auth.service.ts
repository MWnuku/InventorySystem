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

@Injectable({
  providedIn: 'root'
})
export class AuthService {

  private url = environment.apiUrl + '/auth';
  private loggedInStatus = new BehaviorSubject<boolean>(this.hasToken());

  constructor(private http: HttpClient) { }
  login(credentials: { username: string; password: string }): Observable<any> {
    const headers = new HttpHeaders({
      'Content-Type': 'application/json; charset=utf-8'
    })
    return this.http.post<any>(`${this.url}/login`, credentials, {headers}).pipe(
      tap((response: any) => {

        sessionStorage.setItem('access_token', response.token);
        sessionStorage.setItem('user_role', response.role);
        sessionStorage.setItem('userId', response.userId);
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
}
