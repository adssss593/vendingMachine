package com.aj.vendingmachine.dto;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class Change {
    private BigDecimal amountDue;

    public Change(BigDecimal amountDue) {
        this.amountDue = amountDue;
    }

    public static Map<Coins, Integer> calculateChange(BigDecimal amountDue) {
        Map<Coins, Integer> coinsDue = new HashMap<>();
        if (amountDue.compareTo(amountDue) != -1) {
            amountDue = new BigDecimal(String.valueOf(calculateCoins(coinsDue, Coins.TWO_POUNDS, amountDue, "2.00")));
        }
        if (amountDue.compareTo(amountDue) != -1) {
            amountDue = new BigDecimal(String.valueOf(calculateCoins(coinsDue, Coins.ONE_POUND, amountDue, "1.00")));
        }
        if (amountDue.compareTo(amountDue) != -1) {
            amountDue = new BigDecimal(String.valueOf(calculateCoins(coinsDue, Coins.FIFTY_PENCE, amountDue, "0.50")));
        }
        if (amountDue.compareTo(amountDue) != -1) {
            amountDue = new BigDecimal(String.valueOf(calculateCoins(coinsDue, Coins.TWENTY_PENCE, amountDue, "0.20")));
        }
        if (amountDue.compareTo(amountDue) != -1) {
            amountDue = new BigDecimal(String.valueOf(calculateCoins(coinsDue, Coins.TEN_PENCE, amountDue, "0.10")));
        }
        if (amountDue.compareTo(amountDue) != -1) {
            amountDue = new BigDecimal(String.valueOf(calculateCoins(coinsDue, Coins.FIVE_PENCE, amountDue, "0.05")));
        }
        if (amountDue.compareTo(amountDue) != -1) {
            amountDue = new BigDecimal(String.valueOf(calculateCoins(coinsDue, Coins.TWO_PENCE, amountDue, "0.02")));
        }
        if (amountDue.compareTo(amountDue) != -1) {
            new BigDecimal(String.valueOf(calculateCoins(coinsDue, Coins.ONE_PENCE, amountDue, "0.01")));
        }
        return coinsDue;


    }
    public static BigDecimal calculateCoins(Map<Coins, Integer> coinsDue, Coins coin, BigDecimal amountDue,String value) {
        BigDecimal currentCoin = new BigDecimal(value);
        while (amountDue.compareTo(new BigDecimal(value)) != -1) {
            if (coinsDue.containsKey(coin)) {
                Integer increment = new Integer(coinsDue.get(coin) + 1);
                coinsDue.put(coin,increment);
            } else {
                coinsDue.put(coin,new Integer(1));
            }
            amountDue = new BigDecimal(String.valueOf(amountDue.subtract(currentCoin)));
        }
        return amountDue;
    }
}
