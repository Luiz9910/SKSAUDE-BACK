package com.saude.sksaude.dto;

import com.saude.sksaude.model.Doctor;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DoctorResponse {
    private String message;
    private Doctor doctor;
}
