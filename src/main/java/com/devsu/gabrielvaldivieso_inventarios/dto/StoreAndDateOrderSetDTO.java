package com.devsu.gabrielvaldivieso_inventarios.dto;

import lombok.Data;

import java.util.Set;

@Data
public class StoreAndDateOrderSetDTO {

    private Set<StoreAndDateOrderDTO> stores = null;

}
