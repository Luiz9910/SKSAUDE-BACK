package com.saude.sksaude.repository;

import com.saude.sksaude.model.Consultation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ConsultationRepository extends JpaRepository<Consultation, Long> {

    @Query(nativeQuery = true, value = "SELECT cd_Paciente FROM sksaude.consulta  WHERE  cd_Paciente = ?")
    String findByCdPaciente(@Param( "cdPatient" ) String cdPatient );

    @Query(nativeQuery = true, value = "SELECT * FROM sksaude.consulta WHERE dt_consulta")
}
