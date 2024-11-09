import { Injectable } from '@angular/core';
import {Observable, tap} from 'rxjs';
import {
  HttpClient,
  HttpHeaders
} from '@angular/common/http';
import {
  environment
} from '../../environment/environment';
@Injectable({
  providedIn: 'root'
})
export class AssetsService {

  constructor(private http: HttpClient) { }
  private url = environment.apiUrl;

  private getHeaders(): HttpHeaders {
    const token = sessionStorage.getItem('access_token');
    return new HttpHeaders({
      'Authorization': `Bearer ${token}`
    });
  }

  getAssets(): Observable<any[]> {
    return this.http.get<any[]>(`${this.url}/asset`, { headers: this.getHeaders() });
  }
}
