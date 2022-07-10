import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { HttpClient } from '@angular/common/http';


@Injectable({
  providedIn: 'root'
})
export class ForecastService {

  constructor(private http: HttpClient) { }

  LoadForecastWeather(city: string):Observable<any> {
    return this.http.get("https://localhost:8000/api/forecast/"+city);
  }

  /*LoadCurrentWeather(zip: any): Observable<any> {
    return this.http.get("https://api.openweathermap.org/data/2.5/weather?zip="+zip+",us&APPID=dabc2b57d81c4493c08ab63bb4d9e326&units=imperial" );
  }*/

}
