package com.saude.sksaude.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
@Entity
@Table(name = "CONSULTAS", schema = "SKSAUDE")
public class Consultation {
    @Id




}
