package com.saude.sksaude.controller;

import com.saude.sksaude.dto.PatientDTO;
import com.saude.sksaude.dto.PatientListResponse;
import com.saude.sksaude.dto.PatientResponse;
import com.saude.sksaude.dto.PatientUpdateDTO;
import com.saude.sksaude.service.PatientService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

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
    @GetMapping("{nrCpf}")
    public PatientResponse getPatientByNrCpf(@PathVariable(value = "nrCpf") String nrCpf) {
        return new PatientResponse("Dados do paciente.",
                this.patientService.getPatientByNrCpf(nrCpf));
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

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/patients/filter")
    public PatientListResponse getAllPatientFilter(
            @RequestParam(required = false) LocalDateTime dtRegister,
            @RequestParam(required = false) String name,
            @RequestParam(required = false) String bloodType,
            @RequestParam(required = false) String gender,
            @RequestParam(required = false) String postalCode) {

        return new PatientListResponse("Dados dos pacientes filtrados.",
                this.patientService.getAllPatients(dtRegister, name, bloodType, gender, postalCode));
    }
}
