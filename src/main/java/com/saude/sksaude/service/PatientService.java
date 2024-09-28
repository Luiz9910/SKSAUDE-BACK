package com.saude.sksaude.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.saude.sksaude.dto.PatientDTO;
import com.saude.sksaude.dto.PatientUpdateDTO;
import com.saude.sksaude.exception.hadleException.BadRequestException;
import com.saude.sksaude.exception.hadleException.ConflictException;
import com.saude.sksaude.model.Patient;
import com.saude.sksaude.repository.PatientCustomRepository;
import com.saude.sksaude.repository.PatientRepository;
import com.saude.sksaude.utils.DefaultValuePatient;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDateTime;
import java.util.List;


@RequiredArgsConstructor
@Service
public class PatientService {
    private final ModelMapper mapper = new ModelMapper();

    private final PatientCustomRepository patientCustomRepository;

    private final PatientRepository patientRepository;

    public Patient savePatient(PatientDTO patientDTO) {
        if (patientRepository.findByNrCpf(patientDTO.getNrCpf()) != null) {
            throw new ConflictException("Já existe esse paciente no sistema");
        }

        patientDTO.toUpperCase();
        Patient patient = mapper.map(patientDTO, Patient.class);
        patient.setSnActive(DefaultValuePatient.snActive);
        patient = this.getLocalizationAPI(patient);
        patient.setDtCreated(LocalDateTime.now());

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

    public Patient getPatientByNrCpf(String nrCpf) {
        return this.findPatientByNrCpf(nrCpf);
    }

    public Patient actionsPatient(String nrCpf, String action) {
        if (nrCpf == null || action == null) {
            throw new BadRequestException("Parâmetros 'nrCpf' e 'action' são obrigatórios.");
        }

        action = action.toUpperCase().trim();
        if (!action.equals("S") && !action.equals("N")) {
            throw new BadRequestException("Ação inválida: " + action + ". Deve ser 'S' ou 'N'.");
        }

        Patient patient = this.findPatientByNrCpf(nrCpf);

        if (action.trim().equals(patient.getSnActive())) {
            throw new ConflictException("O paciente está " + patient.getSnActive() + " no sistema. Altere a ação (S ou N) para a gente trocar no sistema");
        }

        patient.setSnActive(action);
        return patientRepository.save(patient);
    }

    public Patient updatePatient(String nrCpf, @Valid PatientUpdateDTO patientUpdateDTO) {
        Patient existingPatient = this.findPatientByNrCpf(nrCpf);
        patientUpdateDTO.toUpperCase();
        existingPatient = this.setPatientUpdate(patientUpdateDTO, existingPatient);
        return patientRepository.save(existingPatient);
    }

    public Patient findPatientByNrCpf(String nrCpf) {
        Patient patient = patientRepository.findPatientByNrCpf(nrCpf.replaceAll("[^0-9]", ""));
        if (patient == null) {
            throw new ConflictException("Paciente não foi encontrado no sistema");
        }

        return patient;
    }

    public Patient setPatientUpdate(PatientUpdateDTO patientUpdateDTO, Patient existingPatient) {
        if (patientUpdateDTO.getNmPatient() != null) {
            existingPatient.setNmPatient(patientUpdateDTO.getNmPatient());
        }

        if (patientUpdateDTO.getEmail() != null) {
            existingPatient.setEmail(patientUpdateDTO.getEmail());
        }

        if (patientUpdateDTO.getNrPhone() != null) {
            existingPatient.setNrPhone(patientUpdateDTO.getNrPhone());
        }

        if (patientUpdateDTO.getDtBirthday() != null) {
            existingPatient.setDtBirthday(patientUpdateDTO.getDtBirthday());
        }

        if (patientUpdateDTO.getTpSex() != null) {
            existingPatient.setTpSex(patientUpdateDTO.getTpSex());
        }

        if (patientUpdateDTO.getTpMaritalStatus() != null) {
            existingPatient.setTpMaritalStatus(patientUpdateDTO.getTpMaritalStatus());
        }

        if (patientUpdateDTO.getNrCep() != null) {
            existingPatient.setNrCep(String.valueOf(patientUpdateDTO.getNrCep()));
        }

        if (patientUpdateDTO.getDsAddress() != null) {
            existingPatient.setDsAddress(patientUpdateDTO.getDsAddress());
        }

        if (patientUpdateDTO.getTpSkinColor() != null) {
            existingPatient.setTpSkinColor(patientUpdateDTO.getTpSkinColor());
        }

        if (patientUpdateDTO.getTpBlood() != null) {
            existingPatient.setTpBlood(patientUpdateDTO.getTpBlood());
        }

        return existingPatient;
    }

    public List<Patient> getAllPatients(LocalDateTime dtRegister, String name, String bloodType, String gender, String postalCode) {
        return patientCustomRepository.findAllByFilters(dtRegister, name, bloodType, gender, postalCode);
    }
}
