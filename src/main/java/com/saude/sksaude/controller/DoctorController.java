package com.saude.sksaude.controller;

import com.saude.sksaude.dto.*;
import com.saude.sksaude.model.Doctor;
import com.saude.sksaude.service.DoctorService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Validated
@RequiredArgsConstructor
@RestController
@RequestMapping("/doctor")
public class DoctorController {

    private final DoctorService doctorService;

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public DoctorResponse saveDoctor(@RequestBody @Valid DoctorDTO doctorDTO){
        return new DoctorResponse("medico foi salvo com sucesso",
                this.doctorService.saveDoctor(doctorDTO)
        );
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("{nrCpf}")
    public DoctorResponse getDoctorByNrCpf(@PathVariable(value = "nrCpf") String nrCpf) {
        return new DoctorResponse("Dados do Médico.",
                this.doctorService.getDoctorByNrCpf(nrCpf));
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("doctors/filter")
    public DoctorListResponse getAllDoctorFilter(
            @RequestParam(required = false) String nmDoctor,
            @RequestParam(required = false) Integer cdSpecialty) {

        return new DoctorListResponse("Médicos da especialidade:",
                this.doctorService.getAllDoctorFilter(nmDoctor, cdSpecialty));
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("doctors")
    public DoctorListResponse getAllDoctors() {
        return new DoctorListResponse("Todos os médiocs:",
                this.doctorService.getAllDoctors());
    }

    @ResponseStatus(HttpStatus.OK)
    @PatchMapping("action/")
    public DoctorResponse actionDoctor(@RequestParam(name = "nrCpf") String nrCpf, @RequestParam("action") String action){
        return new DoctorResponse("Ação para ativar ou inativar o paciente foi realizada com sucesso.",
                this.doctorService.actionDoctor(nrCpf, action));
    }

    @ResponseStatus(HttpStatus.OK)
    @PutMapping("{nrCpf}")
    public DoctorResponse updateDoctor(@PathVariable(value = "nrCpf") String nrCpf, @RequestBody @Valid DoctorUpdateDTO doctorUpdateDTO) {
        return new DoctorResponse("Dados do paciente atualizado com suceso.",
                this.doctorService.updateDoctor(nrCpf, doctorUpdateDTO));
    }

}
