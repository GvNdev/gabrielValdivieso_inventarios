package com.devsu.gabrielvaldivieso_inventarios.dto;

import lombok.Data;

@Data
public class StockDTO {

    private String code;
    private String name;
    private Integer stock;

    public StockDTO() {
        super();
    }
}
