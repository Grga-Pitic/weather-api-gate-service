package com.pet.main.api.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.pet.main.api.factory.WeatherClientFactory;
import com.pet.main.api.service.client.base.WeatherClientType;

@RestController
public class ApiController {
	
	@Autowired
	private WeatherClientFactory clientFactory;
	
	@RequestMapping("/api/get")
	public Object getByCity(@RequestParam(name="type", required=true) String type) {
		try {
			return clientFactory.createClient(WeatherClientType.valueOf(type.toUpperCase())).getWeatherInfo();
		} catch (IllegalArgumentException e) {
			Map<String, String> body = new HashMap<String, String>();
			body.put("message", "Error: api type not found. Error: " + e.getMessage());
			return ResponseEntity
					.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.contentType(MediaType.APPLICATION_JSON)
					.body(body);
		} catch (IOException e) {
			Map<String, String> body = new HashMap<String, String>();
			body.put("message", "Getiing data error: " + e.getMessage());
			return ResponseEntity
					.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.contentType(MediaType.APPLICATION_JSON)
					.body(body);
		} 
	}	
}
