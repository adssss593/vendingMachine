package com.aj.vendingmachine;

import com.aj.vendingmachine.Dao.AuditDao;
import com.aj.vendingmachine.Dao.AuditDaoImpl;
import com.aj.vendingmachine.Dao.FileDao;
import com.aj.vendingmachine.Dao.FileDaoImpl;
import com.aj.vendingmachine.ServiceLayer.ServiceLayer;
import com.aj.vendingmachine.ServiceLayer.ServiceLayerImpl;
import com.aj.vendingmachine.UI.UserConsoleImpl;
import com.aj.vendingmachine.UI.UserIO;
import com.aj.vendingmachine.UI.View;
import com.aj.vendingmachine.controller.Controller;

public class App {
    public static void main(String[] args) {
        AuditDao auditDao = new AuditDaoImpl();
        FileDao fileDao = new FileDaoImpl("items.txt");
        UserIO io = new UserConsoleImpl();
        View view = new View(io);

        ServiceLayer serviceLayer = new ServiceLayerImpl(auditDao,fileDao);
        Controller controller = new Controller(serviceLayer,view);

        controller.run();
    }




}
