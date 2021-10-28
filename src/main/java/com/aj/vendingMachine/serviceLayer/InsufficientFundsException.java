package com.aj.vendingMachine.serviceLayer;

public class InsufficientFundsException extends Exception{

    public InsufficientFundsException (String message) {
        super(message);
    }

}
