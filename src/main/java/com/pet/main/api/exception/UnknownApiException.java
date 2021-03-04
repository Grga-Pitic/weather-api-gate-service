package com.pet.main.api.exception;

public class UnknownApiException extends WeatherApiException {

    public UnknownApiException() {
        super("Unknown api type");
    }

    public UnknownApiException(String message) {
        super(message);
    }

}
