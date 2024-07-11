package com.example.insurance.repository;

import com.example.insurance.model.Exams;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ExamsRepository extends JpaRepository<Exams, Integer> {
}
