package com.pet.main.api.service.client;

import com.pet.main.api.service.client.base.IWeatherClient;

public class TestWeatherClient implements IWeatherClient {

	@Override
	public String getMessage() {
		return "test";
	}

}
