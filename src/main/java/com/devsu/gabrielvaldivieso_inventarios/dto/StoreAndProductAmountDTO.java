package com.devsu.gabrielvaldivieso_inventarios.dto;

import lombok.Data;

@Data
public class StoreAndProductAmountDTO {

    private Long storeId;
    private Long productId;
    private Integer amount;

    public StoreAndProductAmountDTO(Long storeId, Long productId, Integer amount) {
        this.storeId = storeId;
        this.productId = productId;
        this.amount = amount;
    }
}
