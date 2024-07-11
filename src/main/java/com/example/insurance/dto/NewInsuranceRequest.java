package com.example.insurance.dto;

public record NewInsuranceRequest(
        String insuranceName,
        Integer insuranceAmount,
        Integer examsDiscount,
        Integer medicinesDiscount
) {
}
