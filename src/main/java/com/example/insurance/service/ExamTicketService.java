package com.example.insurance.service;

import com.example.insurance.model.ExamTicket;
import com.example.insurance.repository.ExamTicketRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
@AllArgsConstructor
@Service
public class ExamTicketService {
    private ExamTicketRepository examTicketRepository;

    public ExamTicket createNewExamTicket (ExamTicket request){
        return examTicketRepository.save(request);
    }

    public List<ExamTicket> getAllExamTickets (){
        return examTicketRepository.findAll();
    }

    public ExamTicket getTicketById (Integer id){
        return examTicketRepository.findById(id).get();
    }
}
