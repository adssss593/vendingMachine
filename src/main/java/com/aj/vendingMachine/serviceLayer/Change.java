package com.aj.vendingMachine.serviceLayer;

import com.aj.vendingMachine.dto.Coins;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.sql.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Change {

    public static Map<Coins,Integer> calculateChange(BigDecimal amountDue) {
        Map<Coins, Integer> coinsDue = new HashMap<>();
        for (Coins coin : Coins.values()) {
            int i = amountDue.divide(coin.value, RoundingMode.DOWN).intValue();
                if (i > 0) {
                    coinsDue.put(coin, i);
                    amountDue = new BigDecimal(String.valueOf(amountDue.subtract(coin.value.multiply(new BigDecimal(Integer.toString(i))))));
                }
        }
        return coinsDue;
    }
}

