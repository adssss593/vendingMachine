package com.aj.vendingMachine.serviceLayer;

import com.aj.vendingMachine.dao.AuditDao;
import com.aj.vendingMachine.dao.FileDao;
import com.aj.vendingMachine.dao.FileDaoImplStub;
import com.aj.vendingMachine.dao.AuditDaoImplStub;
import com.aj.vendingMachine.dto.Coins;
import com.aj.vendingMachine.dto.Item;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class ServiceLayerImplTest {

    AuditDao stubAuditDao = new AuditDaoImplStub();
    FileDao stubFileDao = new FileDaoImplStub();

    ServiceLayer testServiceLayer = new ServiceLayerImpl(stubAuditDao,stubFileDao);
    Map<String,Item> items = new HashMap<>();
    Item testItem = new Item("Time out", new BigDecimal("1000"),14);

    @BeforeEach
    void setUp () {
        items.put(testItem.getName(),testItem);
    }

    @Test
    void buyItem() throws Exception {
        try {
            testServiceLayer.buyItem(testItem,new BigDecimal("999"));
            fail("not enough funds to buy item");

        } catch (InsufficientFundsException ignore) {
        }
        try {
            testServiceLayer.buyItem(testItem,new BigDecimal("1000"));
        } catch (InsufficientFundsException e) {
            fail("enough funds");
        }
        try {
            BigDecimal result = testServiceLayer.buyItem(testItem,new BigDecimal("1001"));
            assertEquals(result,new BigDecimal("1"));
        } catch (InsufficientFundsException e) {
            fail("enough funds");
        }
    }

    @Test
    void calculateChange() {
        Map<Coins, Integer> coins = testServiceLayer.calculateChange(new BigDecimal("3.88"));

        Map<Coins, Integer> correctCoins = new HashMap<>();
        for (Coins coin : Coins.values()) {
            correctCoins.put(coin,1);
        }
        assertEquals(coins,correctCoins,"test case should contain one of each coin");

    }
}