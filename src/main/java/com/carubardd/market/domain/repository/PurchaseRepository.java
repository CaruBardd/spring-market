package com.carubardd.market.domain.repository;

import com.carubardd.market.domain.Purchase;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PurchaseRepository {

    List<Purchase> getAll();
    Optional<Purchase> getByPurchase(int purchaseId);
    Optional<List<Purchase>> getByClient(String clientId);
    Purchase save(Purchase purchase);
    void delete(int purchaseId);

}
