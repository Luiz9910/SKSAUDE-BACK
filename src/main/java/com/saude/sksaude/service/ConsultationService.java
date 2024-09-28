package com.saude.sksaude.service;

import com.saude.sksaude.dto.ConsultationDTO;
import com.saude.sksaude.exception.hadleException.ConflictException;
import com.saude.sksaude.model.Consultation;
import com.saude.sksaude.repository.ConsultationCustom;
import com.saude.sksaude.repository.ConsultationRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

    @RequiredArgsConstructor
    @Service
    public class ConsultationService {

        private final ModelMapper mapper = new ModelMapper();

        private final ConsultationCustom consultationCustomRepository;

        private final ConsultationRepository consultationRepository;

        public Consultation saveConsultation(ConsultationDTO consultationDTO) {
            if (consultationRepository.findByCdPaciente(consultationDTO.getCdPaciente()) != null) {
                throw new ConflictException("Sua consulta a está cadastrada");
            }

           if ()
        }

    }
