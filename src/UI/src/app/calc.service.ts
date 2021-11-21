import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { environment } from 'src/environments/environment';
import { Expression } from '@angular/compiler';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class CalcService {
  private apiServerUrl = environment.apiBaseUrl;

  constructor(private http: HttpClient) { }

  public getExpressionList(): Observable<Expression[]> {
    return this.http.get<Expression[]>(`${this.apiServerUrl}/calc`);
  }

  public addEmployee(expression: Expression): Observable<Expression> {
    return this.http.post<Expression>(`${this.apiServerUrl}/employee/add`, expression);
  }

  public subTotal(expression: Expression): Observable<Expression> {
    return this.http.post<Expression>(`${this.apiServerUrl}/employee/add`, expression);
  }

}
