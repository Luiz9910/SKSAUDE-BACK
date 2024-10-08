package com.saude.sksaude.repository;

import com.saude.sksaude.model.Consultation;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Repository
public class ConsultationCustomRepository {
    @PersistenceContext
    EntityManager entityManager;

    public List<Consultation> findAllConsultationsByFilters ( Long cdSpecialty, LocalDateTime dtConsultation) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Consultation> query = cb.createQuery(Consultation.class);
        Root<Consultation> consulta = query.from(Consultation.class);

        List<Predicate> predicates = new ArrayList<>();


        if (cdSpecialty != null) {
            predicates.add(cb.equal(consulta.get("cdSpecialty"), cdSpecialty));
        }

        if (dtConsultation != null) {
            predicates.add(cb.between(consulta.get("dtConsultation"), dtConsultation, LocalDateTime.now()));
        }

        predicates.add(cb.equal(consulta.get("snActive"), "S"));

        query.where(predicates.toArray(new Predicate[0]));
        query.select(consulta);
        return entityManager.createQuery(query).getResultList();
    }
}
