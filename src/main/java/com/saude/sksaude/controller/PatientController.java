package com.saude.sksaude.controller;

import com.saude.sksaude.dto.PatientDTO;
import com.saude.sksaude.dto.PatientResponse;
import com.saude.sksaude.service.PatientService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Validated
@RequiredArgsConstructor
@RestController
@RequestMapping("/patient")
public class PatientController {

    private final PatientService patientService;

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public PatientResponse savePatient(@RequestBody @Valid PatientDTO patientDTO) {
        return new PatientResponse("Dados do paciente foi salvo com sucesso",
                this.patientService.savePatient(patientDTO));
    }
}
