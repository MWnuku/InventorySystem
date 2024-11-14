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
@Injectable({
  providedIn: 'root'
})
export class AssetsService {

  private url = environment.apiUrl;

  constructor(private http: HttpClient) {}

  private getHeaders(): HttpHeaders {
    const token = sessionStorage.getItem('access_token');
    return new HttpHeaders({
      'Authorization': `Bearer ${token}`
    });
  }

  // Fetch all assets
  getAssets(): Observable<Asset[]> {
    return this.http.get<Asset[]>(`${this.url}/asset/`, { headers: this.getHeaders() });
  }

  // Update an existing asset
  updateAsset(asset: EditAsset): Observable<EditAsset> {
    return this.http.post<EditAsset>(`${this.url}/update`, asset, { headers: this.getHeaders() });
  }

  // Add a new asset
  addAsset(asset: Asset): Observable<Asset> {
    return this.http.post<Asset>(`${this.url}/asset/`, asset, { headers: this.getHeaders() }).pipe(
      tap(newAsset => console.log('Asset added:', newAsset))
    );
  }

  deleteAsset(id: number): Observable<void> {
    return this.http.delete<void>(`${this.url}/asset/${id}`, { headers: this.getHeaders() });
  }
}
