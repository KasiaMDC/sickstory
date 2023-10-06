package com.kasia.sickstory.patient;

import jakarta.transaction.Transactional;
import org.springframework.stereotype.Repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

@Transactional
@Repository
public class PatientDao {

    @PersistenceContext
    private EntityManager entityManager;
    public void savePatient(Patient patient) {
        entityManager.persist(patient); }


    public Patient findById(long id) {
        return entityManager.find(Patient.class, id);
    }

    public Patient findByFirstName(String firstName) {
        return entityManager.find(Patient.class, firstName);
    }

    public Patient findByLastName(String lastName) {
        return entityManager.find(Patient.class, lastName);
    }
    public void update(Patient patient) {
        entityManager.merge(patient);
    }

    public void delete(Patient patient) {
        entityManager.remove(entityManager.contains(patient) ? patient : entityManager.merge(patient));
    }
}
