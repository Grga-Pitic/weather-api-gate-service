package com.pet.main.api.model.response;

import java.util.Map;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.pet.main.api.model.response.base.IWeatherInfo;
import com.pet.main.api.model.response.base.WeatherInfo;
import com.pet.main.api.model.response.deserializer.WeatherbitResponseDeserializer;

@JsonDeserialize(using = WeatherbitResponseDeserializer.class)
public class WeatherbipResponse extends WeatherInfo implements IWeatherInfo {

    public WeatherbipResponse(Map<String, String> data) {
        super(data);
    }

}
