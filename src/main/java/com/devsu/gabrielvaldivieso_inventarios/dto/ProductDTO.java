package com.devsu.gabrielvaldivieso_inventarios.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
public class ProductDTO {

    private Long id;
    private String cod;
    private String name;
    private BigDecimal price;
    private Integer stock;

    public ProductDTO(Long id) {
        super();
        this.id = id;
    }
}
