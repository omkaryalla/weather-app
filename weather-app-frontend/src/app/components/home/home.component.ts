import { Component, OnInit } from '@angular/core';
import { UserService } from 'src/app/services/user.service';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit {

  user:string = 'Login';

  constructor(private userService: UserService) {
    if (this.userService.getUser().token != "") {
      this.user = this.userService.getUser().userName;
    }
  }

  ngOnInit(): void {
  }

}
