package com.aj.vendingmachine.Dao;

import com.aj.vendingmachine.dto.Item;

import java.util.stream.Stream;

public interface FileDao {

    public void buyItem(Item item) throws VendingMachineException;
    public void readData()throws VendingMachineException;
    public Stream<Item> getCurrentItems();

}
