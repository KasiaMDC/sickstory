package com.kasia.sickstory.sickness;

import com.kasia.sickstory.patient.Patient;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@RestController
@Transactional
public class SicknessController {

    @PersistenceContext
    private EntityManager entityManager;

    private SicknessDao sicknessDao;


    @PostMapping("/patient/{patientId}/sickness")
    @ResponseBody
    public Sickness create(@PathVariable long patientId,
                           @RequestParam String name,
                           //@RequestParam String startDate,
                           //@RequestParam(required = false) String endDate,
                           @RequestParam String symptoms,
                           @RequestParam(required = false) int doctorAppointment,
                           @RequestParam(required = false) String commentsToTheDoctorsAppointment,
                           @RequestParam(required = false) String medicine
                           ) {
        Sickness sickness = new Sickness();
        sickness.setName(name);
        //sickness.setStartDate(startDate); trzeba zamienic string na odpowiedni format daty i to robi servis
       // sickness.setEndDate(endDate);
        sickness.setDoctorAppointment(doctorAppointment == 0 ? false : true);
        sickness.setCommentsToTheDoctorsAppointment(commentsToTheDoctorsAppointment);
        sickness.setMedicine(medicine);
        sicknessDao.saveSickness(sickness);
        return sickness;
    }
}
