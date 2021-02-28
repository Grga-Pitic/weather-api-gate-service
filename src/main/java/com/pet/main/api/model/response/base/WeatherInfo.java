package com.pet.main.api.model.response.base;

import java.util.Map;


public class WeatherInfo implements IWeatherInfo {

    private static final String[] FIELDS_NAMES = {
            "name",
            "temperature",
            "feelsLike",
            "pressure",
            "weatherDescription",
            "windSpeed",
    };

    private String name;

    private String temperature;

    private String feelsLike;

    private String pressure;

    private String weatherDescription;

    private String windSpeed;

    /**
     *
     * @param data - must contains keys "name", "temperature", "feelsLike", "pressure", "weatherDescription", "windSpeed"
     */
    public WeatherInfo(Map<String, String> data) {
        validate(data);

        name               = data.get("name");
        temperature        = data.get("temperature");
        feelsLike          = data.get("feelsLike");
        pressure           = data.get("pressure");
        weatherDescription = data.get("weatherDescription");
        windSpeed          = data.get("windSpeed");
    }

    private void validate(Map<String, String> data) {

        if (data.isEmpty()) {
            throw new IllegalArgumentException("data is empty");
        }

        if (data.size() < FIELDS_NAMES.length) {
            throw new IllegalArgumentException("too few data");
        }

        for (String fieldName : FIELDS_NAMES) {
            if (!data.keySet().contains(fieldName)) {
                throw new IllegalArgumentException("not found field  '" + fieldName + "'");
            }
        }
    }

    public String getName() {
        return name;
    }

    public String getTemperature() {
        return temperature;
    }

    public String getFeelsLike() {
        return feelsLike;
    }

    public String getPressure() {
        return pressure;
    }

    public String getWeatherDescription() {
        return weatherDescription;
    }

    public String getWindSpeed() {
        return windSpeed;
    }

}
