package com.saude.sksaude.service;

import com.saude.sksaude.dto.DoctorDTO;
import com.saude.sksaude.exception.hadleException.ConflictException;
import com.saude.sksaude.exception.hadleException.NoContentException;
import com.saude.sksaude.exception.hadleException.NotFoundException;
import com.saude.sksaude.model.Doctor;
import com.saude.sksaude.repository.DoctorRepository;
import com.saude.sksaude.repository.customer.DoctorCustom;
import com.saude.sksaude.utils.DefaultValueDoctor;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class DoctorService {
    private ModelMapper mapper = new ModelMapper();

    @Autowired
    private final DoctorRepository doctorRepository;

    @Autowired
    private final DoctorCustom doctorCustom;

    public Doctor saveDoctor(DoctorDTO doctorDTO){
        if (doctorRepository.findByNrCpf(doctorDTO.getNrCpf()) != null) {
            throw new ConflictException("Já existe esse medico no sistema");
        }

        if (doctorRepository.findByCdSpecialty(doctorDTO.getCdSpecialty()) == null) {
            throw new ConflictException("Especialidade não encontrada");
        }

        doctorDTO.toUpperCase();
        Doctor doctor = mapper.map(doctorDTO, Doctor.class);
        doctor.setSnActive(DefaultValueDoctor.snActive);

        doctorRepository.save(doctor);
        return doctor;

    }

    public Doctor getDoctorByNrCpf(String nrCpf) {
        if(doctorRepository.findDoctorByNrCpf(nrCpf) == null){
            throw new NotFoundException("CPF não encontrado");
        }
        return this.doctorRepository.findDoctorByNrCpf(nrCpf);
    }

    public List<Doctor> getAllDoctorFilter(String nmDoctor, Integer cdSpecialty){
        return doctorCustom.findAllDoctorByFilter(nmDoctor.toUpperCase(), cdSpecialty);
    }

    public ResponseEntity<List<Doctor>> getAllDoctors(){
        List<Doctor> doctorList = doctorRepository.findAll();

        if (doctorList.isEmpty()){
            throw new NoContentException("Nenhum médico encontrado.");
        }
        return ResponseEntity.ok(doctorList);
    }



}
