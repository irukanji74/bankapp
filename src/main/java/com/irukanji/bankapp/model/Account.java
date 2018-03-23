package com.irukanji.bankapp.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@EqualsAndHashCode(exclude = {"client"})
@Entity
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name="client_id")
    private Client client;

    @PositiveOrZero
    @NotNull
    private BigDecimal accountBalance;

    public Account() {
    }

    public Account(Client client, BigDecimal amount) {
        this.client = client;
        this.accountBalance = amount;
    }
}
