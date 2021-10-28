package com.aj.vendingMachine.dao;

import com.aj.vendingMachine.dto.Item;
import org.springframework.stereotype.Component;

import java.io.*;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.stream.Stream;

import static java.lang.String.valueOf;

@Component
public class FileDaoImpl implements FileDao{

    private Map<String,Item> items = new HashMap<>();
    private String DELIMITER = "::";
    private String FILE = "items.txt";

    public FileDaoImpl() {
    }

    public FileDaoImpl(String FILE) {
        this.FILE = FILE;
    }

    @Override
    public void buyItem(Item item){
        int stock = item.getStock();
        int newStock = stock - 1 ;
        item.setStock(newStock);
        items.put(item.getName(),item);
        try{
            writeData();
        } catch (VendingMachineException e) {
            e.getMessage();
        }
    }

    @Override
    public Stream<Item> getCurrentItems() {
        return items.values().stream().filter((i) -> i.getStock() > 0);
    }

    private Item unmarshall(String line) {
        String[] elements = line.split(DELIMITER,-2);
        Item item = new Item(elements[0],new BigDecimal(elements[1]),Integer.parseInt(elements[2]));
        return item;
    }

    private String marshall(Item item) {
        return item.getName() + DELIMITER + item.getPrice().toString() + DELIMITER + valueOf(item.getStock());
    }

    @Override
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
                .forEach(i -> {
            myWriter.println(marshall(i));
        });
        myWriter.flush();
    }

    public Map<String, Item> getItems() {
        return items;
    }
}

