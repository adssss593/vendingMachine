package com.aj.vendingMachine;

import com.aj.vendingMachine.controller.Controller;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class App {
    public static void main(String[] args) {

        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
        applicationContext.scan("com.aj.vendingmachine");
        applicationContext.refresh();

        Controller controller = applicationContext.getBean("controller", Controller.class);
        controller.run();
    }




}
