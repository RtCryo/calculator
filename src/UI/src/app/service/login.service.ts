import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { environment } from 'src/environments/environment';

@Injectable({
  providedIn: 'root'
})
export class LoginService {

  constructor(private http: HttpClient) { }

  login(username: string, password: string){
    return this.http.get(environment.hostUrl + '/login', 
    { headers: { authorization: this.createBasicAuthToken(username, password)}}
    )
  }

  createBasicAuthToken(username: string, password: string) {
    return 'Basic ' + window.btoa(username + ":" + password);
  }

  getUser(){
    return this.http.get(environment.hostUrl + '/login');
  }

}
