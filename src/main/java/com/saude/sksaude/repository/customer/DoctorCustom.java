package com.saude.sksaude.repository.customer;

import com.saude.sksaude.exception.hadleException.NotFoundException;
import com.saude.sksaude.model.Doctor;
import com.saude.sksaude.utils.DefaultValueDoctor;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

import static com.saude.sksaude.utils.DefaultValueDoctor.snActive;

@Repository
public class DoctorCustom {
    @PersistenceContext
    EntityManager entityManager;

    public List<Doctor> findAllDoctorByFilter(String nmDoctor, Integer cdSpecialty) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Doctor> query = cb.createQuery(Doctor.class);
        Root<Doctor> medico = query.from(Doctor.class);

        List<Predicate> predicates = new ArrayList<>();

        if(nmDoctor != null) {
            predicates.add(cb.like(cb.upper(medico.get("nmDoctor")), "%" + nmDoctor.trim().toUpperCase() + "%"));
        }

        if (cdSpecialty != null) {
            predicates.add(cb.equal(medico.get("cdSpecialty"), cdSpecialty));
        }

        predicates.add(cb.equal(medico.get("snActive"), snActive));


        query.where(predicates.toArray(new Predicate[predicates.size()]));
        query.select(medico);
        return entityManager.createQuery(query).getResultList();
    }
}
