package com.devsu.gabrielvaldivieso_inventarios.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "stores")
public class Store extends BaseEntity {

    public Store(Long id, String cod, String name) {
        super(id);
        this.cod = cod;
        this.name = name;
    }

    @Column(name = "cod")
    private String cod;

    @Column(name = "name")
    private String name;

}
