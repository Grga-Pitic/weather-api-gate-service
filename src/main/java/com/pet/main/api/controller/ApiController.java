package com.pet.main.api.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import com.pet.main.api.model.WeatherForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import com.pet.main.api.factory.WeatherClientFactory;
import com.pet.main.api.service.client.base.WeatherClientType;

@RestController
public class ApiController {

    @Autowired
    private WeatherClientFactory clientFactory;


    /**
     * Gets city name and API type. Returns weather info by city.
     * @param data - data from WeatherForm
     * @return WeatherInfo
     * @throws IllegalArgumentException
     * @throws IOException
     */
    @RequestMapping("/api/get")
    public Object getByCity(@ModelAttribute WeatherForm data) throws IllegalArgumentException, IOException {
        return clientFactory.createClient(WeatherClientType.valueOf(data.getType().toUpperCase())).getWeatherInfo(data.getCityName());
    }

    @ExceptionHandler(IllegalArgumentException.class)
    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
    public Map<String, String> handleIOException(IllegalArgumentException e) {
        Map<String, String> body = new HashMap<String, String>();
        body.put("message", "Getiing data error: " + e.getMessage());
        return body;
    }

    @ExceptionHandler(IOException.class)
    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
    public Map<String, String> handleIOException(IOException e) {
        Map<String, String> body = new HashMap<String, String>();
        body.put("message", "Getiing data error: " + e.getMessage());
        return body;
    }
}
