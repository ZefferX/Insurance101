package com.example.insurance.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Medicine {
    @Id
    @SequenceGenerator(
            name = "medicine_id_sequence",
            sequenceName = "medicine_id_sequence",
            allocationSize = 1)
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "medicine_id_sequence")

    private Integer Id;
    private String medicineName;
    private Integer quantity;
    private Integer price;
}

