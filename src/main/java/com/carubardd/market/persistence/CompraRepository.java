package com.carubardd.market.persistence;

import com.carubardd.market.domain.Purchase;
import com.carubardd.market.domain.repository.PurchaseRepository;
import com.carubardd.market.persistence.crud.CompraCrudRepository;
import com.carubardd.market.persistence.entity.Compra;
import com.carubardd.market.persistence.mapper.PurchaseMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class CompraRepository implements PurchaseRepository {

    @Autowired
    private CompraCrudRepository compraCrudRepository;
    @Autowired
    private PurchaseMapper mapper;

    @Override
    public List<Purchase> getAll() {
        return mapper.toPurchases((List<Compra>) compraCrudRepository.findAll());
    }

    @Override
    public Optional<Purchase> getByPurchase(int purchaseId) {
        return compraCrudRepository.findById(purchaseId)
                .map(compra -> mapper.toPurchase(compra));
    }

    @Override
    public Optional<List<Purchase>> getByClient(int clientId) {
        return compraCrudRepository.findByIdCliente(clientId)
                .map(compras -> mapper.toPurchases(compras));
    }

    @Override
    public Purchase save(Purchase purchase) {
        Compra compra = mapper.toCompra(purchase);
        compra.getProductos().forEach(producto -> producto.setCompra(compra));
        return mapper.toPurchase(compraCrudRepository.save(compra));
    }

    @Override
    public void delete(int purchaseId) {
        compraCrudRepository.deleteById(purchaseId);
    }
}
