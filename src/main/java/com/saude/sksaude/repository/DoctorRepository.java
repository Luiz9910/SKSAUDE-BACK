package com.saude.sksaude.repository;

import com.saude.sksaude.model.Doctor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DoctorRepository extends JpaRepository<Doctor, Long> {
    @Query(nativeQuery = true, value = "SELECT NR_CPF FROM SKSAUDE.MEDICO WHERE NR_CPF = ?")
    String findByNrCpf(@Param("nrCpf") String nrCpf);

    @Query(nativeQuery = true, value = "SELECT * FROM sksaude.medico WHERE nr_cpf = ?")
    Doctor findDoctorByNrCpf(@Param("nrCpf") String nrCpf);

    @Query(nativeQuery = true, value = "SELECT cd_especialidade FROM SKSAUDE.ESPECIALIDADE WHERE cd_especialidade = ?")
    Long findByCdSpecialty(@Param("cdSpecialty") Integer cdSpeciality);
}
