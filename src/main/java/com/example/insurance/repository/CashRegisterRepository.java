package com.example.insurance.repository;

import com.example.insurance.model.CashRegister;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CashRegisterRepository extends JpaRepository<CashRegister, Integer> {
    CashRegister findFirstByOrderByIdDesc();
}
