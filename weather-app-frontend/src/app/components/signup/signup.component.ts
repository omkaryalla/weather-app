import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { User } from 'src/app/models/user';
import { UserHttpService } from 'src/app/services/user-http.service';
import { UserService } from 'src/app/services/user.service';

@Component({
  selector: 'app-signup',
  templateUrl: './signup.component.html',
  styleUrls: ['./signup.component.css']
})
export class SignupComponent implements OnInit {
  userName: string = "";
  password: string = "";
  confirmPassword: string = "";

  constructor(
    private userHttp: UserHttpService,
    private userService: UserService,
    private router: Router
    ) { }

  ngOnInit(): void {
  }

  passwordValid() {
    if(this.password == '' || this.confirmPassword == '') {
      return false;
    }
    return this.password == this.confirmPassword;
  }

  onSubmit() {
    if(this.passwordValid()) {
      let user = new User(this.userName, this.password, "", []);
      console.log(user)
      this.userHttp.register(user).subscribe(
        data => {
          console.log(data);
          this.userHttp.login(user).subscribe(
            data => {
              this.userService.login(
                new User(this.userName, this.password, data.token, [])
              );
              //change the route to home
              this.router.navigate(['/home']);
            })
        }
      );
    }
  }

}
