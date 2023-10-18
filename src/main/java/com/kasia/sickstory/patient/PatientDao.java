package com.kasia.sickstory.patient;

import com.kasia.sickstory.user.User;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

import java.util.List;

@Transactional
@Repository
public class PatientDao {

    @PersistenceContext
    private EntityManager entityManager;

    public void savePatient(Patient patient) {
        entityManager.persist(patient);
    }

    public Patient findById(long id) {
        return entityManager.find(Patient.class, id);
    }

    public Patient findByFirstName(String firstName) {
        return entityManager.find(Patient.class, firstName);
    }

    public Patient findByLastName(String lastName) {
        return entityManager.find(Patient.class, lastName);
    }

    public List<Patient> findAll(User user) {
        TypedQuery<Patient> query = entityManager.createQuery("SELECT p FROM Patient p where p.user=:user", Patient.class);
        query.setParameter("user", user);
        return query.getResultList();
    }

    public void update(Patient patient) {
        entityManager.merge(patient);
    }

    public void delete(Patient patient) {
        entityManager.remove(entityManager.contains(patient) ? patient : entityManager.merge(patient));
    }
}
