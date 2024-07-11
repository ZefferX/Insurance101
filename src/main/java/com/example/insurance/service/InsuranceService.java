package com.example.insurance.service;

import com.example.insurance.dto.NewInsuranceRequest;
import com.example.insurance.model.Insurance;
import com.example.insurance.repository.InsuranceRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class InsuranceService {

    private final InsuranceRepository insuranceRepository;

    public String addNewInsurance(NewInsuranceRequest request){
        Insurance newInsurance = new Insurance();
        newInsurance.setInsuranceName(request.insuranceName());
        newInsurance.setInsuranceAmount(request.insuranceAmount());
        newInsurance.setExamsDiscount(request.examsDiscount());
        newInsurance.setMedicinesDiscount(request.medicinesDiscount());
        insuranceRepository.save(newInsurance);
        return "Insurance created successfully";
    }

    public List<Insurance> getAllInsurances(){
        return insuranceRepository.findAll();
    }

    public Insurance findInsuranceById(Integer id){
        Optional<Insurance> foundInsurance = insuranceRepository.findById(id);
        if (foundInsurance.isEmpty()) throw new RuntimeException();
        return foundInsurance.get();
    }
}
