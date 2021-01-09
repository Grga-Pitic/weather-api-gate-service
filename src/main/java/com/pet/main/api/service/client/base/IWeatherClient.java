package com.pet.main.api.service.client.base;

import java.io.IOException;

import com.pet.main.api.model.WeatherInfo;

public interface IWeatherClient {

    WeatherInfo getWeatherInfo(String cityName) throws IOException;

}
