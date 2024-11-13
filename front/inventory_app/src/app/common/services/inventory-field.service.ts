import { Injectable } from '@angular/core';
import {
  HttpClient, HttpHeaders
} from '@angular/common/http';
import {Observable} from 'rxjs';
import {
  environment
} from '../../environment/environment';

@Injectable({
  providedIn: 'root'
})
export class InventoryFieldService {

  private baseUrl = environment.apiUrl + '/field/';

  constructor(private http: HttpClient) {}

  private getHeaders(): HttpHeaders {
    const token = sessionStorage.getItem('access_token');
    return new HttpHeaders({
      'Authorization': `Bearer ${token}`
    });
  }

  getInventoryFields(): Observable<any[]> {
    return this.http.get<any[]>(this.baseUrl, {headers: this.getHeaders()});
  }

}
