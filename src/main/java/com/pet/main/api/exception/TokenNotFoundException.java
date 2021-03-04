package com.pet.main.api.exception;

public class TokenNotFoundException extends WeatherApiException {

    public TokenNotFoundException() {
        super("Token not found");
    }

    public TokenNotFoundException(String message) {
        super(message);
    }
}
