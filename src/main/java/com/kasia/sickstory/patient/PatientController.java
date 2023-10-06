package com.kasia.sickstory.patient;

import jakarta.annotation.Resource;
import jakarta.transaction.Transactional;
import org.springframework.web.bind.annotation.*;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

@RestController
@Transactional
public class PatientController {
    @PersistenceContext
    private EntityManager entityManager;

    @Resource
    private PatientDao patientDao;

    @PostMapping("/patient/add")
    @ResponseBody
    public Patient create(@RequestParam String firstName, @RequestParam String lastName) {
        Patient patient = new Patient();
        patient.setFirstName(firstName);
        patient.setLastName(lastName);
        patientDao.savePatient(patient);
        return patient;
    }

    @GetMapping("/patient/{id}")
    @ResponseBody
    public Patient getPatient(@PathVariable long id) {
        Patient patient = patientDao.findById(id);
        return patient;
    }

    @PutMapping("/patient/{id}")
    @ResponseBody
    public Patient updatePatient(@PathVariable long id, @RequestParam(required = false) String firstName, @RequestParam(required = false) String lastName) {
        Patient patient = patientDao.findById(id);
        //add optional to check if it's not null
        patient.setFirstName(firstName);
        patient.setLastName(lastName);
        patientDao.update(patient);
        return patient;
    }

    @DeleteMapping("/patient/{id}")
    @ResponseBody
    public Patient deleteBook(@PathVariable long id) {
        Patient patient = patientDao.findById(id); patientDao.delete(patient);
        return patient;
    }

}
