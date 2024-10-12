package com.saude.sksaude.module.Consultation.dto.response;

import com.saude.sksaude.module.Consultation.model.Consultation;
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
