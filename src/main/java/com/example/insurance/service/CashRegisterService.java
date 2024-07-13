package com.example.insurance.service;

import com.example.insurance.model.CashRegister;
import com.example.insurance.repository.CashRegisterRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class CashRegisterService {
    private final CashRegisterRepository cashRegisterRepository;

    public CashRegister getCashRegisterByLastId(){
        return cashRegisterRepository.findFirstByOrderByIdDesc();
    }

    // Esta deberia usarla para el momento de venta, haciendo asi que tome el valor de la
    // venta y lo sume automaticamente a la caja creando un nuevo registro
    /*
    public CashRegister addMoneyToCashRegister(Integer money){
        CashRegister newCashRegister = cashRegisterRepository.findFirstByOrderByIdDesc();
        newCashRegister.setTotal(newCashRegister.getTotal() + money);
        return cashRegisterRepository.save(newCashRegister);
    }
    Este por alguna razon no funciono, seguramente tiene algo mal*/

    public String addMoneyToCashRegister(Double money){
        Double currentMoney = getCashRegisterByLastId().getTotal();
        Double newTotal = currentMoney + money;
        CashRegister cashRegister = new CashRegister();
        cashRegister.setTotal(newTotal);

        cashRegisterRepository.save(cashRegister);
        return "Money added successfully";
    }

    //Este me costo, pero creo que esta bien y valio la pena
    //Edit 2: Si, pero es una pendejada, porque no sirve borrar una entrada con un total de caja que tenia en ese
    //momento, necesitariamos es borrar cuanto se le sumo, hay que pensarlo nuevamente
    public CashRegister deleteCashRegisterEntry(Integer id){
        Optional<CashRegister> foundCashRegisterEntry = cashRegisterRepository.findById(id);
        if (foundCashRegisterEntry.isEmpty()) throw new RuntimeException();
        CashRegister newCashRegister = foundCashRegisterEntry.get();
        Double lastCashEntry = cashRegisterRepository.findFirstByOrderByIdDesc().getTotal();
        Double newCashRegisterTotal = lastCashEntry - newCashRegister.getTotal();
        newCashRegister.setTotal(newCashRegisterTotal);
        cashRegisterRepository.deleteById(id);
        return cashRegisterRepository.save(newCashRegister);


    }

}
