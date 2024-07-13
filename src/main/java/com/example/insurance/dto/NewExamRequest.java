package com.example.insurance.dto;

public record NewExamRequest(
        String name,
        Integer price,
        Integer quantity
) {

}
