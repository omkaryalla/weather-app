package com.favouriteservice;


    import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.server.ConfigurableWebServerFactory;
import org.springframework.boot.web.server.WebServerFactoryCustomizer;

import java.util.Collections;

@SpringBootApplication
public class FavouriteApi implements WebServerFactoryCustomizer<ConfigurableWebServerFactory>{

	public static void main(String[] args) {
		//SpringApplication.run(WeatherServiceApplication.class, args);
		SpringApplication app = new SpringApplication(FavouriteApi.class);
        app.setDefaultProperties(Collections
          .singletonMap("server.port", "8086"));
        app.run(args);
		
	}

	@Override
	public void customize(ConfigurableWebServerFactory factory) {
		// TODO Auto-generated method stub
		factory.setPort(8086);
	}
}
