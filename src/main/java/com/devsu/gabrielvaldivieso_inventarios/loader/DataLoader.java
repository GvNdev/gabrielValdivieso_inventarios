package com.devsu.gabrielvaldivieso_inventarios.loader;

import com.devsu.gabrielvaldivieso_inventarios.dto.ProductDTO;
import com.devsu.gabrielvaldivieso_inventarios.services.external.ExternalProductService;
import com.devsu.gabrielvaldivieso_inventarios.services.ProductService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Set;

@Component
public class DataLoader implements CommandLineRunner {

    private ExternalProductService externalProductService;
    private ProductService productService;

    public DataLoader(ExternalProductService externalProductService, ProductService productService) {
        this.externalProductService = externalProductService;
        this.productService = productService;
    }

    @Override
    public void run(String... args) throws Exception {
        Set<ProductDTO> products = externalProductService.getMockProducts();
        productService.saveSet(products);
    }

}
