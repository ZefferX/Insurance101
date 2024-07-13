package com.example.insurance.service;

import com.example.insurance.dto.NewMedicineRequest;
import com.example.insurance.model.Medicine;
import com.example.insurance.repository.MedicineRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Service

public class MedicineService {

    private final MedicineRepository medicineRepository;

    public void addMedicine(NewMedicineRequest request){
        Medicine newMedicine = new Medicine();
        newMedicine.setMedicineName(request.medicineName());
        newMedicine.setPrice(request.medicinePrice());
        newMedicine.setQuantity(request.quantity());
        medicineRepository.save(newMedicine);
    }

    public String deleteMedicine(Integer id){
        Optional<Medicine> foundMedicine = medicineRepository.findById(id);
        if (foundMedicine.isEmpty()) return "Medicine not found";
        medicineRepository.deleteById(id);
        return "Medicine with id " + id + " deleted succesfully";
    }

    public Medicine updateMedicine (Integer id, NewMedicineRequest request){
        Optional<Medicine> updateMedicine = medicineRepository.findById(id);
        if (updateMedicine.isEmpty()) throw new RuntimeException();
        Medicine medicine = updateMedicine.get();
        medicine.setMedicineName(request.medicineName());
        medicine.setPrice(request.medicinePrice());
        medicine.setQuantity(request.quantity());
        return medicineRepository.save(medicine);
    }

    public Medicine getMedicineById (Integer id){
        Optional<Medicine> foundMedicine = medicineRepository.findById(id);
        if (foundMedicine.isEmpty()) throw new RuntimeException();
        return foundMedicine.get();
    }

    public List<Medicine> getAllMedicines (){
        return medicineRepository.findAll();
    }

    public Medicine updateMedicineToInternalUse (Medicine medicine){
        return medicineRepository.save(medicine);
    }
}
