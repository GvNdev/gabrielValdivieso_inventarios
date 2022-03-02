package com.devsu.gabrielvaldivieso_inventarios.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "orders")
public class Order extends BaseEntity {

    public Order(LocalDateTime dateTime, Client client, Store store) {
        super();
        this.dateTime = dateTime;
        this.client = client;
        this.store = store;
    }

    @Column(name = "date_time")
    private LocalDateTime dateTime;

    @Column(name = "amount")
    private Integer amount;

    @ManyToOne
    @JoinColumn(name = "client_id")
    private Client client;

    @ManyToOne
    @JoinColumn(name = "store_id")
    private Store store;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;

}
