package com.irukanji.bankapp.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;
import java.math.BigDecimal;
import java.util.Date;

@Getter
@Setter
@EqualsAndHashCode
@Entity
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @PositiveOrZero
    @NotNull
    private BigDecimal txAmount;

    @Column(name = "account_id_from")
    @NotNull
    @Digits(integer=10, fraction=2)
    private Long accountIdFrom;

    @Column(name="account_id_to")
    @NotNull
    @Digits(integer=10, fraction=2)
    private Long accountIdTo;

    @Column(name = "txTime")
    @Temporal(TemporalType.DATE)
    private Date txTime;

    public Transaction() {
    }

    public Transaction(BigDecimal txAmount, Long accountIdFrom, Long accountIdTo) {
        this.txAmount = txAmount;
        this.accountIdFrom = accountIdFrom;
        this.accountIdTo = accountIdTo;
        this.txTime = new Date();
    }
}
