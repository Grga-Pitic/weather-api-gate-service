package com.pet.main.api.model.response;

import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class OpenWeatherMapResponse {

	private String name;
	
	private Map<String, String> main;
	
	private Map<String, String> wind;
	
	private List<Map<String, String>> weather;
	
	public String getName() {
		return name;
	}

	public Map<String, String> getMain() {
		return main;
	}

	public Map<String, String> getWind() {
		return wind;
	}

	public List<Map<String, String>> getWeather() {
		return weather;
	}
	
}
