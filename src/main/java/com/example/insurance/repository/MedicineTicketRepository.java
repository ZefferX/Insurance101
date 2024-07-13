package com.example.insurance.repository;

import com.example.insurance.model.MedicineTicket;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MedicineTicketRepository extends JpaRepository<MedicineTicket, Integer> {
}
