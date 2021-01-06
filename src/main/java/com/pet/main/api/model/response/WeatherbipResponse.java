package com.pet.main.api.model.response;

import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class WeatherbipResponse {
	
	private List<Map<String, Object>> data;
	
	public List<Map<String, Object>> getData() {
		return data;
	}

}
