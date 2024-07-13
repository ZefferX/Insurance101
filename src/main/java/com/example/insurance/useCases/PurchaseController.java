package com.example.insurance.useCases;

import com.example.insurance.dto.NewPurchaseRequest;
import com.example.insurance.model.MedicineTicket;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/usecases")
public class PurchaseController {

    private PurchaseService purchaseService;

    @PostMapping("/buyproduct")
    public String buyProduct(@RequestBody NewPurchaseRequest request){
        return purchaseService.buyAProduct(request);
    }
}
