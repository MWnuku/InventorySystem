import { Injectable } from '@angular/core';
import {Observable} from 'rxjs';
import {
  environment
} from '../../environment/environment';
import {
  HttpClient
} from '@angular/common/http';
import {Router} from '@angular/router';

@Injectable({
  providedIn: 'root'
})
export class PersonService {

  private url = environment.apiUrl + '/person';

  constructor(private http: HttpClient,
              private router: Router) { }

  register(data: { firstName: string; lastName: string; email: string; password: string }): Observable<any> {
    return this.http.post<any>(`${this.url}/register`, data);
  }
}
