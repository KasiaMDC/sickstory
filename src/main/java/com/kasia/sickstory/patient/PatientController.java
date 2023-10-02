package com.kasia.sickstory.patient;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@RestController
public class PatientController {
    @PersistenceContext
    private EntityManager entityManager;

    @GetMapping("/test")
    void getTest() {
        Patient patient = new Patient();
        patient.setFirstName("sdfsdf");
        entityManager.persist(patient);
    }
}
