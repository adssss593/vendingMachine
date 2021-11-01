package com.aj.vendingMachine.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;

@Component
public class AuditDaoImpl implements AuditDao{

    private String AUDIT_FILE = "auditFile.txt";

    public AuditDaoImpl() {
    }

    public AuditDaoImpl(String AUDIT_FILE) {
        this.AUDIT_FILE = AUDIT_FILE;
    }

    @Override
    public void writeAudit(String itemName) throws VendingMachineException{
        PrintWriter myWriter;
        try {
            myWriter = new PrintWriter(new FileWriter(AUDIT_FILE,true));
        } catch (IOException e) {
            throw new VendingMachineException("Error!!");
        }
        LocalDateTime stamp = LocalDateTime.now();
        myWriter.println(itemName + " bought at: " + stamp.format(DateTimeFormatter.ofLocalizedDateTime(FormatStyle.MEDIUM)));
        myWriter.flush();
        myWriter.close();
    }
}
