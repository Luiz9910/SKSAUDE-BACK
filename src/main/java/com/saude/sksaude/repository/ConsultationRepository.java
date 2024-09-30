package com.saude.sksaude.repository;

import com.saude.sksaude.model.Consultation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.LocalDateTime;


@Repository
public interface ConsultationRepository extends JpaRepository<Consultation, Long> {

    @Query(nativeQuery = true, value = "SELECT CD_PACIENTE FROM SKSAUDE.CONSULTA WHERE cd_paciente = ?")
    Long findByCdPatient(@Param("cdPatient")Long cdPatient);

    @Query(nativeQuery = true, value = "SELECT CD_ESPECIALIDADE FROM SKSAUDE.CONSULTA WHERE cd_especialidade = ?")
    Long findByCdSpecialty(@Param("cdSpecialty") Long cdSpeciality);

    @Query(nativeQuery = true, value = "SELECT CD_MEDICO FROM SKSAUDE.CONSULTA WHERE cd_medico = ?")
    Long findCdDoctor(@Param("cdDoctor") Long cdDoctor);


}




