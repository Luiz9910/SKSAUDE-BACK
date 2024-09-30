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

    public List<Consultation> findAllConsultationsByFilters ( long cdSpecialty) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Consultation> query = cb.createQuery(Consultation.class);
        Root<Consultation> consultation = query.from(Consultation.class);

        List<Predicate> predicates = new ArrayList<>();


        predicates.add(cb.equal(consultation.get("snActive"), "S"));

        query.where(predicates.toArray(new Predicate[0]));
        query.select(consultation);
        return entityManager.createQuery(query).getResultList();






    }



}
