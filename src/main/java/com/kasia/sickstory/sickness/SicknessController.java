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
    public void create(@PathVariable long patientId,
                           @RequestBody SicknessPOJO sickness) {
        /*@RequestParam String name, --->zrobic walidacje na tych required
        @RequestParam String startDate,
        @RequestParam (required = false) String endDate,
        @RequestParam String symptoms,
        @RequestParam (required = false) int doctorAppointment,
        @RequestParam (required = false) String commentsToTheDoctorsAppointment,
        @RequestParam (required = false) String medicine*/
        Sickness result = new Sickness();
        Patient patient = patientDao.findById(patientId);
        result.setPatient(patient);
        populateSickness(result, sickness.name(),
                sickness.startDate(), sickness.endDate(), sickness.symptoms(), sickness.commentsToTheDoctorsAppointment(), sickness.medicine());
        sicknessDao.saveSickness(result);
    }

    @PutMapping("/patient/{patientId}/sickness/{sicknessUid}")
    @ResponseBody
    public void update(@PathVariable long sicknessUid,
                       @RequestBody SicknessPOJO sickness) {
        Sickness result = sicknessDao.findByUid(sicknessUid);
        populateSickness(result, sickness.name(),
                sickness.startDate(), sickness.endDate(), sickness.symptoms(), sickness.commentsToTheDoctorsAppointment(), sickness.medicine());
        sicknessDao.update(result);
    }

    @DeleteMapping("/patient/{patientId}/sickness/{sicknessUid}")
    @ResponseBody
    public void delete(@PathVariable long sicknessUid) {
        Sickness sickness = sicknessDao.findByUid(sicknessUid);
        sicknessDao.delete(sickness);
    }

    @GetMapping("/patient/{patientId}/sickness/{sicknessUid}")
    @ResponseBody
    public SicknessPOJO getSickness(@PathVariable long sicknessUid) {
        Sickness sickness = sicknessDao.findByUid(sicknessUid);
        return sicknessConverter.convert(sickness);
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

    private void populateSickness(Sickness sickness, String name, String startDate, String endDate, String symptoms, String commentsToTheDoctorsAppointment,
                                 String medicine) {

        Optional.ofNullable(name).ifPresent(sickness::setName);
        Optional.ofNullable(startDate).ifPresent(it -> sickness.setStartDate(dateFormatService.convertStringToDate(it)));
        Optional.ofNullable(endDate).ifPresent(it -> sickness.setEndDate(dateFormatService.convertStringToDate(it)));
        Optional.ofNullable(symptoms).ifPresent(sickness::setSymptoms);
        Optional.ofNullable(commentsToTheDoctorsAppointment).ifPresent(sickness::setCommentsToTheDoctorsAppointment);
        Optional.ofNullable(medicine).ifPresent(sickness::setMedicine);
    }
}
