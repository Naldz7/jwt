package com.challenge.jwt.utils;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.List;
import java.util.regex.Pattern;
import java.util.stream.IntStream;

public class JwtValidator {

    public static Boolean validateToken(String decodedPayloadToken) {

        Type mapType = new TypeToken<HashMap<String, String>>() {}.getType();
        HashMap<String, String> decodedPayloadMap = new Gson().fromJson(decodedPayloadToken, mapType);

        return decodedPayloadMap.size() < 4 &&
                validateRole(decodedPayloadMap.get("Role")) &&
                validateSeed(decodedPayloadMap.get("Seed")) &&
                validateName(decodedPayloadMap.get("Name")) ? Boolean.TRUE : Boolean.FALSE;
    }

    private String extractName(String jwtPayloadToken) {
        String decodedPayloadToken = new Base64Decoder().decodePayload(jwtPayloadToken);

        Type mapType = new TypeToken<HashMap<String, String>>() {}.getType();
        HashMap<String, String> decodedPayloadMap = new Gson().fromJson(decodedPayloadToken, mapType);

        return decodedPayloadMap.get("Name");
    }

    private static boolean validateName(String name) {
        boolean nameContainsDigit = JwtValidator.hasOnlyLettersAndSymbols(name);
        return nameContainsDigit && JwtValidator.has256Characters(name);
    }

    private static boolean validateRole(String role) {
        return JwtValidator.isValidRole(role);
    }

    private static boolean validateSeed(String seed) {
        return JwtValidator.isPrimeNumber(Integer.parseInt(seed));
    }


    public static boolean hasOnlyLettersAndSymbols(String stringWithNumber) {
        if (stringWithNumber.isEmpty() && stringWithNumber.isBlank())
            return false;
        return Pattern.compile(
                "^[a-zA-Z\\p{Punct}\\sçÇáàãâéèêíìóòõôúùûüÁÀÃÂÉÈÊÍÌÓÒÕÔÚÙÛÜ]*$"
        ).matcher(stringWithNumber).matches();
    }

    public static boolean isPrimeNumber(int number) {

        int newNumber = number;

        boolean checkPrimeNum = isGreaterThanOne(number);
        number = newNumber / 2;

        if (checkPrimeNum || number <= 1)
            checkPrimeNum = IntStream.rangeClosed(2, number).noneMatch(i -> newNumber % i == 0);

        return checkPrimeNum;
    }

    public static boolean isGreaterThanOne(int number) {
        if (number > 1) return true;
        else
            throw new ArithmeticException("It's Wrong! This is a negative number, zero or one!");
    }

    public static boolean isValidRole(String role) {
        List<String> roles = List.of("Admin", "Member", "External");

        return roles.stream()
                .filter(
                        r -> !role.isEmpty() && !role.isBlank()
                )
                .anyMatch(role::equalsIgnoreCase);
    }

    public static boolean has256Characters(String data) {
        if (!data.isEmpty() && !data.isBlank()) return data.length() <= 256;
        else return false;
    }
}
