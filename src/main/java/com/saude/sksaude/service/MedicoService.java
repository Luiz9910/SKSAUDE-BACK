package com.saude.sksaude.service;

import com.saude.sksaude.dto.MedicoDTO;
import com.saude.sksaude.exception.ConflictException;
import com.saude.sksaude.model.Medico;
import com.saude.sksaude.repository.MedicoRepository;
import com.saude.sksaude.utils.DefaultValueMedico;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class MedicoService {
    private ModelMapper mapper = new ModelMapper();

    private final MedicoRepository medicoRepository;

    public Medico saveMedico(MedicoDTO medicoDTO){
        if (medicoRepository.findByNrCpf(medicoDTO.getNrCpf()) != null) {
            throw new ConflictException("Já existe esse medico no sistema");
        }
        if (medicoRepository.findByCdEspecialidade(medicoDTO.getCdEspecialidade()) == null) {
            throw new ConflictException("Especialidade não encontrada");
        }
        medicoDTO.toUpperCase();
        Medico medico = mapper.map(medicoDTO, Medico.class);
        medico.setSnActive(DefaultValueMedico.snActive);

        medicoRepository.save(medico);
        return medico;

    }

}
