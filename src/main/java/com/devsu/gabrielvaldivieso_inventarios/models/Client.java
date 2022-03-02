package com.devsu.gabrielvaldivieso_inventarios.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "clients")
public class Client extends BaseEntity {

    public Client(Long id, String name, String identification) {
        super(id);
        this.name = name;
        this.identification = identification;
    }

    @Column(name = "name")
    private String name;
    @Column(name = "identification")
    private String identification;
    @Lob
    private Byte[] photo;

}
