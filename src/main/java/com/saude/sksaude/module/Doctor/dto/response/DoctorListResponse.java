package com.saude.sksaude.module.Doctor.dto.response;

import com.saude.sksaude.module.Doctor.model.Doctor;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DoctorListResponse {
    private String message;
    private List<Doctor> doctors;
}
