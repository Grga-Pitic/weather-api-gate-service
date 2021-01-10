package com.pet.main.api.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.pet.main.api.factory.WeatherClientFactory;
import com.pet.main.api.service.client.base.WeatherClientType;

@RestController
public class ApiController {

    @Autowired
    private WeatherClientFactory clientFactory;

    @RequestMapping("/api/get")
    public Object getByCity(@RequestParam(name="type", required=true) String type,
                            @RequestParam(name="cityName", required=true) String cityName) throws IllegalArgumentException, IOException {
        return clientFactory.createClient(WeatherClientType.valueOf(type.toUpperCase())).getWeatherInfo(cityName);
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
