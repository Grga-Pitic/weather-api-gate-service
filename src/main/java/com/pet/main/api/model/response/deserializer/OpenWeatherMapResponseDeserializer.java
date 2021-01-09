package com.pet.main.api.model.response.deserializer;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.ObjectCodec;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import com.pet.main.api.model.response.OpenWeatherMapResponse;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class OpenWeatherMapResponseDeserializer extends JsonDeserializer<OpenWeatherMapResponse> {

    @Override
    public OpenWeatherMapResponse deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JsonProcessingException {
        ObjectCodec oc = jsonParser.getCodec();
        JsonNode node = oc.readTree(jsonParser);

        Map<String, String> data = new HashMap<String, String>();

        data.put("name", node.get("name").asText());
        data.put("temperature", node.get("main").get("temp").asText());
        data.put("feelsLike", node.get("main").get("feels_like").asText());
        data.put("pressure", node.get("main").get("pressure").asText());
        data.put("weatherDescription", node.get("weather").get(0).get("description").asText());
        data.put("windSpeed", node.get("wind").get("speed").asText());

        return new OpenWeatherMapResponse(data);
    }
}
