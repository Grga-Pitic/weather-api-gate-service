package com.pet.main.api.controller;

import java.util.HashMap;
import java.util.Map;

import com.pet.main.api.exception.WeatherApiException;
import com.pet.main.api.model.WeatherForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import com.pet.main.api.factory.WeatherClientFactory;
import com.pet.main.api.service.client.base.WeatherClientType;
import org.springframework.web.client.RestClientResponseException;

@RestController
public class ApiController {

    @Autowired
    private WeatherClientFactory clientFactory;


    /**
     * Gets city name and API type. Returns weather info by city.
     * @param data - data from WeatherForm
     * @return WeatherInfo
     * @throws RestClientResponseException
     * @throws WeatherApiException
     */
    @RequestMapping("/api/get")
    public Object getByCity(@ModelAttribute WeatherForm data) throws WeatherApiException, RestClientResponseException {
        return clientFactory.createClient(data.getType()).getWeatherInfo(data.getCityName());
    }

    @ExceptionHandler(RestClientResponseException.class)
    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
    public Map<String, String> handleIOException(RestClientResponseException e) {
        Map<String, String> body = new HashMap<String, String>();
        body.put("message", "Outer API http error: " + e.getMessage());
        return body;
    }

    @ExceptionHandler(WeatherApiException.class)
    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
    public Map<String, String> handleIOException(WeatherApiException e) {
        Map<String, String> body = new HashMap<String, String>();
        body.put("message", "Application error: " + e.getMessage());
        return body;
    }

}
