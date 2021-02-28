package com.pet.main.api.service.client.error.handler;

import org.springframework.http.client.ClientHttpResponse;
import org.springframework.web.client.*;

import java.io.IOException;

/**
 * Custom error handler for Weatherbit API
 */
public class WeatherbitErrorHandler extends DefaultResponseErrorHandler implements ResponseErrorHandler {

    @Override
    public boolean hasError(ClientHttpResponse clientHttpResponse) throws IOException {
        if (clientHttpResponse.getStatusCode().value() != 200) {
            return true;
        }
        return false;
    }

    @Override
    public void handleError(ClientHttpResponse clientHttpResponse) throws IOException {

        if (clientHttpResponse.getStatusCode().is5xxServerError()) {
            throw new HttpServerErrorException(clientHttpResponse.getStatusCode(), "Error on server side");
        }

        switch (clientHttpResponse.getStatusCode().value()) {
            case 204:
                throw new HttpClientErrorException(clientHttpResponse.getStatusCode(),
                        "There's no results",
                        getResponseBody(clientHttpResponse),
                        getCharset(clientHttpResponse));
            case 403:
                throw new HttpClientErrorException(clientHttpResponse.getStatusCode(),
                        "Unautorized",
                        getResponseBody(clientHttpResponse),
                        getCharset(clientHttpResponse));
            case 404:
                throw new HttpClientErrorException(clientHttpResponse.getStatusCode(),
                        "Page not found",
                        getResponseBody(clientHttpResponse),
                        getCharset(clientHttpResponse));
            default:
                throw new UnknownHttpStatusCodeException(clientHttpResponse.getRawStatusCode(),
                        "Unknown status code",
                        clientHttpResponse.getHeaders(),
                        getResponseBody(clientHttpResponse),
                        this.getCharset(clientHttpResponse));
        }
    }
}
