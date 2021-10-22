package com.aj.vendingmachine.Dao;

public interface AuditDao {

    public void writeAudit(String itemName) throws VendingMachineException;

}
