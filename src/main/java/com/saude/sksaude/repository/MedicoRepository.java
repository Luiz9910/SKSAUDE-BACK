package com.saude.sksaude.repository;

import com.saude.sksaude.model.Medico;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface MedicoRepository extends JpaRepository<Medico, Long> {
    @Query(nativeQuery = true, value = "SELECT NR_CPF FROM SKSAUDE.MEDICO WHERE NR_CPF = ?")
    String findByNrCpf(@Param("nrCpf") String nrCpf);

    @Query(nativeQuery = true, value = "SELECT cd_especialidade FROM SKSAUDE.ESPECIALIDADE WHERE cd_especialidade = ?")
    String findByCdEspecialidade(@Param("cdEspecialidade") Integer cdEspecialidade);
}
