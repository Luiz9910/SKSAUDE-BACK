package com.saude.sksaude.module.Doctor.dto.response;

import com.saude.sksaude.module.Doctor.model.Doctor;
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
