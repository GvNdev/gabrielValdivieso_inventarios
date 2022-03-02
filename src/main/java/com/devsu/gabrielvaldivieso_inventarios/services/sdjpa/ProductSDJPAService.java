package com.devsu.gabrielvaldivieso_inventarios.services.sdjpa;

import com.devsu.gabrielvaldivieso_inventarios.dto.ProductDTO;
import com.devsu.gabrielvaldivieso_inventarios.models.Product;
import com.devsu.gabrielvaldivieso_inventarios.repositories.ProductRepository;
import com.devsu.gabrielvaldivieso_inventarios.services.ProductService;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class ProductSDJPAService implements ProductService {

    private final ProductRepository productRepository;

    public ProductSDJPAService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public Set<Product> findAll() {
        Set<Product> products = new HashSet<>();
        productRepository.findAll().forEach(products::add);
        return products;
    }

    @Override
    public Product findById(Long aLong) {
        return productRepository.findById(aLong).orElse(null);
    }

    @Override
    public Product save(Product object) {
        return productRepository.save(object);
    }

    @Override
    public void saveSet(Set<ProductDTO> products) {
        products.stream().map(p -> {
            return new Product(p.getId(), p.getCod(), p.getName(), p.getPrice(), p.getStock());
        }).collect(Collectors.toList()).forEach(product -> save(product));
    }

    @Override
    public Product updateStock(Product product) {
        Optional<Product> updated = productRepository.findById(product.getId());
        if (!updated.isEmpty()) {
            Product newStock = updated.get();
            newStock.setStock(product.getStock());
            return productRepository.save(newStock);
        } else {
            return null;
        }
    }

    @Override
    public void delete(Product object) {

    }

    @Override
    public void deleteById(Long aLong) {

    }
}
