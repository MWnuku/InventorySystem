import {
  Injectable
} from '@angular/core';
import {environment} from '../../environment/environment';
import {Observable, tap} from 'rxjs';
import {
  HttpClient
} from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class AuthService {

  private url = environment.apiUrl + '/auth';

  constructor(private http: HttpClient ) { }

  login(credentials: { email: string; password: string }): Observable<any> {
    return this.http.post<any>(`${this.url}/login`, credentials).pipe(
      tap((response: any) => {
        // localStorage.setItem('access_token', response.token);
        // localStorage.setItem('user_role', response.role);
        // localStorage.setItem('userId', response.$id);
        sessionStorage.setItem('access_token', response.token);
        sessionStorage.setItem('user_role', response.role);
        sessionStorage.setItem('userId', response.userId);

      })
    );
  }
}
