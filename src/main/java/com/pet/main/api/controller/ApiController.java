package com.pet.main.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.pet.main.api.factory.WeatherClientFactory;
import com.pet.main.api.service.client.base.WeatherClientType;

@RestController
public class ApiController {
	
	@Autowired
	private WeatherClientFactory clientFactory;
	
	@RequestMapping("/api/hello")
	public String hello(@RequestParam(name="type", required=true) String type) {
		try {
			return clientFactory.createClient(WeatherClientType.valueOf(type.toUpperCase())).getMessage();
		} catch (IllegalArgumentException e) {
			return "Error: api type not found";
		}
		
	}
	
}
