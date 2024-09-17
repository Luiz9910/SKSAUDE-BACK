package com.saude.sksaude.dto;

import com.saude.sksaude.ValidatorAnnotation.birthDay.ValidBirthday;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.br.CPF;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PatientDTO {
    @NotBlank(message = "Nome do paciente precisa ser informado! Ex: Luiz")
    @Schema(description = "Patient's full name", example = "Luiz", required = true)
    private String nmPatient;

    @Email(message = "E-mail precisa ser informado! Ex: exemplo@email.com")
    @Schema(description = "Patient's email address", example = "exemplo@email.com", required = true)
    private String email;

    @NotNull(message = "Número de telefone precisa ser informado! Ex: 81992596941")
    @Pattern(regexp = "^(?:\\+55\\s?)?(?:\\(\\d{2}\\)\\s?)?\\d{4,5}-?\\d{4}$", message = "Número de telefone inválido. Ex: (81) 99259-6941")
    @Schema(description = "Patient's phone number", example = "81992596941", required = true)
    private String nrPhone;

    @NotNull(message = "Data de nascimento precisa ser informada! Ex: 2023-08-27T00:00:00")
    @ValidBirthday
    @Schema(description = "Patient's birthdate", example = "2023-08-27T00:00:00", required = true)
    private LocalDateTime dtBirthday;

    @CPF
    @NotBlank(message = "Número do CPF precisa ser informado! Ex: 13834119482")
    @Schema(description = "Patient's CPF (Brazilian individual taxpayer registry number)", example = "13834119482", required = true)
    private String nrCpf;

    @NotBlank(message = "Sexo precisa ser informado! Ex: M (Masculino), F (Feminino) ou I (Indeterminado)")
    @Pattern(regexp = "^(I|M|F)$", message = "Sexo deve ser 'I' (Indeterminado), 'M' (Masculino) ou 'F' (Feminino). Ex: I")
    @Schema(description = "Patient's gender (M: Male, F: Female, I: Indeterminate)", example = "I", required = true)
    private String tpSex;

    @NotBlank(message = "Estado civil precisa ser informado! Ex: S (Solteiro), D (Divorciado), C (Casado), V (Viúvo)")
    @Pattern(regexp = "^(S|D|C|V)$", message = "Estado civil deve ser 'S' (Solteiro), 'D' (Divorciado), 'C' (Casado) ou 'V' (Viúvo). Ex: S")
    @Schema(description = "Patient's marital status", example = "S", required = true)
    private String tpMaritalStatus;

    @NotNull(message = "CEP precisa ser informado! Ex: 53900000")
    @Schema(description = "Patient's ZIP code", example = "53900000", required = true)
    private Long nrCep;

    @Schema(description = "Patient's address", example = "Rua A, 123")
    private String dsAddress;

    @NotBlank(message = "Cor da pele precisa ser informada! Ex: I (Indefinido), P (Preto), B (Branco), P (Pardo), M (Amarelo), D (Indígena)")
    @Pattern(regexp = "^(I|P|B|P|M|D)$", message = "Cor da pele deve ser  I (Indefinido), P (Preto), B (Branco), P (Pardo), M (Amarelo), D (Indígena). Ex: P")
    @Schema(description = "Patient's skin color", example = "P", required = true)
    private String tpSkinColor;

    @NotBlank(message = "Tipo sanguíneo precisa ser informado! Ex: 'A+', 'A-', 'B+', 'B-', 'O+', 'O-', 'AB+' ou 'AB-'")
    @Pattern(regexp = "^(A|B|AB|O)[+-]$", message = "Tipo sanguíneo deve ser 'A+', 'A-', 'B+', 'B-', 'O+', 'O-', 'AB+' ou 'AB-'. Ex: A+")
    @Schema(description = "Patient's blood type", example = "A+", required = true)
    private String tpBlood;
}
