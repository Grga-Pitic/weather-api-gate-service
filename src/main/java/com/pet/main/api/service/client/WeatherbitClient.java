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
import com.pet.main.api.model.response.WeatherbipResponse;
import com.pet.main.api.repository.ApiRepository;
import com.pet.main.api.service.client.base.IWeatherClient;
import com.pet.main.api.service.client.base.WeatherClientType;

@Component
public class WeatherbitClient implements IWeatherClient {

	public static final WeatherClientType type = WeatherClientType.WEATHERBIT;
	
	private static final String URL = 
			"http://api.weatherbit.io/v2.0/current?lang=ru";
	
	@Autowired
	private RestTemplateBuilder httpClientBuilder;
	
	@Autowired
	private ApiRepository apiRepository;
	
	@Override
	public WeatherInfo getWeatherInfo(String cityName) throws IOException {
		RestTemplate httpClient = httpClientBuilder.build();
		try {
			String token = apiRepository.findByName(type.toString()).get(0).getToken();
			String preparedUrl = URL + "&key=" + token + "&city=" + cityName;
			
			WeatherbipResponse response =
					httpClient.getForObject(preparedUrl, WeatherbipResponse.class);
			
			Map<String, String> data = new HashMap<String, String>();
			
			// TODO move to response model
			data.put("name", (String) response.getData().get(0).get("city_name"));
			data.put("temperature", Double.toString(((double) response.getData().get(0).get("temp"))));
			data.put("feelsLike", Double.toString((double) response.getData().get(0).get("app_temp")));
			data.put("pressure", Double.toString((double) response.getData().get(0).get("pres")));
			data.put("weatherDescription", (String)((Map<String, Object>) response.getData().get(0).get("weather")).get("description"));
			data.put("windSpeed", Double.toString((double) response.getData().get(0).get("wind_spd")));
			
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
