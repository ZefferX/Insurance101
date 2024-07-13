package com.example.insurance.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Patient {

    @Id
    @SequenceGenerator(
            name = "patient_id_sequence",
            sequenceName = "patient_id_sequence",
            allocationSize = 1)
    @GeneratedValue(
            strategy = GenerationType.IDENTITY,
            generator = "patient_id_sequence")
    private Integer id;

    private String name;
    private Integer age;
    private Double insuranceAmount;

    @ManyToOne
    @JoinColumn(name = "insurance_id", nullable = false)
    private Insurance insurance;

    //PARA CONSULTAR A JULIO, por alguna razon el transforma la variable insuranceAmount a insurance_amount en DB
    //intente este @Column que al parecer lo arreglaba, pero siguio sin funcionar




}
