package com.aj.vendingMachine.serviceLayer;

public class NoItemInventoryException extends Exception{

    public NoItemInventoryException(String message) {
        super(message);
    }
}
