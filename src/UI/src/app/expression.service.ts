import { Injectable } from '@angular/core';
import {
  HttpClient,
  HttpParams,
} from '@angular/common/http'
import { Expression } from './expression';
import { environment } from 'src/environments/environment';

@Injectable({
  providedIn: 'root'
})
export class ExpressionService {
  private apiServerUrl = environment.hostUrl;
  
  constructor(private http: HttpClient) { }

  public getExpressions(){
    return this.http.get(this.apiServerUrl + '/');
  }

}
