import { Component, Input, OnInit } from '@angular/core';
import { ForecastDetails } from 'src/app/models/forecast-details';

@Component({
  selector: 'app-weather-card',
  templateUrl: './weather-card.component.html',
  styleUrls: ['./weather-card.component.css']
})
export class WeatherCardComponent implements OnInit {

  @Input() weather:ForecastDetails | undefined;

  constructor() { }

  ngOnInit(): void {
  }

}
