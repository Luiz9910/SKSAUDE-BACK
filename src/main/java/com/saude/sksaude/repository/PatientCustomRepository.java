package com.saude.sksaude.repository;

import com.saude.sksaude.model.Patient;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Repository
public class PatientCustomRepository {
    @PersistenceContext
    private EntityManager entityManager;

    public List<Patient> findAllByFilters(LocalDateTime dtRegister, String name, String bloodType, String gender, String postalCode) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Patient> query = cb.createQuery(Patient.class);
        Root<Patient> patient = query.from(Patient.class);

        List<Predicate> predicates = new ArrayList<>();

        if (dtRegister != null) {
            predicates.add(cb.between(patient.get("dtCreated"), dtRegister, LocalDateTime.now()));
        }

        if (name != null && !name.isEmpty()) {
            predicates.add(cb.like(cb.upper(patient.get("nmPatient")), "%" + name.toUpperCase() + "%"));
        }

        if (bloodType != null && !bloodType.isEmpty()) {
            predicates.add(cb.equal(patient.get("tpBlood"), bloodType.toUpperCase()));
        }

        if (gender != null && !gender.isEmpty()) {
            predicates.add(cb.equal(patient.get("tpSex"), gender.toUpperCase()));
        }

        if (postalCode != null && !postalCode.isEmpty()) {
            predicates.add(cb.equal(patient.get("nrCep"), postalCode));
        }

        query.where(predicates.toArray(new Predicate[0]));
        query.select(patient);
        return entityManager.createQuery(query).getResultList();
    }
}
