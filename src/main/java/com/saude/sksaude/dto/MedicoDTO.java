package com.saude.sksaude.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.br.CPF;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MedicoDTO {
    @NotBlank(message = "nome do médico precisa ser informado! Ex: Marcelo")
    @Schema(description = "nome completo do médio", example = "Marcelo", required = true)
    private String nmMedico;

    @CPF
    @NotBlank(message = "Número do CPF precisa ser informado! Ex: 97002046004")
    @Schema(description = "Doctor CPF (Brazilian individual taxpayer registry number)", example = "97002046004", required = true)
    private String nrCpf;

    @NotBlank(message = "Número do CRM precisa ser informado! Ex: 123456-UF")
    @Pattern(regexp = "\\d{5,7}-(AC|AL|AP|AM|BA|CE|DF|ES|GO|MA|MT|MS|MG|PA|PB|PR|PE|PI|RJ|RN|RS|RO|RR|SC|SP|SE|TO)",
            message = "Digite a numeração do código e logo após a sigla do estado (ex: 123456-SP)")
    @Schema(description = "Código CRM do médico", example = "123456-SP", required = true)
    private String crm;


    @NotNull(message = "Codigo da especialidade precisa ser informado")
    @Schema(description = "codigo da especialidade", example = "1", required = true)
    private Integer cdEspecialidade;



    public void toUpperCase() {
        nmMedico = nmMedico.toUpperCase();
        crm = crm.toUpperCase();

    }
}
