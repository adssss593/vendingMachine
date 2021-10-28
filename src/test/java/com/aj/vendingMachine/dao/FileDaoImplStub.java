package com.aj.vendingMachine.dao;

import com.aj.vendingMachine.dto.Item;

import java.util.stream.Stream;

public class FileDaoImplStub implements FileDao{

    @Override
    public void buyItem(Item item){
    }

    @Override
    public void readData() throws VendingMachineException {
    }

    @Override
    public Stream<Item> getCurrentItems() {
        return null;
    }
}
