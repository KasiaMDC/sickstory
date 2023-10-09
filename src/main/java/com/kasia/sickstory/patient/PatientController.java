package com.kasia.sickstory.patient;

import com.kasia.sickstory.user.User;
import com.kasia.sickstory.user.UserDao;
import jakarta.annotation.Resource;
import jakarta.transaction.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.security.core.Authentication;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin
@Transactional
public class PatientController {

    @Resource
    private PatientDao patientDao;
    @Resource
    private UserDao userDao;

    @Resource
    private PatientConverter patientConverter;

    @PostMapping("/patient/add")
    @ResponseBody
    public Patient create(@RequestParam String firstName, @RequestParam String lastName, Authentication authentication) {
        Patient patient = new Patient();
        String userName = authentication.getName();
        User user = userDao.findByUsername(userName);
        patient.setUser(user);

        Optional.ofNullable(firstName).ifPresent(patient::setFirstName);
        Optional.ofNullable(lastName).ifPresent(patient::setLastName);

        patientDao.savePatient(patient);
        return patient;
    }

    @GetMapping("/patient/list")
    @ResponseBody
    public List<PatientPOJO> listPatients(Authentication authentication){
        String userName = authentication.getName();
        User user = userDao.findByUsername(userName);

        List<Patient> patients = patientDao.findAll(user);
        List<PatientPOJO> result = new ArrayList<>();
        for (Patient patient :patients) {
            result.add(patientConverter.convert(patient));
        }
        return result;
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
