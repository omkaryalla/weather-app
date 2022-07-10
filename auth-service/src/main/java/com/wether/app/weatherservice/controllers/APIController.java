package com.wether.app.weatherservice.controllers;



import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication

@RestController
public class APIController {

  

    @GetMapping("/api/weather/{city}")
    public Object getJson(@PathVariable String city) {

        RestTemplate restTemplate = new RestTemplate();
       // HttpHeaders headers = new HttpHeaders();

         String apiKey="be53678b2752672d094331f36b07d8b2";
        ResponseEntity<Object> response = restTemplate.
                getForEntity("https://api.openweathermap.org/data/2.5/weather?q=" + city + "&units=metric&APPID=" + apiKey,
                Object.class);

        return response;
    }

    @GetMapping("/api/forecast/{city}")
    public Object getForecast(@PathVariable String city) {

        RestTemplate restTemplate = new RestTemplate();
       // HttpHeaders headers = new HttpHeaders();

         String apiKey="be53678b2752672d094331f36b07d8b2";
        ResponseEntity<Object> response = restTemplate.
                getForEntity("https://api.openweathermap.org/data/2.5/forecast?q=" + city + "&units=metric&APPID=" + apiKey,
                Object.class);

        return response;
    }
}
