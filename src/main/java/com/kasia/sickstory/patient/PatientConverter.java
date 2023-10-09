package com.kasia.sickstory.patient;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Service;

@Service
public class PatientConverter implements Converter<Patient, PatientPOJO> {
    @Override
    public PatientPOJO convert(Patient source) {
        return new PatientPOJO(
                source.getId(),
                source.getFirstName(),
                source.getLastName());
    }
}
