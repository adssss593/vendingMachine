package com.aj.vendingmachine.UI;

import com.aj.vendingmachine.dto.Coins;
import com.aj.vendingmachine.dto.Item;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Stream;

public class View {

    private UserIO io;

    public View(UserIO io) {
        this.io = io;
    }

    public void showItems(Stream<Item> items) {
        io.print("Here are our items");
        items.forEach((i) -> {
            io.print(i.getName() + ": £" + i.getPrice());
        });
        io.getUserString("press enter to continue");
    }

    public int getUserCoins(){
        return  io.getUserInt(
                "\n\nPut in your coins:" +
                "\n1. £2" +
                "\n2. £1" +
                "\n3. £0.50" +
                "\n4. £0.20" +
                "\n5. £0.10" +
                "\n6. £0.05" +
                "\n7. £0.02" +
                "\n8. £0.01" +
                "\n9. I've entered all my coins!",1,9);
        }

    public int getUserItem(Stream<Item> items) {

        Collection<Integer> results = new ArrayList<>();

        AtomicInteger count = new AtomicInteger(1);
        items.forEach((i) -> {
            io.print(count +". " + i.getName() + ": £" + i.getPrice());
            count.getAndIncrement();
        });
        int userChoice = io.getUserInt("which would you like?",1,count.get());
        return userChoice;
    }

    public void displayErrorMessage (String message) {
        io.print(message);
    }

    public void showChange(Map<Coins,Integer> change) {
        if (change.keySet().size() == 0) {
            io.print("No change needed!");
        } else {
            io.print("Your change:");
            for (Coins c: change.keySet()) {
                io.print(c + " : " + change.get(c));
            }
        }
    }

    public void displayTotalMoney(BigDecimal money) {
        io.print("Total: £" + money.toString());
    }


}
