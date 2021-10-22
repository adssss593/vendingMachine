package com.aj.vendingmachine.Dao;

import com.aj.vendingmachine.dto.Item;
import org.junit.jupiter.api.BeforeEach;

import java.io.FileWriter;
import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;
import com.aj.vendingmachine.Dao.FileDaoImpl;
import org.junit.jupiter.api.Test;

class FileDaoImplTest {

    FileDaoImpl testDao;

    @BeforeEach
    void setUp() throws Exception{
        String testFile = "testFile.txt";
        testDao = new FileDaoImpl(testFile);
        testDao.readData();
        Item testItem = testDao.items.get("Lucozade");
        testItem.setStock(2);
        testDao.items.put("Lucozade",testItem);
    }

    @Test
    void buyItem() throws Exception{
        Item testItem = testDao.items.get("Lucozade");
        testDao.buyItem(testItem);
        int newQuantity = testDao.items.get("Lucozade").getStock();
        assertTrue( newQuantity == 1);
        assertNull(testDao.items.get("This item doesn't exist"));
    }

    @Test
    void getCurrentItems() {
        List<Item> inStock = testDao.getCurrentItems().collect(Collectors.toList());
        assertEquals(inStock.size(),1);
        assertEquals(testDao.items.get("Lucozade").toString(), inStock.get(0).toString());
    }
}