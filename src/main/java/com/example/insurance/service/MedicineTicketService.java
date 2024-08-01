package com.example.insurance.service;

import com.example.insurance.model.MedicineTicket;
import com.example.insurance.repository.MedicineTicketRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@AllArgsConstructor
@Service

public class MedicineTicketService {
    private MedicineTicketRepository medicineTicketRepository;

    public MedicineTicket createNewMedicineTicket (MedicineTicket request){
        return medicineTicketRepository.save(request);
    }

    public List<MedicineTicket> getAllMedicineTickets (){
        return medicineTicketRepository.findAll();

    }

    public MedicineTicket getMedicineTicketById (Integer id){
        return medicineTicketRepository.findById(id).get();

    }
}
