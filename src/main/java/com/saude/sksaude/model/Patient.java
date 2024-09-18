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
@Table(name = "PACIENTE", schema = "SKSAUDE")
public class Patient {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_PACIENTE")
    @SequenceGenerator(name = "SEQ_PACIENTE", schema = "SKSAUDE", sequenceName = "SEQ_GENERATOR", allocationSize = 1)
    @Column(name = "CD_PACIENTE")
    private Long cdPatient;

    @Column(name = "NM_PACIENTE")
    private String nmPatient;

    @Column(name = "EMAIL")
    private String email;

    @Column(name = "NR_TELEFONE")
    private String nrPhone;

    @Column(name = "DT_NASCIMENTO")
    private LocalDateTime dtBirthday;

    @Column(name = "NR_CPF")
    private String nrCpf;

    @Column(name = "TP_SEXO")
    private String tpSex;

    @Column(name = "TP_CIVIL")
    private String tpMaritalStatus;

    @Column(name = "NR_CEP")
    private String nrCep;

    @Column(name = "DS_ENDERECO")
    private String dsAddress;

    @Column(name = "NM_CIDADE")
    private String nmCity;

    @Column(name = "TP_LOGRADOURO")
    private String tpPublicPlace;

    @Column(name = "NM_ESTADO")
    private String nmState;

    @Column(name = "TP_COR")
    private String tpSkinColor;

    @Column(name = "TP_SANGUINEO")
    private String tpBlood;

    @Column(name = "SN_ATIVO")
    private String snActive;
}
