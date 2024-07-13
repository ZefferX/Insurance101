package com.example.insurance.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Exams {

    @Id
    @SequenceGenerator(
            name = "exams_id_sequence",
            sequenceName = "exams_id_sequence",
            allocationSize = 1)
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "exams_id_sequence")

    private Integer Id;
    private String examName;
    private Integer quantity;
    private Integer price;

}
