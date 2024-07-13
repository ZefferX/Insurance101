package com.example.insurance.dto;

public record NewInsuranceRequest(
        String insuranceName,
        Double insuranceAmount,
        Integer examsDiscount,
        Integer medicinesDiscount
) {
}
