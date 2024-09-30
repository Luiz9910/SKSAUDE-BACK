package com.saude.sksaude.dto;

import com.saude.sksaude.model.Consultation;
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
