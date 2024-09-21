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
public class PatientUpdateDTO {
    private String nmPatient;
    private String email;
    private String nrPhone;
    private LocalDateTime dtBirthday;
    private String tpSex;
    private String tpMaritalStatus;
    private Long nrCep;
    private String dsAddress;
    private String tpSkinColor;
    private String tpBlood;

    public void toUpperCase() {
        nmPatient = nmPatient.toUpperCase();
        tpSex = tpSex.toUpperCase();
        tpMaritalStatus = tpMaritalStatus.toUpperCase();
        tpSkinColor = tpSkinColor.toUpperCase();
        tpBlood = tpBlood.toUpperCase();
    }
}
