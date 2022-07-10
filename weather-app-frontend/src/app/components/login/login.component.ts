import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { User } from 'src/app/models/user';
import { UserHttpService } from 'src/app/services/user-http.service';
import { UserService } from 'src/app/services/user.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {
  userName: string = "";
  password: string = "";

  constructor(
          private userHttp: UserHttpService, 
          private userService: UserService,
          private router: Router) { }

  ngOnInit(): void {
  }

  onSubmit() {
    let user = new User(this.userName, this.password, "", []);
      this.userHttp.login(user).subscribe(
        data => {
          this.userService.login(
            new User(this.userName, this.password, data.token, [])
          );
          //change the route to home
          this.router.navigate(['/home']);
        }
      );
  }

}
