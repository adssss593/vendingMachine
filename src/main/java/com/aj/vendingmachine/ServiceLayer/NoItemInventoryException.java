package com.aj.vendingmachine.ServiceLayer;

public class NoItemInventoryException extends Exception{

    public NoItemInventoryException(String message) {
        super(message);
    }
}
