package com.pet.main.api.service.client;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.client.UnknownHttpStatusCodeException;

import com.pet.main.api.model.WeatherInfo;
import com.pet.main.api.model.response.OpenWeatherMapResponse;
import com.pet.main.api.service.client.base.IWeatherClient;

@Component
public class OpenWeatherMapClient implements IWeatherClient {
	
	private static final String URL = 
			"http://api.openweathermap.org/data/2.5/weather?q=Smolensk&appid=14be897f3f9d2a00cb033f0ba82e9bcf 1&units=metric&lang=ru";
	
	@Autowired
	private RestTemplateBuilder httpClientBuilder;
	
	@Override
	public WeatherInfo getWeatherInfo() throws IOException {
		RestTemplate httpClient = httpClientBuilder.build();
		try {
			OpenWeatherMapResponse response =
					httpClient.getForObject(URL, OpenWeatherMapResponse.class);
			
			Map<String, String> data = new HashMap<String, String>();
			
			data.put("name", response.getName());
			data.put("temperature", response.getMain().get("temp"));
			data.put("feelsLike", response.getMain().get("feels_like"));
			data.put("pressure", response.getMain().get("pressure"));
			data.put("weatherDescription", response.getWeather().get(0).get("description"));
			data.put("windSpeed", response.getWind().get("speed"));
			
			return new WeatherInfo(data);
		} catch (HttpClientErrorException 
				| HttpServerErrorException 
				| UnknownHttpStatusCodeException e) {
			throw new IOException(e.getMessage());
		}
	}

}
