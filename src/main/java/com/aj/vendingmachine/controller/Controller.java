package com.aj.vendingmachine.controller;

import com.aj.vendingmachine.Dao.VendingMachineException;
import com.aj.vendingmachine.ServiceLayer.InsufficientFundsException;
import com.aj.vendingmachine.ServiceLayer.NoItemInventoryException;
import com.aj.vendingmachine.ServiceLayer.ServiceLayer;
import com.aj.vendingmachine.UI.View;
import com.aj.vendingmachine.dto.Change;
import com.aj.vendingmachine.dto.Coins;
import com.aj.vendingmachine.dto.Item;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

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


        Stream<Item> currentItems = serviceLayer.getCurrentItems();
        Stream<Item> currentItems2 = serviceLayer.getCurrentItems();
        Stream<Item> currentItems3 = serviceLayer.getCurrentItems();

        view.showItems(currentItems);

        getUserCoins();

        int userChoice = view.getUserItem(currentItems2);
        List<Item> items = currentItems3.collect(Collectors.toList());
        Item userItem = items.get(userChoice - 1);
        BigDecimal remainingAmount = new BigDecimal("2");
        try {
            remainingAmount = new BigDecimal(serviceLayer.buyItem(userItem,totalMoney).toString());
            Map<Coins, Integer> coinsDue = Change.calculateChange(remainingAmount);
            view.showChange(coinsDue);
        } catch (VendingMachineException e) {
            view.displayErrorMessage(e.getMessage());
        } catch (InsufficientFundsException e) {
            view.displayErrorMessage(e.getMessage());
            view.displayTotalMoney(totalMoney);
            view.displayErrorMessage("you don't have enough coins");
            Map<Coins, Integer> coinsDue = Change.calculateChange(remainingAmount);
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
