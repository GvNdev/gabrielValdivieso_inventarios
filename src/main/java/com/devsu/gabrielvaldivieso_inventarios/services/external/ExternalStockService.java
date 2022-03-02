package com.devsu.gabrielvaldivieso_inventarios.services.external;

import com.devsu.gabrielvaldivieso_inventarios.dto.StockDTO;
import com.devsu.gabrielvaldivieso_inventarios.models.Order;
import com.devsu.gabrielvaldivieso_inventarios.models.Product;
import com.devsu.gabrielvaldivieso_inventarios.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class ExternalStockService {

    private final ProductRepository productRepository;

    @Autowired
    RestTemplate restTemplate;

    @Value("${stock5Mock}")
    private String stock5Mock;

    public ExternalStockService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public StockDTO getStockRenewal(String url) {
        ResponseEntity<StockDTO> resp = restTemplate.getForEntity(url, StockDTO.class);
        return resp.getStatusCode() == HttpStatus.OK ? resp.getBody() : new StockDTO();
    }

    @Async
    public void updateAsyncStock(Order order, Product ordered) {
        StockDTO syncStock = getStockRenewal(stock5Mock);
        Integer newAsyncStock = ordered.getStock() - order.getAmount() + syncStock.getStock();
        ordered.setStock(newAsyncStock);
        Product newAsyncProduct = productRepository.save(ordered);
    }
}
