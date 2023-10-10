package com.kasia.sickstory.sickness;

public record SicknessPOJO(long uid, String name, String startDate, String endDate,
                               String symptoms, String commentsToTheDoctorsAppointment, String medicine){}

