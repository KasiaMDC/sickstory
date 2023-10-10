package com.kasia.sickstory.sickness;

import com.kasia.sickstory.patient.Patient;
import com.kasia.sickstory.patient.PatientDao;
import com.kasia.sickstory.patient.PatientPOJO;
import com.kasia.sickstory.user.User;
import com.kasia.sickstory.utils.DateFormatService;
import jakarta.annotation.Resource;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin
@Transactional
public class SicknessController {

    @PersistenceContext
    private EntityManager entityManager;
    @Resource
    private SicknessDao sicknessDao;

    @Resource
    private SicknessConverter sicknessConverter;

    @Resource
    private PatientDao patientDao;

    @Resource
    private DateFormatService dateFormatService;


    @PostMapping("/patient/{patientId}/sickness")
    @ResponseBody
    public Sickness create(@PathVariable long patientId,
                           @RequestParam String name,
                           @RequestParam String startDate,
                           @RequestParam (required = false) String endDate,
                           @RequestParam String symptoms,
                           @RequestParam (required = false) int doctorAppointment,
                           @RequestParam (required = false) String commentsToTheDoctorsAppointment,
                           @RequestParam (required = false) String medicine
                           ) {
        Sickness sickness = new Sickness();
        Patient patient = patientDao.findById(patientId);
        sickness.setPatient(patient);
        populateSickness(sickness, name, startDate, endDate, symptoms, doctorAppointment, commentsToTheDoctorsAppointment, medicine);
        sicknessDao.saveSickness(sickness);
        return sickness;
    }

    @PutMapping("/patient/{patientId}/sickness/{sicknessId}")
    public void update(@PathVariable long sicknessId,
                           @RequestParam (required = false) String name,
                           @RequestParam (required = false) String startDate,
                           @RequestParam (required = false) String endDate,
                           @RequestParam (required = false)String symptoms,
                           @RequestParam (required = false) int doctorAppointment,
                           @RequestParam (required = false) String commentsToTheDoctorsAppointment,
                           @RequestParam (required = false) String medicine) {
        Sickness sickness = sicknessDao.findByUid(sicknessId);
        populateSickness(sickness, name, startDate, endDate, symptoms, doctorAppointment, commentsToTheDoctorsAppointment, medicine);
        sicknessDao.update(sickness);
    }

    @DeleteMapping("/patient/{patientId}/sickness/{sicknessId}")
    public void delete(@PathVariable long sicknessId) {
        Sickness sickness = sicknessDao.findByUid(sicknessId);
        sicknessDao.delete(sickness);
    }
    @GetMapping("/patient/{patientId}/sickness/list")
    @ResponseBody
    public List<SicknessPOJO> listSickness(@PathVariable long patientId){
        Patient patient = patientDao.findById(patientId);
        List<Sickness> sicknesses = sicknessDao.findAll(patient);
        List<SicknessPOJO> result = new ArrayList<>();
        for (Sickness sickness :sicknesses) {
            result.add(sicknessConverter.convert(sickness));
        }
        return result;
    }

    private void populateSickness(Sickness sickness, String name, String startDate, String endDate, String symptoms,
                                 int doctorAppointment, String commentsToTheDoctorsAppointment,
                                 String medicine) {

        Optional.ofNullable(name).ifPresent(sickness::setName);
        Optional.ofNullable(startDate).ifPresent(it -> sickness.setStartDate(dateFormatService.convertStringToDate(it)));
        Optional.ofNullable(endDate).ifPresent(it -> sickness.setEndDate(dateFormatService.convertStringToDate(it)));
        Optional.ofNullable(symptoms).ifPresent(sickness::setSymptoms);
        sickness.setDoctorAppointment(doctorAppointment == 0 ? false : true);
        Optional.ofNullable(commentsToTheDoctorsAppointment).ifPresent(sickness::setCommentsToTheDoctorsAppointment);
        Optional.ofNullable(medicine).ifPresent(sickness::setMedicine);
    }
}
