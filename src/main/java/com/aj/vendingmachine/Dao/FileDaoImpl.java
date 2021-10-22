package com.aj.vendingmachine.Dao;

import com.aj.vendingmachine.dto.Coins;
import com.aj.vendingmachine.dto.Item;

import java.io.*;
import java.math.BigDecimal;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.stream.Stream;

import static java.lang.String.valueOf;

public class FileDaoImpl implements FileDao{

    Map<String,Item> items = new HashMap<>();
    private String DELIMITER = "::";
    private String FILE;

    public FileDaoImpl(String FILE) {
        this.FILE = FILE;
    }

    @Override
    public void buyItem(Item item) throws VendingMachineException{
        int stock = item.getStock();
        int newStock = stock - 1 ;
        item.setStock(newStock);
        items.put(item.getName(),item);
        try{
            writeData();
        } catch (Exception e) {
            System.out.println("error");
        }
    }

    @Override
    public Stream<Item> getCurrentItems() {
        return items.values().stream().filter((i) -> i.getStock() > 0);
    }

    public Item unmarshall(String line) {
        String[] elements = line.split(DELIMITER,-2);
        Item item = new Item(elements[0],new BigDecimal(elements[1]),Integer.parseInt(elements[2]));
        return item;
    }

    public String marshall(Item item) {
        return item.getName() + DELIMITER + item.getPrice().toString() + DELIMITER + valueOf(item.getStock());
    }

    public void readData() throws VendingMachineException{
        Scanner scanner;
        try {
            scanner = new Scanner( new BufferedReader(new FileReader(FILE)));
        } catch (FileNotFoundException e) {
            throw new VendingMachineException("Internal error!");
        }

        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            Item item = unmarshall(line);
            items.put(item.getName(),item);
        }
    }

    public void writeData() throws VendingMachineException {
        PrintWriter myWriter;
        try {
            myWriter = new PrintWriter(new FileWriter(FILE));
        } catch (IOException e) {
            throw new VendingMachineException("Internal error!");
        }

        items.values().stream()
                .forEach((i) -> {
            myWriter.println(marshall(i));
            myWriter.flush();
        });
    }
}

