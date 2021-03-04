package com.pet.main.api.service.client.base;

import java.io.IOException;

import com.pet.main.api.exception.WeatherApiException;
import com.pet.main.api.model.response.base.IWeatherInfo;

/**
 * Client for getting data from different weather api.
 */
public interface IWeatherClient {

    /**
     * Returns weather info by city name.
     * @param cityName
     * @return
     * @throws WeatherApiException
     */
    IWeatherInfo getWeatherInfo(String cityName) throws WeatherApiException;

}
