package com.saude.sksaude.controller;

import com.saude.sksaude.ValidatorAnnotation.birthDay.ValidBirthday;
import com.saude.sksaude.dto.ConsultationDTO;
import com.saude.sksaude.dto.ConsultationResponse;
import com.saude.sksaude.service.ConsultationService;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@Validated
@RequiredArgsConstructor
@RestController
@RequestMapping("/consultation")
public class ConsultationController {

    private final ConsultationService consultationService;

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public ConsultationResponse saveConsultation(@RequestBody @Valid ConsultationDTO consultationDTO) {
        return new ConsultationResponse("Dados da consuulta foi salva com sucesso.",
                this.consultationService.saveConsultation(consultationDTO));
    }



}
