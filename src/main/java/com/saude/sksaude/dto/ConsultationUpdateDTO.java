package com.saude.sksaude.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ConsultationUpdateDTO {
    private String dsConsultation;
    private LocalDateTime dtConsultation;
    private Long cdSpecialty;
    private Long cdPatient;
    private Long cdDoctor;

    public void toUpperCase() {
        dsConsultation = dsConsultation.toUpperCase();
    }
}
