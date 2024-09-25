package com.saude.sksaude.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name ="MEDICO", schema = "SKSAUDE")
public class Medico {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_MEDICO")
    @SequenceGenerator(name = "SEQ_MEDICO", schema = "SKSAUDE", sequenceName = "SEQ_GENERATOR", allocationSize = 1)
    @Column(name = "cd_medico")
    private Long cdMedico;

    @Column(name = "nm_medico")
    private String nmMedico;

    @Column(name = "nr_cpf")
    private String nrCpf;

    @Column(name = "crm")
    private String crm;

    @Column(name = "cd_especialidade")
    private Integer cdEspecialidade;

    @Column(name = "sn_ativo")
    private String snActive;

}
