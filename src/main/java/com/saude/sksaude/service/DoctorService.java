package com.saude.sksaude.service;

import com.saude.sksaude.dto.DoctorDTO;
import com.saude.sksaude.dto.DoctorUpdateDTO;
import com.saude.sksaude.dto.PatientUpdateDTO;
import com.saude.sksaude.exception.hadleException.BadRequestException;
import com.saude.sksaude.exception.hadleException.ConflictException;
import com.saude.sksaude.exception.hadleException.NoContentException;
import com.saude.sksaude.exception.hadleException.NotFoundException;
import com.saude.sksaude.model.Doctor;
import com.saude.sksaude.model.Patient;
import com.saude.sksaude.repository.DoctorRepository;
import com.saude.sksaude.repository.customer.DoctorCustom;
import com.saude.sksaude.utils.DefaultValueDoctor;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.print.Doc;
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

    public Doctor actionDoctor(String nrCpf, String action){
        if (nrCpf == null || action == null) {
            throw new BadRequestException("Parâmetros 'nrCpf' e 'action' são obrigatórios.");
        }

        action = action.toUpperCase().trim();

        if (!action.equals("S") && !action.equals("N")) {
            throw new BadRequestException("Ação inválida: " + action + ". Deve ser 'S' ou 'N'.");
        }

        Doctor doctor = doctorRepository.findDoctorByNrCpf(nrCpf);

        if (action.trim().equals(doctor.getSnActive())) {
            throw new ConflictException("O médico está " + doctor.getSnActive() + " no sistema. Altere a ação (S ou N) para a gente trocar no sistema");
        }

        doctor.setSnActive(action);
        return doctorRepository.save(doctor);
    }

    public Doctor updateDoctor(String nrCpf, @Valid DoctorUpdateDTO doctorUpdateDTO) {
        Doctor existingDoctor = doctorRepository.findDoctorByNrCpf(nrCpf);
        doctorUpdateDTO.toUpperCase();
        existingDoctor = this.setDoctorUpdate(doctorUpdateDTO, existingDoctor);
        return doctorRepository.save(existingDoctor);
    }

    public Doctor setDoctorUpdate(DoctorUpdateDTO doctorUpdateDTO, Doctor existingDoctor) {
        if (doctorUpdateDTO.getNmDoctor() != null) {
            existingDoctor.setNmDoctor(doctorUpdateDTO.getNmDoctor());
        }

        if (doctorUpdateDTO.getCrm() != null) {
            existingDoctor.setCrm(doctorUpdateDTO.getCrm());
        }

        if (doctorUpdateDTO.getCdSpecialty() != null) {
            existingDoctor.setCdSpecialty(doctorUpdateDTO.getCdSpecialty());
        }

        return existingDoctor;
    }

}


