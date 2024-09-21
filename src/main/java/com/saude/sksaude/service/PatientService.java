package com.saude.sksaude.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.saude.sksaude.dto.PatientDTO;
import com.saude.sksaude.exception.BadRequestException;
import com.saude.sksaude.exception.ConflictException;
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
            throw new ConflictException("Já existe esse paciente no sistema");
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

    public Patient actionsPatient(String nrCpf, String action) {
        action = action.toUpperCase().trim();
        if (!action.equals("S") && !action.equals("N")) {
            throw new BadRequestException("Ação inválida: " + action + ". Deve ser 'S' ou 'N'.");
        }

        Patient patient = patientRepository.findPatientByNrCpf(nrCpf);
        if (patient == null) {
            throw new ConflictException("Paciente não foi encontrado no sistema");
        }

        if (action.trim().equals(patient.getSnActive())) {
            throw new ConflictException("O paciente está " + patient.getSnActive() + " no sistema. Altere a ação (S ou N) para a gente trocar no sistema");
        }

        patient.setSnActive(action);
        return patientRepository.save(patient);
    }
}
