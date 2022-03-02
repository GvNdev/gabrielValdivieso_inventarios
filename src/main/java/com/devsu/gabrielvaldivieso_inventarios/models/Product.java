package com.devsu.gabrielvaldivieso_inventarios.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.math.BigDecimal;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "products")
public class Product extends BaseEntity{

    public Product(Long id, String cod, String name, BigDecimal price, Integer stock) {
        super(id);
        this.cod = cod;
        this.name = name;
        this.price = price;
        this.stock = stock;
    }

    @Column(name = "cod")
    private String cod;

    @Column(name = "name")
    private String name;

    @Column(name = "price")
    private BigDecimal price;

    @Column(name = "stock")
    private Integer stock;

}
