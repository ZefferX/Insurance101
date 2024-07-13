package com.example.insurance.dto;

public record NewPurchaseRequest(
        Integer patientId,
        Integer productId,
        Integer quantity,
        String productType,
        Boolean isFlexibleClient
) {
}
