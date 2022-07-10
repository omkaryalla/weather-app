package com.wether.app.weatherservice;


    import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.server.ConfigurableWebServerFactory;
import org.springframework.boot.web.server.WebServerFactoryCustomizer;

import java.util.Collections;

@SpringBootApplication
public class WeatherServiceApplication implements WebServerFactoryCustomizer<ConfigurableWebServerFactory>{

	public static void main(String[] args) {
		//SpringApplication.run(WeatherServiceApplication.class, args);
		SpringApplication app = new SpringApplication(WeatherServiceApplication.class);
        app.setDefaultProperties(Collections
          .singletonMap("server.port", "8083"));
        app.run(args);
		
	}

	@Override
	public void customize(ConfigurableWebServerFactory factory) {
		// TODO Auto-generated method stub
		factory.setPort(8083);
	}

}

