package com.aj.vendingMachine.controller;

import com.aj.vendingMachine.dao.VendingMachineException;
import com.aj.vendingMachine.serviceLayer.InsufficientFundsException;
import com.aj.vendingMachine.serviceLayer.ServiceLayer;
import com.aj.vendingMachine.UI.View;
import com.aj.vendingMachine.dto.Coins;
import com.aj.vendingMachine.dto.Item;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Component
public class Controller {

    private ServiceLayer serviceLayer;
    private View view;
    private  BigDecimal totalMoney = new BigDecimal("0.00");

    @Autowired
    public Controller(ServiceLayer serviceLayer, View view) {
        this.serviceLayer = serviceLayer;
        this.view = view;
    }

    public void run() {

        try {
            serviceLayer.readData();
        } catch (VendingMachineException e) {
            view.displayErrorMessage(e.getMessage());
            System.exit(0);
        }

        view.showItems(serviceLayer.getCurrentItems());

        getUserCoins();

        int userChoice = view.getUserItem(serviceLayer.getCurrentItems());
        List<Item> items = serviceLayer.getCurrentItems().collect(Collectors.toList());
        Item userItem = items.get(userChoice - 1);
        BigDecimal remainingAmount = new BigDecimal("2");
        try {
            remainingAmount = new BigDecimal(serviceLayer.buyItem(userItem,totalMoney).toString());
            Map<Coins, Integer> coinsDue = serviceLayer.calculateChange(remainingAmount);
            view.showChange(coinsDue);
        } catch (VendingMachineException e) {
            view.displayErrorMessage(e.getMessage());
        } catch (InsufficientFundsException e) {
            view.displayErrorMessage(e.getMessage());
            view.displayTotalMoney(totalMoney);
            view.displayErrorMessage("you don't have enough coins");
            Map<Coins, Integer> coinsDue = serviceLayer.calculateChange(remainingAmount);
            view.showChange(coinsDue);
        }
    }

    public void getUserCoins() {
        boolean addingMoney = true;
        do {
            view.displayTotalMoney(totalMoney);
            int userCoin = view.getUserCoins();
            switch (userCoin) {
                case 1:
                    totalMoney = totalMoney.add(new BigDecimal("2.00"));
                    break;
                case 2:
                    totalMoney = totalMoney.add(new BigDecimal("1.00"));
                    break;
                case 3:
                    totalMoney = totalMoney.add(new BigDecimal("0.50"));
                    break;
                case 4:
                    totalMoney = totalMoney.add(new BigDecimal("0.20"));
                    break;
                case 5:
                    totalMoney =totalMoney.add(new BigDecimal("0.10"));
                    break;
                case 6:
                    totalMoney = totalMoney.add(new BigDecimal("0.05"));
                    break;
                case 7:
                    totalMoney = totalMoney.add(new BigDecimal("0.02"));
                    break;
                case 8:
                    totalMoney = totalMoney.add(new BigDecimal("0.01"));
                    break;
                case 9:
                    addingMoney = false;
                    break;
            }
        } while (addingMoney);
    }
}
