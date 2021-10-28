package com.aj.vendingMachine.dao;

import com.aj.vendingMachine.dto.Item;
import org.junit.jupiter.api.BeforeEach;

import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class FileDaoImplTest {

    FileDaoImpl testDao;

    @BeforeEach
    void setUp() throws Exception{
        String testFile = "testFile.txt";
        testDao = new FileDaoImpl(testFile);
        testDao.readData();
        Item testItem = testDao.getItems().get("Lucozade");
        testItem.setStock(2);
        testDao.getItems().put("Lucozade",testItem);
    }

    @Test
    void buyItem() throws Exception{
        Item testItem = testDao.getItems().get("Lucozade");
        testDao.buyItem(testItem);
        int newQuantity = testDao.getItems().get("Lucozade").getStock();
        assertTrue( newQuantity == 1);
        assertNull(testDao.getItems().get("This item doesn't exist"));
    }

    @Test
    void getCurrentItems() {
        List<Item> inStock = testDao.getCurrentItems().collect(Collectors.toList());
        assertEquals(inStock.size(),1);
        assertEquals(testDao.getItems().get("Lucozade").toString(), inStock.get(0).toString());
    }
}