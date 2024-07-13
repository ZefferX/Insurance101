package com.example.insurance.dto;

public record NewExamRequest(
        String name,
        Double price,
        Integer quantity
) {

}
