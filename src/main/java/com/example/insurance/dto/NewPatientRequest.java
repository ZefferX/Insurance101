package com.example.insurance.dto;

public record NewPatientRequest(
    String name,
    Integer age,
    Integer insuranceId
) {
}
