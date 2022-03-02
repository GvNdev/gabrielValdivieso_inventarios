package com.devsu.gabrielvaldivieso_inventarios.dto;

import lombok.Data;

import java.util.Set;

@Data
public class ClientOrderDTO {

    private Long clientId;
    private Set<StoreOrderDTO> orders;
}
