package com.pet.main.api.service.client;

import java.util.List;

import com.pet.main.api.exception.TokenNotFoundException;
import com.pet.main.api.exception.WeatherApiException;
import com.pet.main.api.model.Api;
import com.pet.main.api.model.response.base.IWeatherInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Component;
import org.springframework.web.client.*;

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
    public IWeatherInfo getWeatherInfo(String cityName) throws WeatherApiException {
        RestTemplate httpClient = httpClientBuilder.build();
        List<Api> tokenList = apiRepository.findByName(type.toString());

        if (tokenList.isEmpty()) {
            throw new TokenNotFoundException("Token for api '" + type.toString() + "' not found");
        }

        String token = tokenList.get(0).getToken();

        String preparedUrl = URL + "?units=metric&lang=ru" + "&appid=" + token + "&q=" + cityName;

        return httpClient.getForObject(preparedUrl, OpenWeatherMapResponse.class);
    }

}
