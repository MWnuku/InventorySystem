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

  constructor() { }
}
