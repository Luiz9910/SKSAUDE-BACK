package com.saude.sksaude.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

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
