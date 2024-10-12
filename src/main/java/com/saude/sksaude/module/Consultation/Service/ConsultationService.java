package com.saude.sksaude.module.Consultation.Service;

import com.saude.sksaude.module.Consultation.dto.request.ConsultationDTO;
import com.saude.sksaude.module.Consultation.dto.request.ConsultationUpdateDTO;
import com.saude.sksaude.exception.hadleException.BadRequestException;
import com.saude.sksaude.exception.hadleException.ConflictException;
import com.saude.sksaude.exception.hadleException.NotFoundException;
import com.saude.sksaude.module.Consultation.model.Consultation;
import com.saude.sksaude.module.Consultation.repository.custom.ConsultationCustomRepository;
import com.saude.sksaude.module.Consultation.repository.ConsultationRepository;
import com.saude.sksaude.utils.DefaultValueConsultation;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;


import java.time.LocalDateTime;
import java.util.List;

@RequiredArgsConstructor
@Service
public class    ConsultationService {

    private final ModelMapper mapper = new ModelMapper();

    private final ConsultationCustomRepository consultationCustomRepository;

    private final ConsultationRepository consultationRepository;


    public Consultation saveConsultation(ConsultationDTO consultationDTO) {
        consultationDTO.toUpperCase();
        if (consultationRepository.findByCdPatient(consultationDTO.getCdPatient()) == null) {
            throw new NotFoundException("Paciente não encontrado");
        }

        if (consultationRepository.findCdDoctor(consultationDTO.getCdDoctor()) == null) {
            throw new NotFoundException("Médico não encontrado");
        }

        if (consultationRepository.findByCdSpecialty(consultationDTO.getCdSpecialty()) == null) {
            throw new NotFoundException("Especialidade não encontrada");
        }

        Consultation consultation = mapper.map(consultationDTO, Consultation.class);
        consultation.setSnActive(DefaultValueConsultation.snActive);


        return consultationRepository.save(consultation);
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
            throw new ConflictException("A Consulta está " + consultation.getSnActive() +  " no sistema. Altere a ação (S ou N) para a gente trocar no sistema");
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





