package com.saude.sksaude.service;

import com.saude.sksaude.dto.ConsultationDTO;
import com.saude.sksaude.exception.hadleException.ConflictException;
import com.saude.sksaude.exception.hadleException.NotFoundException;
import com.saude.sksaude.model.Consultation;
import com.saude.sksaude.repository.ConsultationCustomRepository;
import com.saude.sksaude.repository.ConsultationRepository;
import com.saude.sksaude.utils.DefaultValueConsultation;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class ConsultationService {

    private final ModelMapper mapper = new ModelMapper();

    private final ConsultationCustomRepository consultationCustomRepository;

    private final ConsultationRepository consultationRepository;


    public Consultation saveConsultation(ConsultationDTO consultationDTO) {
        consultationDTO.toUpperCase();
        if (consultationRepository.findByCdPatient(consultationDTO.getCdPatient()) != null) {
            throw new ConflictException("Sua consulta já existe no sistema");
        }

       if (consultationRepository.findByCdSpecialty(consultationDTO.getCdSpecialty()) != null) {
           throw new ConflictException("já existe esse codigo de especialidade nessa data no sistema");
       }

       if (consultationRepository.findCdDoctor(consultationDTO.getCdDoctor()) != null) {
           throw new ConflictException ("já existe consulta com esse codigo no sistema ");
       }

       Consultation consultation = mapper.map(consultationDTO, Consultation.class);
       consultation.setSnActive(DefaultValueConsultation.snActive);

       consultationRepository.save(consultation);
       return consultation;
    }

    public Consultation getConsultationByCdPatient(Long cdPatient) {
        if (consultationRepository.findByCdPatient(cdPatient) == null){
            throw new NotFoundException("Consulta não encotrada no sistema");
        }
        return this.consultationRepository.findByCdPatient(cdPatient);
    }




}




