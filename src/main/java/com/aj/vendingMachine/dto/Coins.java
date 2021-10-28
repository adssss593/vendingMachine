package com.aj.vendingMachine.dto;

import java.math.BigDecimal;

public enum Coins {
    TWO_POUNDS(new BigDecimal("2.00")),
    ONE_POUND(new BigDecimal("1.00")),
    FIFTY_PENCE(new BigDecimal("0.50")),
    TWENTY_PENCE(new BigDecimal("0.20")),
    TEN_PENCE(new BigDecimal("0.10")),
    FIVE_PENCE(new BigDecimal("0.05")),
    TWO_PENCE(new BigDecimal("0.02")),
    ONE_PENCE(new BigDecimal("0.01"));

    public BigDecimal value;

    Coins(BigDecimal value) {
        this.value = value;
    }
}
