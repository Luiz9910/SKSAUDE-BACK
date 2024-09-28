package com.saude.sksaude.dto;

import com.saude.sksaude.model.Doctor;
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
