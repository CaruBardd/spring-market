package com.carubardd.market.domain.service;

import com.carubardd.market.domain.Product;
import com.carubardd.market.domain.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    public List<Product> getAll() {
        return productRepository.getAll();
    }

    public Optional<Product> getProduct(int productId) {
        return productRepository.getProduct(productId);
    }

    public Optional<List<Product>> getByCategory(int categoryId) {
        return productRepository.getByCategory(categoryId);
    }

    public Product save(Product product) {
        return productRepository.save(product);
    }

    public boolean delete(int productId) {
        // Se obtiene el producto por su ID, si existe se mapea como función, la cual elimina al producto por su ID
        // Y retorna true; en caso de no existir y no poder crear un .map, se usa
        // .orElse, en donde se devuelve false porque el producto no existe y no se ha podido eliminar
        return getProduct(productId).map(product -> {
            productRepository.delete(productId);
            return true;
        }).orElse(false);

        /* Método 2
        Se verifica si el producto está presente con .isPresent()
        if (getProduct(productId).isPresent()) {
            productRepository.delete(productId);
            return true;
        } else {
            return false;
        }
         */
    }

}
