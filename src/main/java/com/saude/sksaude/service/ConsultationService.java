package com.saude.sksaude.service;

import com.saude.sksaude.dto.ConsultationDTO;
import com.saude.sksaude.dto.ConsultationUpdateDTO;
import com.saude.sksaude.dto.DoctorUpdateDTO;
import com.saude.sksaude.exception.hadleException.BadRequestException;
import com.saude.sksaude.exception.hadleException.ConflictException;
import com.saude.sksaude.exception.hadleException.NoContentException;
import com.saude.sksaude.exception.hadleException.NotFoundException;
import com.saude.sksaude.model.Consultation;
import com.saude.sksaude.repository.ConsultationCustomRepository;
import com.saude.sksaude.repository.ConsultationRepository;
import com.saude.sksaude.utils.DefaultValueConsultation;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;


import java.time.LocalDateTime;
import java.util.List;

@RequiredArgsConstructor
@Service
public class ConsultationService {

    private final ModelMapper mapper = new ModelMapper();

    private final ConsultationCustomRepository consultationCustomRepository;

    private final ConsultationRepository consultationRepository;


    public Consultation saveConsultation(ConsultationDTO consultationDTO) {
        consultationDTO.toUpperCase();
        if (consultationRepository.findByCdPatient(consultationDTO.getCdPatient()) != null) {
            throw new ConflictException("Já existe esse paciente no sistema");
        }

        if (consultationRepository.findCdDoctor(consultationDTO.getCdDoctor()) != null) {
            throw new ConflictException("Já existe um doutor com esse Médico ");
        }

        if (consultationRepository.findByCdSpecialty(consultationDTO.getCdSpecialty()) != null) {
            throw new ConflictException("Já existe essa Especialidade no sistema");
        }

        Consultation consultation = mapper.map(consultationDTO, Consultation.class);
        consultation.setSnActive(DefaultValueConsultation.snActive);

        consultationRepository.save(consultation);
        return consultation;
    }

    public List<Consultation> getConsultationsByCdPatient(String nrCpf) {
        List<Consultation> consultations = consultationRepository.findAllByCpfPatient(nrCpf);
        if (consultations.isEmpty()) {
            throw new NotFoundException("Consultas não encontradas no sistema para este CPF");
        }
        return consultations;
    }

    public List<Consultation> getAllConsultationsByFilters(Long cdSpecialty, LocalDateTime dtConsultation){
       return consultationCustomRepository.findAllConsultationsByFilters(cdSpecialty, dtConsultation);
    }

    public Consultation actionConsultation(Long cdConsultation, String action) {
        if (cdConsultation == null || action == null) {
            throw new BadRequestException("Parâmetros 'cdConsultation' e 'action' são obrigatórios.");
        }

        action = action.toUpperCase().trim();

        if (!action.equals("S") && !action.equals("N")) {
            throw new BadRequestException("Ação invalida: "+ action + "Deve ser 'S' ou 'N'." );
        }

        Consultation consultation = consultationRepository.findConsultation(cdConsultation);

        if (consultation == null) {
            throw new NotFoundException("codigo da consulta  não encontrado");
        }

        if (action.trim().equals((consultation.getSnActive()))) {
            throw new ConflictException("A Consulta está  + consultation.getSnActive +  no sistema. Altere a ação (S ou N) para a gente trocar no sistema");
        }

        consultation.setSnActive(action);
        return consultationRepository.save(consultation);
    }

    public Consultation updateConsultation(String nrCpf, ConsultationUpdateDTO consultationUpdateDTO) {
        nrCpf = nrCpf.replaceAll("[^\\d]", "");
        consultationUpdateDTO.toUpperCase();
        Consultation existingConsultation = consultationRepository.findByCpfPatient(nrCpf);

        if (existingConsultation == null) {
            throw new NotFoundException("CPF do paciente não encontrado");
        }


        existingConsultation = this.setConsultationUpdate(consultationUpdateDTO, existingConsultation);
        return consultationRepository.save(existingConsultation);
    }

    public  Consultation setConsultationUpdate(ConsultationUpdateDTO consultationUpdateDTO , Consultation existingConsultation) {
        if (consultationUpdateDTO.getDtConsultation() != null) {
            existingConsultation.setDtConsultation(consultationUpdateDTO.getDtConsultation());
        }

        if (consultationUpdateDTO.getDsConsultation() != null) {
            existingConsultation.setDsConsultation(consultationUpdateDTO.getDsConsultation());

        }

        return existingConsultation;
    }


    }





