package by.prus.moneytransfer.service.interfaces;

import by.prus.moneytransfer.model.entity.Account;
import by.prus.moneytransfer.model.entity.User;

import java.math.BigDecimal;

public interface AccountService {
    Account findByUser(User user);
    boolean transfer(Account from, Account to, BigDecimal amount);
    Account update(Account account);
}
