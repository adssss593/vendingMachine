package com.aj.vendingmachine.ServiceLayer;

import com.aj.vendingmachine.Dao.VendingMachineException;
import com.aj.vendingmachine.dto.Item;

import java.math.BigDecimal;
import java.util.stream.Stream;

public interface ServiceLayer {

    public void readData() throws VendingMachineException;

    public Stream<Item> getCurrentItems();

    public BigDecimal buyItem(Item item, BigDecimal money) throws VendingMachineException, InsufficientFundsException;
}
