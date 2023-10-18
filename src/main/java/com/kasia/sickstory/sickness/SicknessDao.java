package com.kasia.sickstory.sickness;

import com.kasia.sickstory.patient.Patient;
import com.kasia.sickstory.sickness.Sickness;
import com.kasia.sickstory.user.User;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

import java.util.List;

@Transactional
@Repository
public class SicknessDao {

    @PersistenceContext
    private EntityManager entityManager;

    public void saveSickness(Sickness sickness) {
        entityManager.persist(sickness);
    }


    public Sickness findByUid(long uid) {
        return entityManager.find(Sickness.class, uid);
    }

    public void update(Sickness sickness) {
        entityManager.merge(sickness);
    }

    public List<Sickness> findAll(Patient patient) {
        TypedQuery<Sickness> query = entityManager.createQuery("SELECT s FROM Sickness s where s.patient=:patient", Sickness.class);
        query.setParameter("patient", patient);
        return query.getResultList();
    }

    public void delete(Sickness sickness) {
        entityManager.remove(entityManager.contains(sickness) ? sickness : entityManager.merge(sickness));
    }
}
