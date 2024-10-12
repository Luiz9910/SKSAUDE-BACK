package com.saude.sksaude.module.Patient.dto.response;

import com.saude.sksaude.module.Patient.model.Patient;
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
