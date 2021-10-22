package com.aj.vendingmachine.ServiceLayer;

import com.aj.vendingmachine.Dao.AuditDao;
import com.aj.vendingmachine.Dao.FileDao;
import com.aj.vendingmachine.Dao.FileDaoImplStub;
import com.aj.vendingmachine.Dao.auditDaoImplStub;
import com.aj.vendingmachine.dto.Item;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class ServiceLayerImplTest {

    AuditDao stubAuditDao = new auditDaoImplStub();
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
            fail();

        } catch (InsufficientFundsException e) {
        }
        try {
            testServiceLayer.buyItem(testItem,new BigDecimal("1000"));
        } catch (InsufficientFundsException e) {
            fail();
        }
        try {
            BigDecimal result = testServiceLayer.buyItem(testItem,new BigDecimal("1001"));
            assertEquals(result,new BigDecimal("1"));
        } catch (InsufficientFundsException e) {
            fail();
        }
    }
}