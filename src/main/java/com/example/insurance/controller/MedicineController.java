package com.example.insurance.controller;

import com.example.insurance.dto.NewMedicineRequest;
import com.example.insurance.model.Medicine;
import com.example.insurance.service.MedicineService;
import lombok.AllArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/medicine")
@AllArgsConstructor
public class MedicineController {

    private final MedicineService medicineService;

    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("/{Id}")
    public Medicine getMedicineById(@PathVariable("Id")Integer id){
        return medicineService.getMedicineById(id);
    }

    @GetMapping public List<Medicine> getAllMedicines(){
        return medicineService.getAllMedicines();
    }

    @PostMapping public void createMedicine(@RequestBody NewMedicineRequest request){
        medicineService.addMedicine(request);
    }

    @DeleteMapping("/{Id}") public String deleteMedicine(@PathVariable("Id") Integer id){
        return medicineService.deleteMedicine(id);
    }

    @PutMapping("/{Id}") public Medicine updateMedicine(@PathVariable("Id") Integer id, @RequestBody NewMedicineRequest request){
        return medicineService.updateMedicine(id, request);
    }

}
