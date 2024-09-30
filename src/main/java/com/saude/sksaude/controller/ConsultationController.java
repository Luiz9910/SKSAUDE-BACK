package com.saude.sksaude.controller;

import com.saude.sksaude.dto.ConsultationDTO;
import com.saude.sksaude.dto.ConsultationResponse;
import com.saude.sksaude.service.ConsultationService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Validated
@RequiredArgsConstructor
@RestController
@RequestMapping("consultation")
public class ConsultationController {

    private final ConsultationService consultationService;

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public ConsultationResponse saveConsultation(@RequestBody @Valid ConsultationDTO consultationDTO) {
        return new ConsultationResponse("Dados da consulta foi salva com sucesso.",
                this.consultationService.saveConsultation(consultationDTO));
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("{cdPatient}")
    public ConsultationResponse getConsultationByCdPatient(@PathVariable(value = "cdpaciente")Long cdPatient) {
     return new ConsultationResponse("Dados da consulta.",
             this.consultationService.getConsultationByCdPatient(cdPatient));
    }


}
