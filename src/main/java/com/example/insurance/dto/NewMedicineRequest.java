package com.example.insurance.dto;

public record NewMedicineRequest (
        String medicineName,
        Double medicinePrice,
        Integer quantity

) {
}
