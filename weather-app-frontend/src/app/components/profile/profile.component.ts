import { Component, OnInit } from '@angular/core';
import { User } from 'src/app/models/user';
import { UserHttpService } from 'src/app/services/user-http.service';
import { UserService } from 'src/app/services/user.service';

@Component({
  selector: 'app-profile',
  templateUrl: './profile.component.html',
  styleUrls: ['./profile.component.css']
})
export class ProfileComponent implements OnInit {
  user: User = new User("", "", "", []);
  userName: string = "";
  password: string = "";
  oldPassword: string = "";
  newPassword: string = "";
  confirmPassword: string = "";

  constructor(
    private userService: UserService,
    private userHttp: UserHttpService
    ) {
    this.user = userService.getUser();
   }

  ngOnInit(): void {
  }

  userNameUpdate(){
    if(this.userName != ""){
      if(this.userName != this.user.userName){
        if(this.password == this.user.password){
          let user = new User(this.userName, this.user.password, "", this.user.watchList);
          this.userHttp.updateUser(this.user.userName, user).subscribe(
            data => {
              this.userHttp.login(user).subscribe(
                data => {
                  this.userService.login(
                    new User(this.userName, this.user.password, data.token, this.user.watchList)
                  );
                  this.user = this.userService.getUser();
                }
              );
            }
          );
        }
      }
    }
  }

  passwordUpdate(){
    if(this.oldPassword == this.user.password){
      if(this.newPassword == this.confirmPassword){
        let user = new User(this.user.userName, this.newPassword, "", this.user.watchList);
        this.userHttp.updateUser(this.user.userName, user).subscribe(
          data => {
            this.userHttp.login(user).subscribe(
              data => {
                this.userService.login(
                  new User(this.user.userName, this.newPassword, data.token, this.user.watchList)
                );
                this.user = this.userService.getUser();
              }
            );
          }
        );
      }
    }
  }

}
