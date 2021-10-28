package com.aj.vendingMachine.dao;

import com.aj.vendingMachine.dto.Item;

import java.util.stream.Stream;

public interface FileDao {

    void buyItem(Item item);
    void readData()throws VendingMachineException;
    Stream<Item> getCurrentItems();

}
