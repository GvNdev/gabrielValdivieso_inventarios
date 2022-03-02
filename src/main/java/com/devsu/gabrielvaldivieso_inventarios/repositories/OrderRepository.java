package com.devsu.gabrielvaldivieso_inventarios.repositories;

import com.devsu.gabrielvaldivieso_inventarios.models.Order;
import org.springframework.data.repository.CrudRepository;

public interface OrderRepository extends CrudRepository<Order, Long> {
}
