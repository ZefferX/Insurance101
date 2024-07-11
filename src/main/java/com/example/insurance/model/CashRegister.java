package com.example.insurance.model;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
public class CashRegister {
    @Id
    @SequenceGenerator(
            name = "cashRegister_id_sequence",
            sequenceName = "cashRegister_id_sequence",
            allocationSize = 1)
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "cashRegister_id_sequence")
    private Integer id;

    private Integer total;
}
