package com.devsu.gabrielvaldivieso_inventarios.controllers;

import com.devsu.gabrielvaldivieso_inventarios.dto.*;
import com.devsu.gabrielvaldivieso_inventarios.models.Client;
import com.devsu.gabrielvaldivieso_inventarios.models.Order;
import com.devsu.gabrielvaldivieso_inventarios.models.Product;
import com.devsu.gabrielvaldivieso_inventarios.models.Store;
import com.devsu.gabrielvaldivieso_inventarios.services.ClientService;
import com.devsu.gabrielvaldivieso_inventarios.services.OrderService;
import com.devsu.gabrielvaldivieso_inventarios.services.ProductService;
import com.devsu.gabrielvaldivieso_inventarios.services.StoreService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.*;

@RestController
@RequestMapping("/order")
public class OrderController {

    private final OrderService orderService;
    private final ClientService clientService;
    private final StoreService storeService;
    private final ProductService productService;

    @Value("${order.error.badRequest}")
    private String errorBadRequest;

    @Value("${client.error.notFound}")
    private String errorClientNotFound;

    @Value("${store.error.notFound}")
    private String errorStoreNotFound;

    @Value("${product.error.notFound}")
    private String errorProductNotFound;

    @Value("${order.error.notAvailable}")
    private String errorOrderNotAvailable;

    public OrderController(OrderService orderService, ClientService clientService, StoreService storeService, ProductService productService) {
        this.orderService = orderService;
        this.clientService = clientService;
        this.storeService = storeService;
        this.productService = productService;
    }

    @GetMapping("/getByStoreAndProduct")
    public StoreAndProductAmountSetDTO getByStoreAndProduct() {
        StoreAndProductAmountSetDTO amounts = new StoreAndProductAmountSetDTO();

        Set<Order> allOrders = orderService.findAll();
        Set<Store> allStores = storeService.findAll();
        Set<Product> allProducts = productService.findAll();

        Set<StoreAndProductAmountDTO> orders = new HashSet<>();

        allStores.stream().forEach(store -> {
            allProducts.stream().forEach(product -> {
                StoreAndProductAmountDTO productAmount = new StoreAndProductAmountDTO(store.getId(), product.getId(), 0);
                allOrders.stream().forEach(order -> {
                    if (order.getStore().getId() == store.getId() && order.getProduct().getId() == product.getId()) {
                        productAmount.setAmount(productAmount.getAmount() + order.getAmount());
                    }
                });
                orders.add(productAmount);
            });
        });

        amounts.setAmounts(orders);
        return amounts;
    }

    @GetMapping("/getByStoreAndDate")
    public StoreAndDateOrderSetDTO getByStoreAndDate() {
        StoreAndDateOrderSetDTO orders = new StoreAndDateOrderSetDTO();

        Set<Order> allOrders = orderService.findAll();
        Set<Store> allStores = storeService.findAll();

        Set<StoreAndDateOrderDTO> orderDTOSet = new HashSet<>();

        allStores.stream().forEach(store -> {
            StoreAndDateOrderDTO orderDTO = new StoreAndDateOrderDTO(store.getId(), 0);

            allOrders.stream().forEach(order -> {
                if (order.getStore().getId() == orderDTO.getId()) {
                    orderDTO.setTotalAmount(orderDTO.getTotalAmount() + order.getAmount());
                }
            });

            orderDTOSet.add(orderDTO);
        });

        orders.setStores(orderDTOSet);

        return orders;
    }

    @PostMapping("/create")
    public ResponseEntity create(@RequestBody ClientOrderDTO clientOrder) {
        Map<String, Object> data = new HashMap<>();

        if (clientOrder.getClientId() == null || clientOrder.getOrders() == null || clientOrder.getOrders().size() == 0) {
            data.put("message", errorBadRequest);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(data);
        }

        Client mainClient = clientService.findById(clientOrder.getClientId());

        data.put("clientId", clientOrder.getClientId());
        data.put("orderDate", LocalDateTime.now());

        if (mainClient == null) {
            data.put("message", errorClientNotFound);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(data);
        }

        Set<Object> orders = new HashSet<>();

        clientOrder.getOrders().stream().forEach(order -> {
            Store storeOrder = storeService.findById(order.getStoreId());

            Map<String, Object> storeResponse = new HashMap<>();

            storeResponse.put("storeId", order.getStoreId());

            if (storeOrder == null) {
                storeResponse.put("message", errorStoreNotFound);
                orders.add(storeResponse);
            } else {
                Set<Object> products = new HashSet<>();

                order.getProducts().stream().forEach(product -> {
                    Product productStore = productService.findById(product.getId());

                    Map<String, Object> productResponse = new HashMap<>();

                    productResponse.put("productId", product.getId());

                    if (productStore == null) {
                        productResponse.put("message", errorProductNotFound);
                        products.add(productResponse);
                    } else {
                        Order newOrder = new Order(LocalDateTime.now(), mainClient, storeOrder);
                        newOrder.setProduct(productStore);
                        newOrder.setAmount(product.getAmount());

                        Order saved = orderService.save(newOrder);
                        if (saved == null) {
                            productResponse.put("message", errorOrderNotAvailable);
                        } else {
                            productResponse.put("orderId", saved.getId());
                            productResponse.put("amount", product.getAmount());
                        }
                        products.add(productResponse);
                    }
                });

                storeResponse.put("products", products);

                orders.add(storeResponse);
            }
        });

        data.put("orders", orders);

        return ResponseEntity.status(HttpStatus.OK).body(data);
    }

}
