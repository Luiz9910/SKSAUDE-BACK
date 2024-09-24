package com.saude.sksaude.dto;

import com.saude.sksaude.model.Patient;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PatientListResponse {
    private String message;
    private List<Patient> patient;
}

