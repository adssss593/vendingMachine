package com.aj.vendingMachine.dao;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AuditDaoImplTest {

    private static final String testFile = "auditDaoTest.txt";
    AuditDao testAuditDao = new AuditDaoImpl(testFile);

    @Test
    void writeAudit() {
        try {
            testAuditDao.writeAudit("Quavers::1.40::12");
        } catch (VendingMachineException e) {
            fail();
        }
    }
}