package com.kasia.sickstory.patient;

import jakarta.annotation.Resource;
import jakarta.transaction.Transactional;
import org.springframework.web.bind.annotation.*;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

import java.util.Optional;

@RestController
@Transactional
public class PatientController {

    @Resource
    private PatientDao patientDao;

    @PostMapping("/patient/add")
    @ResponseBody
    public Patient create(@RequestParam String firstName, @RequestParam String lastName) {
        Patient patient = new Patient();
        Optional.ofNullable(firstName).ifPresent(patient::setFirstName);
        Optional.ofNullable(lastName).ifPresent(patient::setLastName);
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
    public void updatePatient(@PathVariable long id,
                              @RequestParam(required = false) String firstName,
                              @RequestParam(required = false) String lastName) {
        Patient patient = patientDao.findById(id);
        Optional.ofNullable(firstName).ifPresent(patient::setFirstName);
        Optional.ofNullable(lastName).ifPresent(patient::setLastName);
        patientDao.update(patient);
    }

    @DeleteMapping("/patient/{id}")
    @ResponseBody
    public void deletePatient(@PathVariable long id) {
        Patient patient = patientDao.findById(id);
        patientDao.delete(patient);
    }

}
