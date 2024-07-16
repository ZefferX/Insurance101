package com.example.insurance.controller;

import com.example.insurance.dto.NewInsuranceRequest;
import com.example.insurance.model.Insurance;
import com.example.insurance.service.InsuranceService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("api/v1/insurance")

public class InsuranceController {
    private final InsuranceService insuranceService;

    @GetMapping("/{Id}") public Insurance getInsuranceById(@PathVariable ("Id") Integer id){
        return insuranceService.findInsuranceById(id);
    }

    @GetMapping() public List<Insurance> getAllInsurances(){
        return insuranceService.getAllInsurances();
    }

    @PostMapping() public String createNewInsurance(@RequestBody NewInsuranceRequest request){
        return insuranceService.addNewInsurance(request);
    }
}
