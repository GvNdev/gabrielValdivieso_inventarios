package com.devsu.gabrielvaldivieso_inventarios.dto;

import lombok.Data;

import java.util.Set;

@Data
public class StoreAndProductAmountSetDTO {

    private Set<StoreAndProductAmountDTO> amounts = null;
}
