package com.saude.sksaude.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ConsultationDTO {
    @NotBlank(message = "Descricao da consulta deve ser informado! Ex: febre")
    @Schema(description = "The description of the consultation must be provided", example = "fever", required = true )
    private String dsConsultation;

    @NotNull(message = "Data da consulta precisa ser informada! Ex:26/10/24")
    @Schema(description = "The consultation date must be provided", example = "26/10/24", required = true)
    private LocalDateTime dtConsultation;

    @NotNull(message = "Especialidade do medico precisa ser informada! Ex: 1")
    @Schema(description = "The doctor's specialty must be provided", example = "1",required = true)
    private Long cdSpecialty;

    @NotNull(message = "Codigo do paciente deve ser informado! Ex:557-29-5318")
    @Schema(description = "The patient's code must be provided", example = "557-29-5318", required = true)
    private Long  cdPatient;

    @NotNull(message = "Codigo do medico deve ser informado! Ex:558-28-5333")
    @Schema(description = "The docter code must be provided", example = "558-28-5333", required = true)
    private Long cdDoctor;



    public void toUpperCase() {
        dsConsultation = this.dsConsultation.toUpperCase();
    }
}
