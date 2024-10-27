import { Component } from '@angular/core';
import {
  HttpClient
} from '@angular/common/http';

@Component({
  selector: 'app-home',
  standalone: true,
  imports: [],
  templateUrl: './acquisition.component.html',
  styleUrl: './acquisition.component.css'
})
export class AcquisitionComponent {
    acquisitions : any;

    constructor(private http:HttpClient) {
    }

    ngOnInit(){
      let response = this.http.get("http://localhost:8080/acquisition/");
      response.subscribe(response => this.acquisitions = response);
    }
}
