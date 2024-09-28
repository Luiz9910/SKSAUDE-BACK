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
    @GetMapping("doctor/filter")
    public DoctorListResponse getAllDoctorFilter(
            @RequestParam(required = false) String nmDoctor,
            @RequestParam(required = false) Integer cdSpecialty) {

        return new DoctorListResponse("Médicos da especialidade:",
                this.doctorService.getAllDoctorFilter(nmDoctor, cdSpecialty));
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/all")
    public ResponseEntity<DoctorListResponse> getAllDoctors() {
        ResponseEntity<List<Doctor>> doctorResponse = this.doctorService.getAllDoctors();

        if (doctorResponse.getStatusCode() == HttpStatus.NO_CONTENT) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT)
                    .body(new DoctorListResponse("Nenhum médico encontrado", null));
        }

        List<Doctor> doctors = doctorResponse.getBody();
        DoctorListResponse response = new DoctorListResponse("Segue a lista de médicos", doctors);

        return ResponseEntity.ok(response);
    }


}
