package com.aj.vendingmachine.UI;

public interface UserIO {

    public void print(String statement);

    public String getUserString(String prompt);

    public int getUserInt(String prompt, int min, int max);

}
