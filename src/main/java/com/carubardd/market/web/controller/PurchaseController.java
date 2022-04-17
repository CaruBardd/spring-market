package com.carubardd.market.web.controller;

import com.carubardd.market.domain.Purchase;
import com.carubardd.market.domain.service.PurchaseService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
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

    @GetMapping("/show/all")
    @ApiOperation("Get all the Purchases")
    @ApiResponse(code = 200, message = "OK")
    public ResponseEntity<List<Purchase>> getAll() {
        return new ResponseEntity<>(purchaseService.getAll(), HttpStatus.OK);
    }

    @GetMapping("/show/client/{id}")
    @ApiOperation("Show the purchases from a Client ID")
    @ApiResponses({
            @ApiResponse(code = 200, message = "OK"),
            @ApiResponse(code = 404, message = "Client not found")
    })
    public ResponseEntity<List<Purchase>> getByClient(
            @ApiParam(value = "Client ID", required = true, example = "4546221")
            @PathVariable("id")
                    String clientId
    ) {
        return purchaseService.getByClient(clientId)
                .map(compras -> new ResponseEntity<>(compras, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @GetMapping("/show/purchase/{id}")
    @ApiOperation("Show a purchase with an ID")
    @ApiResponses({
            @ApiResponse(code = 200, message = "OK"),
            @ApiResponse(code = 404, message = "Purchase Not Found")
    })
    public ResponseEntity<Purchase> getByPurchase(
            @ApiParam(value = "Purchase ID", required = true, example = "3")
            @PathVariable("id")
                    int purchaseId
    ) {
        return purchaseService.getByPurchase(purchaseId)
                .map(purchase -> new ResponseEntity<>(purchase, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping("/new")
    @ApiOperation("Save new purchase")
    @ApiResponse(code = 200, message = "Purchase saved successfully")
    public ResponseEntity<Purchase> save(
            @ApiParam(value = "JSON of the purchase", required = true)
            @RequestBody
                    Purchase purchase
    ) {
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
