package com.aj.vendingMachine.serviceLayer;

import com.aj.vendingMachine.dao.AuditDao;
import com.aj.vendingMachine.dao.FileDao;
import com.aj.vendingMachine.dao.VendingMachineException;
import com.aj.vendingMachine.dto.Coins;
import com.aj.vendingMachine.dto.Item;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Stream;

@Component
public class ServiceLayerImpl implements ServiceLayer{

    private AuditDao auditDao;
    private FileDao fileDao;

    @Autowired
    public ServiceLayerImpl(AuditDao auditDao, FileDao fileDao) {
        this.auditDao = auditDao;
        this.fileDao = fileDao;
    }

    @Override
    public void readData() throws VendingMachineException {
        fileDao.readData();
    }

    @Override
    public BigDecimal buyItem(Item item, BigDecimal money) throws VendingMachineException, InsufficientFundsException {
        fundsCheck(item,money);
        fileDao.buyItem(item);
        BigDecimal remainingMoney = money.subtract(item.getPrice());
        auditDao.writeAudit(item.getName());
        return remainingMoney;
    }

    @Override
    public Stream<Item> getCurrentItems() {
        return fileDao.getCurrentItems();
    }

    @Override
    public Map<Coins,Integer> calculateChange(BigDecimal amountDue) {
        Map<Coins, Integer> coinsDue = new HashMap<>();
        for (Coins coin : Coins.values()) {
            int i = amountDue.divide(coin.value, RoundingMode.DOWN).intValue();
            if (i > 0) {
                coinsDue.put(coin, i);
                amountDue = new BigDecimal(String.valueOf(amountDue.subtract(coin.value.multiply(new BigDecimal(Integer.toString(i))))));
            }
        }
        return coinsDue;
    }

    public void fundsCheck(Item item, BigDecimal money) throws InsufficientFundsException {
        if (money.compareTo(item.getPrice()) == -1) {
            throw new InsufficientFundsException("You do not have the funds for this");
        }
    }
}
