package com.aj.vendingmachine.Dao;

import com.aj.vendingmachine.dto.Item;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Stream;

public class FileDaoImplStub implements FileDao{

    @Override
    public void buyItem(Item item) throws VendingMachineException {
    }

    @Override
    public void readData() throws VendingMachineException {

    }

    @Override
    public Stream<Item> getCurrentItems() {
        return null;
    }
}
