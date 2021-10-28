package com.aj.vendingmachine.UI;

import org.springframework.stereotype.Component;

import java.util.Scanner;

@Component
public class UserConsoleImpl implements UserIO{

    Scanner scanner = new Scanner(System.in);

    @Override
    public void print(String statement) {
        System.out.println(statement);
    }

    @Override
    public String getUserString(String prompt) {
        System.out.println(prompt);
        String input = scanner.nextLine();
        return input;
    }

    @Override
    public int getUserInt(String prompt, int min, int max) {
        boolean validInput = false;
        int input = 1;
        do {
            System.out.println(prompt);
            input = scanner.nextInt();
            if (input > max || input < min) {
                System.out.println(". Number must be between " + min + " and " + max);
            } else {
                validInput = true;
            }
        } while (!validInput);
        return input;
    }
}
