package com.saude.sksaude.dto;

import com.saude.sksaude.model.Medico;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MedicoResponse {
    private String message;
    private Medico medico;
}
