package com.pet.main.api.factory;

import org.springframework.stereotype.Component;

import com.pet.main.api.service.client.TestWeatherClient;
import com.pet.main.api.service.client.base.IWeatherClient;
import com.pet.main.api.service.client.base.WeatherClientType;

@Component
public class WeatherClientFactory {
	public IWeatherClient createClient(WeatherClientType type) {
		switch (type) {
		case TEST:
			return new TestWeatherClient();

		default:
			return new TestWeatherClient();
		}
		
	}
}
