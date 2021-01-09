package com.pet.main.api.model.response.deserializer;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.ObjectCodec;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import com.pet.main.api.model.response.WeatherbipResponse;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class WeatherbitResponseDeserializer extends JsonDeserializer<WeatherbipResponse> {

    @Override
    public WeatherbipResponse deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
        ObjectCodec oc = jsonParser.getCodec();
        JsonNode node = oc.readTree(jsonParser);

        Map<String, String> data = new HashMap<String, String>();

        data.put("name", node.get("data").get(0).get("city_name").asText());
        data.put("temperature", node.get("data").get(0).get("temp").asText());
        data.put("feelsLike", node.get("data").get(0).get("app_temp").asText());
        data.put("pressure", node.get("data").get(0).get("pres").asText());
        data.put("weatherDescription", node.get("data").get(0).get("weather").get("description").asText());
        data.put("windSpeed", node.get("data").get(0).get("wind_spd").asText());

        return new WeatherbipResponse(data);
    }
}
