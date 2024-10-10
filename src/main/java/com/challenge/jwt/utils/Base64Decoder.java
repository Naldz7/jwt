package com.challenge.jwt.utils;

import com.google.gson.*;

import java.util.Base64;

public class Base64Decoder {

    static final String EMPTY_PAYLOAD = "{\"Role\":\"\",\"Seed\":\"\",\"Name\":\"\"}";

    public String decodePayload(String encodedData) {

        String payload = "";

        if (encodedData.isEmpty() && encodedData.isBlank()) {
            String errorDescription = String.format("ERROR:: Index out of bounds: encodedData: %s", encodedData.length());
            throw new ArrayIndexOutOfBoundsException(errorDescription);
        }

        try {
            payload = new String(Base64.getDecoder().decode(encodedData.split("\\.")[1]));
        } catch (Exception exception) {
            String errorDescription = String.format("ERROR:: %s", exception.getCause());
            throw new RuntimeException(exception);
        }

        return isStringJsonValid(payload) ?
                new String(Base64.getDecoder().decode(encodedData.split("\\.")[1])) :
                EMPTY_PAYLOAD;
    }

    private boolean isStringJsonValid(String payload) {

        try {
            if (!payload.isEmpty() && !payload.isBlank()) {
                JsonElement jsonElement = JsonParser.parseString(payload);
                Gson gson = new GsonBuilder().setPrettyPrinting().create();
            }
        } catch (JsonSyntaxException ex) {
            return false;
        }
        return true;
    }
}