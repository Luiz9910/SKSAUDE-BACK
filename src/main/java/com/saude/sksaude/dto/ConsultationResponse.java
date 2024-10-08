package com.saude.sksaude.dto;

import com.saude.sksaude.model.Consultation;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ConsultationResponse {
    private String message;
    private Consultation consultation;
}
