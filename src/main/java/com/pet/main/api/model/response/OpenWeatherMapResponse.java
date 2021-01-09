package com.pet.main.api.model.response;

import java.util.Map;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.pet.main.api.model.response.base.IWeatherInfo;
import com.pet.main.api.model.response.base.WeatherInfo;
import com.pet.main.api.model.response.deserializer.OpenWeatherMapResponseDeserializer;

@JsonDeserialize(using = OpenWeatherMapResponseDeserializer.class)
public class OpenWeatherMapResponse extends WeatherInfo implements IWeatherInfo {

	public OpenWeatherMapResponse(Map<String, String> data) {
		super(data);
	}

}
