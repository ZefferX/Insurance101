package com.example.insurance.repository;

import com.example.insurance.model.ExamTicket;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ExamTicketRepository extends JpaRepository<ExamTicket, Integer> {
}
