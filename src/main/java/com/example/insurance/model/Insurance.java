package com.example.insurance.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Insurance {

        @Id
        @SequenceGenerator(
                name = "insurance_id_sequence",
                sequenceName = "insurance_id_sequence",
                allocationSize = 1)
        @GeneratedValue(
                strategy = GenerationType.SEQUENCE,
                generator = "insurance_id_sequence")

        private Integer Id;
        private String insuranceName;
        private Double insuranceAmount;
        private Integer examsDiscount;
        private Integer medicinesDiscount;
    }
