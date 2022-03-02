package com.devsu.gabrielvaldivieso_inventarios.services;

import com.devsu.gabrielvaldivieso_inventarios.dto.ProductDTO;
import com.devsu.gabrielvaldivieso_inventarios.models.Product;

import java.util.Set;

public interface ProductService extends CrudService<Product, Long>{

    Product updateStock(Product product);

    void saveSet(Set<ProductDTO> products);

}
