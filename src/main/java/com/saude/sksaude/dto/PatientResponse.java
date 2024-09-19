package com.saude.sksaude.dto;

import com.saude.sksaude.model.Patient;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PatientResponse {
    private String message;
    private Patient patient;
}
