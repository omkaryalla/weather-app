import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { retry } from 'rxjs';
import { Observable } from 'rxjs';
import { User } from '../models/user';

const url = 'http://localhost:8000/api/v1/users/';
const httpOptions = {
  headers: new HttpHeaders({
    'Content-Type': 'application/json'
  }),
};

@Injectable({
  providedIn: 'root'
})
export class UserHttpService {

  constructor(private http: HttpClient) { }

  register(user: User):Observable<User> {
    return this.http.post<User>(url, user, httpOptions)
    .pipe(retry(1));
  }

  login(user: User): Observable<User> {
    return this.http.post<User>(url + 'login', user, httpOptions)
    .pipe(retry(1));
  }

  updateUser(userName:string, user: User): Observable<User> {
    console.log("user");
    return this.http.post<User>(url + userName, user, httpOptions)
    .pipe(retry(1));
  }
}
