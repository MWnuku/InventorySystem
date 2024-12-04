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

  private baseUrl = environment.apiUrl + '/field/person/';

  constructor(private http: HttpClient) {}

  private getHeaders(): HttpHeaders {
    const token = sessionStorage.getItem('access_token');
    return new HttpHeaders({
      'Authorization': `Bearer ${token}`
    });
  }

  getInventoryFields(): Observable<any[]> {
    return this.http.get<any[]>(this.baseUrl + this.getCurrentUser(), {headers: this.getHeaders()});
  }

  getCurrentInventoryField(): number | null{
    const selected =  sessionStorage.getItem('selectedInventoryFieldId');
    return selected ? +selected : null;
  }

  getCurrentUser(): number | null{
    const selected = sessionStorage.getItem('userId');
    return selected ? +selected : null;
  }
}
