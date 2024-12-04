import { Injectable } from '@angular/core';
import {Observable, tap} from 'rxjs';
import {
  HttpClient,
  HttpHeaders
} from '@angular/common/http';
import {
  environment
} from '../../environment/environment';
import {
  EditAsset
} from '../models/asset-edit';
import {Asset} from '../models/asset';
import {Person} from '../models/person';
import {
  InventoryField
} from '../models/inventory-field';
import {Room} from '../models/room';
import {
  InventoryFieldService
} from './inventory-field.service';
@Injectable({
  providedIn: 'root'
})
export class AssetsService {

  private url = environment.apiUrl;

  constructor(private http: HttpClient, private inventoryFieldService: InventoryFieldService) {}

  private getHeaders(): HttpHeaders {
    const token = sessionStorage.getItem('access_token');
    return new HttpHeaders({
      'Authorization': `Bearer ${token}`,
      'content-type': 'application/json'
    });
  }

  // Fetch all assets
  getAssets(): Observable<Asset[]> {
    return this.http.get<Asset[]>(`${this.url}/asset/field/` + this.inventoryFieldService.getCurrentInventoryField(), { headers: this.getHeaders() });
  }

  addAsset(asset: Asset): Observable<Asset> {
    console.log('saveAsset called service');
    return this.http.post<Asset>(`${this.url}/asset/`, asset, { headers: this.getHeaders() }).pipe(
      tap((newAsset) => console.log('Asset added:', newAsset))
    );
  }

  getInventoryFields(): Observable<InventoryField[]> {
    return this.http.get<InventoryField[]>(`${this.url}/field/`, { headers: this.getHeaders() });
  }


  updateAsset(asset: Asset): Observable<Asset> {
    return this.http.post<Asset>(`${this.url}/asset/update`, asset, { headers: this.getHeaders() }).pipe(
      tap((updatedAsset) => console.log('Asset updated:', updatedAsset))
    );
  }

  deleteAsset(id: number): Observable<void> {
    return this.http.delete<void>(`${this.url}/asset/${id}`, { headers: this.getHeaders() }).pipe(
      tap(() => console.log(`Asset with ID ${id} deleted`))
    );
  }

  getPersons(): Observable<Person[]> {
    return this.http.get<Person[]>(`${this.url}/person/`, { headers: this.getHeaders() }).pipe(
      tap((persons) => console.log('Fetched persons:', persons))
    );
  }

  getRooms(): Observable<Room[]> {
    return this.http.get<Room[]>(`${this.url}/room/`, { headers: this.getHeaders() }).pipe(
      tap((rooms) => console.log('Fetched persons:', rooms))
    );
  }


}
