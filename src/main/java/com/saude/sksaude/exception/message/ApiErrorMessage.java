package com.saude.sksaude.exception.message;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ApiErrorMessage {
    private Instant timestamp;
    private String error;
    private String messagem;
    private String path;
}