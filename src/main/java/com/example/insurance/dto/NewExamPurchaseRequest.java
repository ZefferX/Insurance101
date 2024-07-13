package com.example.insurance.dto;

public record NewExamPurchaseRequest(
        Integer clientId,
        Integer examId,
        Integer quantity,
        Boolean isFlexibleClient
) {
}
