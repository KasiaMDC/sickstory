package com.kasia.sickstory.sickness;

import com.kasia.sickstory.patient.Patient;
import com.kasia.sickstory.patient.PatientPOJO;
import com.kasia.sickstory.utils.DateFormatService;
import jakarta.annotation.Resource;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Service;

@Service
public class SicknessConverter implements Converter<Sickness, SicknessPOJO> {

    @Resource
    private DateFormatService dateFormatService;

    @Override
    public SicknessPOJO convert(Sickness source) {
        return new SicknessPOJO(
                source.getUid(),
                source.getName(),
                dateFormatService.convertDateToString(source.getStartDate()),
                dateFormatService.convertDateToString(source.getEndDate()),
                source.getSymptoms(),
                source.getCommentsToTheDoctorsAppointment(),
                source.getMedicine());
    }
}
