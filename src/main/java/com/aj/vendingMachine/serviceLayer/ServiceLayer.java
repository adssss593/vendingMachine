package com.aj.vendingMachine.serviceLayer;

import com.aj.vendingMachine.dao.VendingMachineException;
import com.aj.vendingMachine.dto.Item;

import java.math.BigDecimal;
import java.util.stream.Stream;

public interface ServiceLayer {

    void readData() throws VendingMachineException;

    Stream<Item> getCurrentItems();

    BigDecimal buyItem(Item item, BigDecimal money) throws VendingMachineException, InsufficientFundsException;
}
