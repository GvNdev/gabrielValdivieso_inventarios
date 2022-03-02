package com.devsu.gabrielvaldivieso_inventarios.services.external;

import com.devsu.gabrielvaldivieso_inventarios.dto.ProductDTO;
import com.devsu.gabrielvaldivieso_inventarios.dto.ProductSetDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.HashSet;
import java.util.Set;

@Service
public class ExternalProductService {

    @Autowired
    private RestTemplate restTemplate;

    @Value("${productListMock}")
    private String productListMock;

    public Set<ProductDTO> getMockProducts() {
        ResponseEntity<ProductSetDTO> resp = restTemplate.getForEntity(productListMock, ProductSetDTO.class);
        return resp.getStatusCode() == HttpStatus.OK ? resp.getBody().getProds() : new HashSet<ProductDTO>();
    }

}
