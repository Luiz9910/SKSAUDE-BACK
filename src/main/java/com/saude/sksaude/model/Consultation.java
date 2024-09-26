package com.saude.sksaude.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "CONSULTAS", schema = "SKSAUDE")
public class Consultation {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_CONSULTA" )
    @SequenceGenerator(name = "SEQ_CONSULTA", schema = "SKSAUDE",sequenceName = "SEQ_CONSULTA", allocationSize = 1)
    @Column(name = "CD_CONSULTA")
    private Long cdConsult;

    @Column(name = "DS_CONSULTA")
    private String dsConsultation;

    @Column(name = "DT_CONSULTA")
    private LocalDateTime dtConsultation;

    @Column(name = "CD_ESPECIALIDADE")
    private String cdSpecialty;

    @Column(name = "CD_PACIENTE")
    private String cdPatient;

    @Column(name = "CD_MEDICO")
    private String cdDoctor;

    @Column(name = "SN_ATIVO")
    private String snActive;









}
