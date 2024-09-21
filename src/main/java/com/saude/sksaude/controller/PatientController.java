package com.saude.sksaude.controller;

import com.saude.sksaude.dto.PatientDTO;
import com.saude.sksaude.dto.PatientResponse;
import com.saude.sksaude.dto.PatientUpdateDTO;
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
        return new PatientResponse("Dados do paciente foi salvo com sucesso.",
                this.patientService.savePatient(patientDTO));
    }

    @ResponseStatus(HttpStatus.OK)
    @PutMapping("{nrCpf}")
    public PatientResponse updatePatient(@PathVariable(value = "nrCpf") String nrCpf, @RequestBody @Valid PatientUpdateDTO patientUpdateDTO) {
        return new PatientResponse("Dados do paciente atualizado com suceso.",
                this.patientService.updatePatient(nrCpf, patientUpdateDTO));
    }

    @ResponseStatus(HttpStatus.OK)
    @PatchMapping("action/")
    public PatientResponse actionsPatient (@RequestParam(name = "nrCpf") String nrCpf, @RequestParam("action") String action) {
        return new PatientResponse("Ação para ativar ou inativar o paciente foi realizada com sucesso.",
                this.patientService.actionsPatient(nrCpf, action));
    }
}
