package com.devsu.gabrielvaldivieso_inventarios.dto;

import lombok.Data;

@Data
public class ProductOrderDTO {

    private Long id;
    private Integer amount;

    public ProductOrderDTO(Long id, Integer amount) {
        this.id = id;
        this.amount = amount;
    }
}
