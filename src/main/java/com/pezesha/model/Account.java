package com.pezesha.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.pezesha.exception.InsufficientBalanceException;
import jakarta.annotation.Nonnull;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.NonNull;

import java.math.BigDecimal;

/**
 * @author : HAron Korir
 * {@code @mailto} : koryrh@gmail.com
 * {@code @created} : 4/4/23, Tuesday
 **/
@Entity
@Data
@Table(name = "accounts")
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonProperty
    private long id;

    @NotBlank(message = "Name is mandatory")
    @Column(nullable = false, unique = true)
    private String accountName;
    @Column(nullable = false, unique = true)
    private String accountNo;

    @JsonProperty
    private float balance;

    public Account() {

    }

    public Account(long id, float deposit) {
        this.id = id;
        this.balance = deposit;
    }

    public synchronized void addToBalance(float amount) {
        balance += amount;
    }

    public synchronized void subtractFromBalance(float amount) {
        if (balance < amount) {
            throw new InsufficientBalanceException("Insufficient balance: " + balance);
        }

        balance -= amount;
    }

    public synchronized boolean hasBalance() {
        return balance > 0;
    }
}
