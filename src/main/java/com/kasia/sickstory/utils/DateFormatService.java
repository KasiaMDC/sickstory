package com.kasia.sickstory.utils;

import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
@Service
public class DateFormatService {
    private static final String DATE_PATTERN = "dd-MM-yyyy";

    public String convertDateToString(LocalDate date){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(DATE_PATTERN);
        return formatter.format(date);
    }

    public LocalDate convertStringToDate(String date){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(DATE_PATTERN);
        return LocalDate.parse(date, formatter);
    }
}
