package com.carubardd.market.domain.service;

import com.carubardd.market.domain.Purchase;
import com.carubardd.market.domain.repository.PurchaseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PurchaseService {

    @Autowired
    PurchaseRepository purchaseRepository;

    public List<Purchase> getAll(){
        return purchaseRepository.getAll();
    }

    public Optional<List<Purchase>> getByClient(String clientId){
        return purchaseRepository.getByClient(clientId);
    }

    public Optional<Purchase> getByPurchase(int purchaseId) {
        return purchaseRepository.getByPurchase(purchaseId);
    }

    public Purchase save(Purchase purchase){
        return purchaseRepository.save(purchase);
    }

    public boolean delete(int purcharseId) {
        return purchaseRepository.getByPurchase(purcharseId)
                .map(purchase -> {
                    purchaseRepository.delete(purcharseId);
                    return true;
                }).orElse(false);
    }

}
