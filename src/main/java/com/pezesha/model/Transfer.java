package com.pezesha.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;

/**
 * @author : HAron Korir
 * {@code @mailto} : koryrh@gmail.com
 * {@code @created} : 4/4/23, Tuesday
 **/
@Data
public class Transfer {
    @JsonProperty("from")
    private String fromAccountNo;

    @JsonProperty("to")
    private String toAccountNo;

    @JsonProperty
    private float amount;

    @JsonProperty
    private String message;

    @JsonProperty("from-account-info")
    private Account fromAccountInfo;

    @JsonProperty("to-account-info")
    private Account toAccountInfo;

    public Transfer() {

    }

    public Transfer(String fromAccountNo, String toAccountNo, float amount, String transferComplete, Account fromAcctInfo, Account toAcctInfo) {
        this.fromAccountNo = fromAccountNo;
        this.toAccountNo = toAccountNo;
        this.amount = amount;
        this.message = transferComplete;
        this.fromAccountInfo = fromAcctInfo;
        this.toAccountInfo = toAcctInfo;
    }
}
