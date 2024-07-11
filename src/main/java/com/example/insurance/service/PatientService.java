package com.example.insurance.service;

import com.example.insurance.dto.NewPatientRequest;
import com.example.insurance.model.Insurance;
import com.example.insurance.model.Patient;
import com.example.insurance.repository.PatientRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class PatientService {
    private final PatientRepository patientRepository;
    private final InsuranceService insuranceService;

    public List<Patient> getPacients(){
        return patientRepository.findAll();
    }

    public Patient getPacientById(Integer id){
        Patient foundPatient = patientRepository.findById(id).get();
        return foundPatient;
    }

    public void addPacient (NewPatientRequest request){
        Insurance insurance = insuranceService.findInsuranceById(request.insuranceId());
        Patient newPatient = new Patient();
        newPatient.setName(request.name());
        newPatient.setAge(request.age());
        newPatient.setInsuranceAmount(insurance.getInsuranceAmount());
        newPatient.setInsurance(insurance);
        patientRepository.save(newPatient);
    }

    public String deletePacient (Integer id){
        Optional<Patient> pacient = patientRepository.findById(id);
        if (pacient.isEmpty()) return "Patient with id " + id + " not found";
        patientRepository.deleteById(id);
        return "Patient deleted successfully";
    }

    public Patient updatePacient(Integer patientId, NewPatientRequest request){
        Insurance insurance = insuranceService.findInsuranceById(request.insuranceId());

        Patient foundPatient = patientRepository.findById(patientId).get();
        foundPatient.setName(request.name());
        foundPatient.setAge(request.age());
        foundPatient.setInsuranceAmount(insurance.getInsuranceAmount());
        foundPatient.setInsurance(insurance);
        return patientRepository.save(foundPatient);
    }

    public Patient updatePatientToInternalUse(Patient patient)
    {
        return patientRepository.save(patient);

    }
}
