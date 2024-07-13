package com.example.insurance.dto;

public record NewMedicinePurchaseRequest(
        Integer clientId,
        Integer medicineId,
        Integer quantity,
        Boolean isFlexibleClient
) {
}
