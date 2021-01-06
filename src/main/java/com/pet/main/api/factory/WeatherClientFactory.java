package com.pet.main.api.factory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import com.pet.main.api.service.client.OpenWeatherMapClient;
import com.pet.main.api.service.client.WeatherbitClient;
import com.pet.main.api.service.client.base.IWeatherClient;
import com.pet.main.api.service.client.base.WeatherClientType;

@Component
public class WeatherClientFactory {
	
	@Autowired
	private ApplicationContext context;
	
	public IWeatherClient createClient(WeatherClientType type) {
		switch (type) {
		case OPEN_WEATHER_MAP:
			return context.getBean(OpenWeatherMapClient.class);
		case WEATHERBIT:
			return context.getBean(WeatherbitClient.class);
			
		default:
			return context.getBean(OpenWeatherMapClient.class);
		}
		
	}
}
