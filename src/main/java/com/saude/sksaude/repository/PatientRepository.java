package com.saude.sksaude.repository;

import com.saude.sksaude.model.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface PatientRepository extends JpaRepository<Patient, Long> {
    @Query(nativeQuery = true, value = "SELECT NR_CPF FROM SKSAUDE.PACIENTE WHERE NR_CPF = :nrCpf")
    String findByNrCpf(@Param("nrCpf") String nrCpf);
}
