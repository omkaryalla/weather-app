import { Component, Input } from '@angular/core';
import { ForecastService } from 'src/app/services/forecast.service';
import { MatIconModule } from '@angular/material/icon';
import { DomSanitizer } from "@angular/platform-browser";
import { ForecastData } from 'src/app/models/forecast-data';
import { ForecastDetails } from 'src/app/models/forecast-details';
import { HttpClient } from '@angular/common/http';


@Component({
  selector: 'app-forecast',
  templateUrl: './forecast.component.html',
  styleUrls: ['./forecast.component.css']
})
export class ForecastComponent  {
  

  @Input() city:string = "";
  forecastData : ForecastData=new ForecastData();
  constructor(//private forecastService: ForecastService,
    //private iconRegistry: MatIconRegistry,
    private iconModule:MatIconModule,
    private http:HttpClient,
    private sanitizer: DomSanitizer) { }

  ngOnInit(): void {
    this.loadForecastWeather();
  }

  loadForecastWeather() {
    console.log("loadForecastWeather");
    this.http.get<any>("http://localhost:8083/api/forecast/" + this.city).subscribe(
   res => {
    console.log(res);
            this.forecastData = new ForecastData();//Instance to store the Data of ForecastModel
            this.forecastData.name = res.city.name;
        for(var i=7; i<res.list.length;i=i+8)//Since we want for 5 days. it Jumps 8 times to get to next day.(A day had 8 details in API.)
        {
          //Instance of type ForecastDetails and stores the data in it.
          var details = new ForecastDetails();
              details.date = res.list[i].dt_txt;
              details.maxTemperature = res.list[i].main.temp_max;
              details.minTemperature = res.list[i].main.temp_min;
              details.description = res.list[i].weather[0].description;
              details.icon = res.list[i].weather[0].icon;
              this.forecastData.details.push(details);//Pushing the data to the to created object

        }
        
   }
 )
}

}
