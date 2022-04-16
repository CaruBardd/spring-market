package com.carubardd.market.web.controller;

import com.carubardd.market.domain.Purchase;
import com.carubardd.market.domain.service.PurchaseService;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/purchases")
public class PurchaseController {

    @Autowired
    private PurchaseService purchaseService;

    @GetMapping("/show")
    public ResponseEntity<List<Purchase>> getAll() {
        return new ResponseEntity<>(purchaseService.getAll(), HttpStatus.OK);
    }

    @GetMapping("/show/client/{id}")
    public ResponseEntity<List<Purchase>> getByClient(@PathVariable("id") int clientId) {
        return purchaseService.getByClient(clientId)
                .map(compras -> new ResponseEntity<>(compras, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @GetMapping("/show/purchase/{id}")
    public ResponseEntity<Purchase> getByPurchase(@PathVariable("id") int purchaseId) {
        return purchaseService.getByPurchase(purchaseId)
                .map(purchase -> new ResponseEntity<>(purchase, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping("/new")
    public ResponseEntity<Purchase> save(@RequestBody Purchase purchase) {
        return new ResponseEntity<>(purchaseService.save(purchase), HttpStatus.CREATED);
    }

    /*
    NO SE IMPLEMENTA PORQUE HAY UNA LLAVE FORANEA VINCULADA
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") int purchaseId) {
        if(purchaseService.delete(purchaseId))
            return new ResponseEntity<>(HttpStatus.OK);
        else
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    } */

}
