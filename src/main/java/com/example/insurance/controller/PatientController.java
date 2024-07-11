package com.example.insurance.controller;

import com.example.insurance.dto.NewPatientRequest;
import com.example.insurance.model.Patient;
import com.example.insurance.service.PatientService;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/patient")
@AllArgsConstructor
public class PatientController {
    private final PatientService patientService;

    @GetMapping
    public List<Patient> getPacients(){
        return patientService.getPacients();
    }

    @GetMapping("/{Id}")
    public Patient getPacientById(@PathVariable("Id")Integer id){
        return patientService.getPacientById(id);
    }

    @PostMapping
    public void addPacient (@RequestBody NewPatientRequest request){
        patientService.addPacient(request);
    }

    @PutMapping("/{Id}")
    public Patient updatePacientById(@PathVariable("Id") Integer id, @RequestBody NewPatientRequest request){
        return patientService.updatePacient(id, request);
    }

    @DeleteMapping("/{Id}")
    public String deletePacient (@PathVariable("Id") Integer id){
        return patientService.deletePacient(id);
    }
}
