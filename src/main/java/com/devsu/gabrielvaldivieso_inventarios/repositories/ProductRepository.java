package com.devsu.gabrielvaldivieso_inventarios.repositories;

import com.devsu.gabrielvaldivieso_inventarios.models.Product;
import org.springframework.data.repository.CrudRepository;

public interface ProductRepository extends CrudRepository<Product, Long> {
}
