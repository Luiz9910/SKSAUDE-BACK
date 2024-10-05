package com.saude.sksaude.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DoctorUpdateDTO {
    private String nmDoctor;
    private String crm;
    private Integer cdSpecialty;

    public void toUpperCase() {
        nmDoctor = nmDoctor.toUpperCase();
        crm = crm.toUpperCase();
    }
}
