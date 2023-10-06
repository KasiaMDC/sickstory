package com.kasia.sickstory.sickness;

import com.kasia.sickstory.sickness.Sickness;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

@Transactional
@Repository
public class SicknessDao {

    @PersistenceContext
    private EntityManager entityManager;
    public void saveSickness(Sickness sickness) {
        entityManager.persist(sickness); }


    public Sickness findByUid(long uid) {
        return entityManager.find(Sickness.class, uid);
    }

    public void update(Sickness sickness) {
        entityManager.merge(sickness);
    }

    public void delete(Sickness sickness) {
        entityManager.remove(entityManager.contains(sickness) ? sickness : entityManager.merge(sickness));
    }
}
