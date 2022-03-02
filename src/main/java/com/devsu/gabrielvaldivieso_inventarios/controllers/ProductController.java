package com.devsu.gabrielvaldivieso_inventarios.controllers;

import com.devsu.gabrielvaldivieso_inventarios.models.Product;
import com.devsu.gabrielvaldivieso_inventarios.services.ProductService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

@RestController
@RequestMapping("/product")
public class ProductController {

    private final ProductService productService;

    @Value("${product.error.notFound}")
    private String errorNotFound;

    @Value("${product.error.badRequest}")
    private String errorBadRequest;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/getAll")
    public ResponseEntity getProducts() {
        Map<String, Object> data = new HashMap<>();
        Set<Product> products = productService.findAll();
        if (products == null) {
            data.put("message", errorNotFound);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(data);
        }
        return ResponseEntity.status(HttpStatus.OK).body(products);
    }

    @PutMapping("/updateStock")
    public ResponseEntity updateStock(@RequestBody Product product) {
        Map<String, Object> data = new HashMap<>();

        if (product.getId() == null || product.getStock() == null) {
            data.put("message", errorBadRequest);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(data);
        }

        Product newProduct = productService.updateStock(product);

        if (newProduct == null) {
            data.put("message", errorNotFound);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(data);
        }

        return ResponseEntity.status(HttpStatus.OK).body(newProduct);

    }

}
