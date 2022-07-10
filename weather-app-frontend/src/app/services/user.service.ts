import { Injectable } from '@angular/core';
import { of } from 'rxjs';
import { delay } from 'rxjs/operators';
import { User } from '../models/user';

@Injectable({
  providedIn: 'root'
})
export class UserService {

  private user: User = new User("", "", "", []);

  constructor() { }

  isLoggedIn() {
    console.log(this.user);
    if(this.user.token == "") {
      return of(true).pipe(delay(500));
    }
    return of(false).pipe(delay(500));
  }

  login(user: User) {
    this.user = user;
  }

  logout() {
    this.user = new User("", "", "", []);
  }

  getUser() {
    return this.user;
  }
}
