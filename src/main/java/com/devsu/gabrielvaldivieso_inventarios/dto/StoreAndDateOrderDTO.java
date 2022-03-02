package com.devsu.gabrielvaldivieso_inventarios.dto;

import lombok.Data;

@Data
public class StoreAndDateOrderDTO {

    private Long id;
    private Integer totalAmount;

    public StoreAndDateOrderDTO(Long id, Integer totalAmount) {
        this.id = id;
        this.totalAmount = totalAmount;
    }
}
