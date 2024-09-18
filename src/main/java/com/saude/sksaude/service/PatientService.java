package com.saude.sksaude.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.saude.sksaude.dto.PatientDTO;
import com.saude.sksaude.exception.BadRequestException;
import com.saude.sksaude.exception.NotFoundException;
import com.saude.sksaude.model.Patient;
import com.saude.sksaude.repository.PatientRepository;
import com.saude.sksaude.utils.DefaultValuePatient;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.concurrent.CompletableFuture;

@RequiredArgsConstructor
@Service
public class PatientService {
    private ModelMapper mapper = new ModelMapper();


    private final PatientRepository patientRepository;

    public Patient savePatient(PatientDTO patientDTO) {
        if (patientRepository.findByNrCpf(patientDTO.getNrCpf()) != null) {
            throw new BadRequestException("Paciente existe no sistema");
        }

        patientDTO.toUpperCase();
        Patient patient = mapper.map(patientDTO, Patient.class);
        patient.setSnActive(DefaultValuePatient.snActive);
        patient = this.getLocalizationAPI(patient);

        patientRepository.save(patient);
        return patient;
    }

    public Patient getLocalizationAPI(Patient patient) {
        try {
            String urlAddress = String.format("https://viacep.com.br/ws/%s/json/", patient.getNrCep());
            RestTemplate restTemplate = new RestTemplate();
            String response = restTemplate.getForObject(urlAddress, String.class);
            ObjectMapper mapper = new ObjectMapper();
            JsonNode node = mapper.readTree(response);
            patient.setNmCity(node.get("localidade").asText());
            patient.setNmState(node.get("estado").asText());
            patient.setTpPublicPlace(node.get("estado").asText() == null ? "Sem informação" : node.get("estado").asText());

            return patient;
        } catch (Exception e) {
            return null;
        }
    }
}
