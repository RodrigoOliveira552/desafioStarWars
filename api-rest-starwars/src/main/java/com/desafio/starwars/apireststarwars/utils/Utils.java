package com.desafio.starwars.apireststarwars.utils;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Utils {

    public static final String API_SWAPI_FORMAT = "yyyy-MM-dd";
    public static final String API_REST_FORMAT = "dd/MM/yyyy";

    public static String formatDate(String padraoInput, String padraoOutput, String valorData) {
        DateTimeFormatter dtfIn = DateTimeFormatter.ofPattern(padraoInput);
        LocalDate ld = LocalDate.parse(valorData, dtfIn);
        DateTimeFormatter dtfOut = DateTimeFormatter.ofPattern(padraoOutput);
        return dtfOut.format(ld);
    }
}