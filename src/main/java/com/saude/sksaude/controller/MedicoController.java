package com.saude.sksaude.controller;

import com.saude.sksaude.dto.MedicoDTO;
import com.saude.sksaude.dto.MedicoResponse;
import com.saude.sksaude.dto.PatientDTO;
import com.saude.sksaude.dto.PatientResponse;
import com.saude.sksaude.service.MedicoService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Validated
@RequiredArgsConstructor
@RestController
@RequestMapping("/medico")
public class MedicoController {

    private final MedicoService medicoService;

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public MedicoResponse saveMedico(@RequestBody @Valid MedicoDTO medicoDTO){
        return new MedicoResponse("medico foi salvo com sucesso",
                this.medicoService.saveMedico(medicoDTO)
        );
    }
}
