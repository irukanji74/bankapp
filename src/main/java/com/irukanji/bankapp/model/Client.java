package com.irukanji.bankapp.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@EqualsAndHashCode(exclude = {"accounts"})
@Entity
public class Client {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "client_id", updatable = false)
    private Long id;

    @NotBlank
    private String name;

    /*@Embedded
    @AttributeOverrides(value = {
            @AttributeOverride(name = "addressLine1", column = @Column(name = "house_number")),
            @AttributeOverride(name = "addressLine2", column = @Column(name = "street"))
    })
    private Address address;*/

    @NotBlank
    private String street;

    @NotBlank
    private String houseNumber;

    @PositiveOrZero
    @NotNull
    private Integer age;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "client")
    private List<Account> accounts  = new ArrayList<>();

    public Client() {
    }

    public Client(String name, String street, String houseNumber, Integer age) {
        this.name = name;
        this.street = street;
        this.houseNumber = houseNumber;
        this.age = age;
    }
}
