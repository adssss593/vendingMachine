package com.aj.vendingMachine.dao;

public interface AuditDao {

    void writeAudit(String itemName) throws VendingMachineException;

}
