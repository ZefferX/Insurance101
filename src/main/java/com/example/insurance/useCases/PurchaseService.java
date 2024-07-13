package com.example.insurance.useCases;

import com.example.insurance.dto.NewPurchaseRequest;
import com.example.insurance.model.*;
import com.example.insurance.service.*;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class PurchaseService {
    private PatientService patientService;
    private InsuranceService insuranceService;
    private ExamsService examsService;
    private MedicineService medicineService;
    private CashRegisterService cashRegisterService;
    private MedicineTicketService medicineTicketService;
    private ExamTicketService examTicketService;

    public String buyAProduct (NewPurchaseRequest request) {
        if (!request.productType().equalsIgnoreCase("medicine") &&
                !request.productType().equalsIgnoreCase("exam")) {
            throw new RuntimeException("Invalid product type");
        }

        Patient patient = patientService.getPacientById(request.patientId());
        Insurance insurance = insuranceService.findInsuranceById(patient.getInsurance().getId());

        Integer quantityToBuy = request.quantity();
        if (request.productType().equalsIgnoreCase("medicine")) {
            Medicine medicine = medicineService.getMedicineById(request.productId());
            boolean isQuantityRequiredBiggerThanAvailable = medicine.getQuantity() < request.quantity();
            if (isQuantityRequiredBiggerThanAvailable && !request.isFlexibleClient())
                throw new RuntimeException("Quantity required not met");

            if (isQuantityRequiredBiggerThanAvailable && request.isFlexibleClient()) {
                quantityToBuy = medicine.getQuantity();
            }
            double totalToPay = medicine.getPrice() * request.quantity();
            if (totalToPay > patient.getInsuranceAmount()) {
                throw new RuntimeException("Insufficient insurance amount to pay");
            }
            double insuranceDiscount = ((double) insurance.getMedicinesDiscount() / 100) * totalToPay;
            totalToPay = totalToPay - insuranceDiscount;


            boolean discountByAge = patient.getAge() > 50;
            if (discountByAge){
                double newDiscount = ((double) 10 / 100) * totalToPay;
                totalToPay = totalToPay - newDiscount;
            }

            medicine.setQuantity(medicine.getQuantity() - quantityToBuy);
            patient.setInsuranceAmount(patient.getInsuranceAmount() - totalToPay);

            MedicineTicket ticket = MedicineTicket.builder()
                    .patientId(request.patientId())
                    .patientName(patient.getName())
                    .medicineId(medicine.getId())
                    .medicineName(medicine.getMedicineName())
                    .medicinePrice(medicine.getPrice())
                    .quantity(quantityToBuy)
                    .insuranceName(insurance.getInsuranceName())
                    .totalSale(totalToPay)
                    .build();


            patientService.updatePatientToInternalUse(patient);
            cashRegisterService.addMoneyToCashRegister(totalToPay);
            medicineService.updateMedicineToInternalUse(medicine);
            medicineTicketService.createNewMedicineTicket(ticket);

        }else if (request.productType().equalsIgnoreCase("exam")){
            Exams exams = examsService.findExamById(request.productId());
            boolean isQuantityRequiredBiggerThanAvailable = exams.getQuantity() < request.quantity();
            if (isQuantityRequiredBiggerThanAvailable && !request.isFlexibleClient())
                throw new RuntimeException("Quantity required not met");

            if (isQuantityRequiredBiggerThanAvailable && request.isFlexibleClient()) {
                quantityToBuy = exams.getQuantity();
            }
            double totalToPay = exams.getPrice() * request.quantity();
            if (totalToPay > patient.getInsuranceAmount()) {
                throw new RuntimeException("Insufficient insurance amount to pay");
            }

            double insuranceDiscount = ((double) insurance.getMedicinesDiscount() / 100) * totalToPay;
            totalToPay = totalToPay - insuranceDiscount;


            boolean discountByAge = patient.getAge() > 50;
            if (discountByAge){
                double newDiscount = ((double) 10 / 100) * totalToPay;
                totalToPay = totalToPay - newDiscount;
            }

            exams.setQuantity(exams.getQuantity() - quantityToBuy);
            patient.setInsuranceAmount(patient.getInsuranceAmount() - totalToPay);

            ExamTicket ticket = ExamTicket.builder()
                    .patientId(request.patientId())
                    .patientName(patient.getName())
                    .examId(exams.getId())
                    .examName(exams.getExamName())
                    .examPrice(exams.getPrice())
                    .quantity(quantityToBuy)
                    .insuranceName(insurance.getInsuranceName())
                    .totalSale(totalToPay)
                    .build();

        }
        return "Purchase Successful";
    }
}
