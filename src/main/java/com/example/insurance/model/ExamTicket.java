package com.example.insurance.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ExamTicket {
    @Id
    @SequenceGenerator(
            name = "exams_id_sequence",
            sequenceName = "exams_id_sequence",
            allocationSize = 1)
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "exams_id_sequence")

    private Integer Id;
    private Integer patientId;
    private String patientName;
    private Integer examId;
    private String examName;
    private Double examPrice;
    private Integer quantity;
    private String insuranceName;
    private Double totalSale;
}
