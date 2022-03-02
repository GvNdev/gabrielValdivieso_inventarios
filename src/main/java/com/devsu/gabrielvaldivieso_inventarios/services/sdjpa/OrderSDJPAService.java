package com.devsu.gabrielvaldivieso_inventarios.services.sdjpa;

import com.devsu.gabrielvaldivieso_inventarios.dto.StockDTO;
import com.devsu.gabrielvaldivieso_inventarios.dto.StoreAndProductAmountDTO;
import com.devsu.gabrielvaldivieso_inventarios.models.Order;
import com.devsu.gabrielvaldivieso_inventarios.models.Product;
import com.devsu.gabrielvaldivieso_inventarios.repositories.OrderRepository;
import com.devsu.gabrielvaldivieso_inventarios.repositories.ProductRepository;
import com.devsu.gabrielvaldivieso_inventarios.services.OrderService;
import com.devsu.gabrielvaldivieso_inventarios.services.external.ExternalStockService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Service
public class OrderSDJPAService implements OrderService {

    private final OrderRepository orderRepository;
    private final ProductRepository productRepository;
    private final ExternalStockService externalStockService;

    @Value("${stock10Mock}")
    private String stock10Mock;

    public OrderSDJPAService(OrderRepository orderRepository, ProductRepository productRepository, ExternalStockService externalStockService) {
        this.orderRepository = orderRepository;
        this.productRepository = productRepository;
        this.externalStockService = externalStockService;
    }

    @Override
    public Set<Order> findAll() {
        Set<Order> orders = new HashSet<>();
        orderRepository.findAll().forEach(orders::add);
        return orders;
    }

    @Override
    public Order findById(Long aLong) {
        return orderRepository.findById(aLong).orElse(null);
    }

    @Override
    public Order save(Order order) {
        Optional<Product> ordered = productRepository.findById(order.getProduct().getId());
        Integer stockPreOrder = ordered.get().getStock();
        Integer newStock = stockPreOrder - order.getAmount();
        if (newStock < -10) {
            return null;
        } else if (newStock >= -10 && newStock < -5) {
            StockDTO syncStock = externalStockService.getStockRenewal(stock10Mock);
            Integer newSyncStock = newStock + syncStock.getStock();
            ordered.get().setStock(newSyncStock);
            Product newSyncProduct = productRepository.save(ordered.get());
        } else if (newStock >= -5 && newStock < 0) {
            externalStockService.updateAsyncStock(order, ordered.get());
        } else {
            ordered.get().setStock(newStock);
            Product newProduct = productRepository.save(ordered.get());
        }
        return orderRepository.save(order);
    }

    @Override
    public void delete(Order order) {
        orderRepository.delete(order);
    }

    @Override
    public void deleteById(Long aLong) {
        orderRepository.deleteById(aLong);
    }
}
