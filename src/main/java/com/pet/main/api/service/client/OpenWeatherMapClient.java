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
import com.pet.main.api.repository.ApiRepository;
import com.pet.main.api.service.client.base.IWeatherClient;
import com.pet.main.api.service.client.base.WeatherClientType;

@Component
public class OpenWeatherMapClient implements IWeatherClient {
	
	public static final WeatherClientType type = WeatherClientType.OPEN_WEATHER_MAP;
	
	private static final String URL = 
			"http://api.openweathermap.org/data/2.5/weather";
	
	@Autowired
	private RestTemplateBuilder httpClientBuilder;
	
	@Autowired
	private ApiRepository apiRepository;
	
	@Override
	public WeatherInfo getWeatherInfo(String cityName) throws IOException {
		RestTemplate httpClient = httpClientBuilder.build();
		try {
			String token = apiRepository.findByName(type.toString()).get(0).getToken();
			String preparedUrl = URL + "?units=metric&lang=ru" + "&appid=" + token + "&q=" + cityName;
			
			OpenWeatherMapResponse response =
					httpClient.getForObject(preparedUrl, OpenWeatherMapResponse.class);
			
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
		} catch (IndexOutOfBoundsException e) {
			throw new IOException("Token for api '" + type.toString() + "' not found");
		}
	}

}
