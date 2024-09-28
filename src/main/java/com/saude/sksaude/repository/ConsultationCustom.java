package com.saude.sksaude.repository;

import com.saude.sksaude.model.Consultation;
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
public class ConsultationCustom {
    @PersistenceContext
    EntityManager entityManager;

    public findAllByFilters (LocalDateTime dtRegister, String cdPatient, String cdDoctor, String discricao) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Consultation> query = cb.createQuery(Consultation.class);
        Root<Consultation> consultation = query.from(Consultation.class);

        List<Predicate> predicates = new ArrayList<>();

        if (dtRegister != null) {
            predicates.add(cb.between(consultation.get("dtConsultation"), dtRegister, LocalDateTime.now()));
        }

        if (discricao != null && !discricao.isEmpty()) {
            predicates.add(cb.like(cb.lower(consultation.get("dsConsultation")), "%"+ discricao.toLowerCase() + "%"));

        }




    }



}
