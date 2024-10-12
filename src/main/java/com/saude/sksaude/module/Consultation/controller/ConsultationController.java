package com.saude.sksaude.module.Consultation.controller;

import com.saude.sksaude.module.Consultation.dto.request.ConsultationDTO;
import com.saude.sksaude.module.Consultation.dto.response.ConsultationListResponse;
import com.saude.sksaude.module.Consultation.dto.request.ConsultationUpdateDTO;
import com.saude.sksaude.module.Consultation.dto.response.ConsultationResponse;
import com.saude.sksaude.module.Consultation.model.Consultation;
import com.saude.sksaude.module.Consultation.Service.ConsultationService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

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
    @GetMapping("{nrCpf}")
    public ConsultationListResponse getByCpfPatient(@PathVariable(value = "nrCpf") String nrCpf) {
        List<Consultation> consultations = this.consultationService.getConsultationsByCdPatient(nrCpf);
        return new ConsultationListResponse("Dados das consultas.", consultations);
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("consultation/filter")
    public ConsultationListResponse getAllConsultationsByFilters (
            @RequestParam(required = false) Long cdSpecialty,
            @RequestParam(required = false) LocalDateTime dtConsultation) {

      return new ConsultationListResponse("Data da consulta:",
              this.consultationService.getAllConsultationsByFilters(cdSpecialty, dtConsultation));
    }

    @ResponseStatus(HttpStatus.OK)
        @PatchMapping("action/")
    public ConsultationResponse actionConsultation(@RequestParam(name = "cdConsultation") Long cdConsultation, @RequestParam("action") String action) {
        return new ConsultationResponse ("Ação para ativar ou inativar a consulta foi realizada com sucesso.",
                this.consultationService.actionConsultation(cdConsultation, action));
    }

    @ResponseStatus(HttpStatus.OK)
    @PutMapping("{nrCpf}")
    public ConsultationResponse updateConsultation(@PathVariable(value = "nrCpf")String nrCpf, @RequestBody @Valid ConsultationUpdateDTO consultationUpdateDTO) {
        return new ConsultationResponse("Dados da consulta atualizado com scesso.",
                this.consultationService.updateConsultation(nrCpf, consultationUpdateDTO ));
    }

}
