package com.kasia.sickstory.utils;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

class DateFormatServiceTest {

    private DateFormatService dateFormatService;

    @BeforeEach
    public void setUp() {
        this.dateFormatService = new DateFormatService();
    }

    @Test
    void convertDateToString() {
        LocalDate sourceDate = LocalDate.of(2023, 10, 18);

        String result = dateFormatService.convertDateToString(sourceDate);

        assertEquals("18-10-2023", result);


    }

    @Test
    void convertStringToDate() {

        //given
        String sourceDate = "22-03-2023";

        //when
        LocalDate result = dateFormatService.convertStringToDate(sourceDate);

        //then
        assertEquals(LocalDate.of(2023, 03, 22), result);

    }
}