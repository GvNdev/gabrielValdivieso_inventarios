package com.devsu.gabrielvaldivieso_inventarios.dto;

import lombok.Data;

import java.util.Set;

@Data
public class StoreOrderDTO {

    private Long storeId;
    private Set<ProductOrderDTO> products;

}
