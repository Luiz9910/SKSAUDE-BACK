package com.saude.sksaude.repository;

import com.saude.sksaude.model.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@Repository
public interface PatientRepository extends JpaRepository<Patient, Long>  {
    @Query(nativeQuery = true, value = "SELECT nr_cpf FROM sksaude.paciente WHERE nr_cpf = ?")
    String findByNrCpf(@Param("nrCpf") String nrCpf);

    @Query(nativeQuery = true, value = "SELECT * FROM sksaude.paciente WHERE nr_cpf = ?")
    Patient findPatientByNrCpf(@Param("nrCpf") String nrCpf);

    @Query(nativeQuery = true, value = "SELECT email FROM sksaude.paciente WHERE email = ?")
    String findByEmail(@Param("email") String email);

    @Query(nativeQuery = true, value = "SELECT REGEXP_REPLACE(nr_telefone, '[^0-9]', '', 'g') AS nr_telefone" +
            "   FROM SKSAUDE.paciente" +
            "   WHERE REGEXP_REPLACE(nr_telefone, '[^0-9]', '', 'g') = ?")
    String findByNrPhone(@Param("nrPhone") String nrPhone);
}
