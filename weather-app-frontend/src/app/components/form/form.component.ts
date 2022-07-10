import { Component, OnInit } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import { Router } from '@angular/router';

interface Weather {
  weather: [
    {
      id: string,
      main: string,
      description: string,
      icon: string
    }
  ]
  main: {
    
    temp: string;
    feels_like: string;
    pressure: string;
    temp_max: string;
    temp_min: string;
    humidity: string;
    
  }

}

@Component({
  selector: 'app-form',
  templateUrl: './form.component.html',
  styleUrls: ['./form.component.css']
})
export class FormComponent implements OnInit {
  city: string = "";
  date:Date=new Date();
  todayNumber:number=Date.now();
  weather: Weather | undefined;
  formCity:string="";
  bool:boolean=false;

  constructor(
    private http: HttpClient
  ) { }

  ngOnInit(): void {
  }

  submit() {
    //this.http.get("http://localhost:8083/api/weather/" + this.city).subscribe((response:any)=>{
      //console.log(response);
     //this.weather = response;
    //})
      this.formCity=this.city;
      this.bool=!this.bool;
  }
}
