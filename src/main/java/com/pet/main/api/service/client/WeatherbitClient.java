package com.pet.main.api.service.client;

import java.io.IOException;

import com.pet.main.api.model.response.WeatherbipResponse;
import com.pet.main.api.model.response.base.IWeatherInfo;
import com.pet.main.api.service.client.error.handler.WeatherbitErrorHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.client.UnknownHttpStatusCodeException;

import com.pet.main.api.repository.ApiRepository;
import com.pet.main.api.service.client.base.IWeatherClient;
import com.pet.main.api.service.client.base.WeatherClientType;

@Component
public class WeatherbitClient implements IWeatherClient {

    public static final WeatherClientType type = WeatherClientType.WEATHERBIT;

    private static final String URL =
            "http://api.weatherbit.io/v2.0/current";

    @Autowired
    private RestTemplateBuilder httpClientBuilder;

    @Autowired
    private ApiRepository apiRepository;

    @Override
    public IWeatherInfo getWeatherInfo(String cityName) throws IOException {
        RestTemplate httpClient = httpClientBuilder
                .errorHandler(new WeatherbitErrorHandler())
                .build();
        try {
            String token = apiRepository.findByName(type.toString()).get(0).getToken();
            String preparedUrl = URL + "?lang=ru" + "&key=" + token + "&city=" + cityName;

            return httpClient.getForObject(preparedUrl, WeatherbipResponse.class);
        } catch (HttpClientErrorException
                | HttpServerErrorException
                | UnknownHttpStatusCodeException e) {
            throw new IOException(e.getMessage());
        } catch (IndexOutOfBoundsException e) {
            throw new IOException("Token for api '" + type.toString() + "' not found");
        }
    }
}
