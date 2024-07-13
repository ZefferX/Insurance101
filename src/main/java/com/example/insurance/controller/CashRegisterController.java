package com.example.insurance.controller;

import com.example.insurance.model.CashRegister;
import com.example.insurance.service.CashRegisterService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/cashregister")
@AllArgsConstructor

public class CashRegisterController {

    private final CashRegisterService cashRegisterService;

    @GetMapping()
    public CashRegister getLastCashRegister(){
        return cashRegisterService.getCashRegisterByLastId();
    }

    @PostMapping public String addMoneyToCashRegister(@RequestBody Double money){
        return cashRegisterService.addMoneyToCashRegister(money);
    }

    //Este delete aunque parece ingenioso esta mal hecho, quiza no deba ni usarlo, simplemente en la logica de
    //negocio arreglare lo que hara
    @DeleteMapping("/{Id}") public CashRegister deleteCashRegisterEntry(@PathVariable ("Id") Integer id){
        return cashRegisterService.deleteCashRegisterEntry(id);
    }
}
