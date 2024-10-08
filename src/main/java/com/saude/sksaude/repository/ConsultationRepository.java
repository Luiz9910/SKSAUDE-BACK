package com.saude.sksaude.repository;

import com.saude.sksaude.model.Consultation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface ConsultationRepository extends JpaRepository<Consultation, Long> {

    @Query(nativeQuery = true, value = "SELECT * FROM SKSAUDE.CONSULTA WHERE CD_PACIENTE = (" +
            "SELECT CD_PACIENTE FROM SKSAUDE.PACIENTE WHERE NR_CPF = ?)")
    Consultation findByCpfPatient(@Param("nrCpf") String nrCpf);

    @Query(nativeQuery = true, value = "SELECT * FROM SKSAUDE.CONSULTA WHERE CD_PACIENTE = (" +
            "SELECT CD_PACIENTE FROM SKSAUDE.PACIENTE WHERE NR_CPF = ?)")
    List<Consultation> findAllByCpfPatient(@Param("nrCpf") String nrCpf);


    @Query(nativeQuery = true, value = "SELECT CD_PACIENTE FROM SKSAUDE.PACIENTE WHERE CD_PACIENTE = ?")
    Long findByCdPatient(@Param("cdPatient") Long cdPatient);

    @Query(nativeQuery = true, value = "SELECT * FROM SKSAUDE.CONSULTA WHERE CD_CONSULTA = ?")
    Consultation findConsultation(@Param("cdConsultation") Long cdConsultation);

    @Query(nativeQuery = true, value = "SELECT CD_ESPECIALIDADE FROM SKSAUDE.ESPECIALIDADE WHERE CD_ESPECIALIDADE = ?")
    Long findByCdSpecialty(@Param("cdSpecialty") Long cdSpeciality);

    @Query(nativeQuery = true, value = "SELECT CD_MEDICO FROM SKSAUDE.MEDICO WHERE CD_MEDICO = ?")
    Long findCdDoctor(@Param("cdDoctor") Long cdDoctor);
}




