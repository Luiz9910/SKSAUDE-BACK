package com.saude.sksaude.module.Consultation.dto.response;

import com.saude.sksaude.module.Consultation.model.Consultation;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ConsultationListResponse {
    private String message;
    private List<Consultation> consultation;
}
