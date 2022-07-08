package by.prus.moneytransfer.service;

import by.prus.moneytransfer.dao.AccountDao;
import by.prus.moneytransfer.model.entity.Account;
import by.prus.moneytransfer.model.entity.User;
import by.prus.moneytransfer.service.interfaces.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.math.BigDecimal;

@Service
@Transactional
public class AccountServiceImpl implements AccountService {

    @Autowired
    private AccountDao accountDao;

    @Override
    public Account findByUser(User user) {
        return accountDao.findByUser(user);
    }
    @Override
    public Account update(Account account) {
        return accountDao.save(account);
    }

    @Override
    public boolean transfer(Account from, Account to, BigDecimal amount) {
        String fromAccountId = from.getId().toString().intern();
        String toAccountId = to.getId().toString().intern();
        String lock1;
        String lock2;

        if (from.getUser().getId() < to.getUser().getId()) {
            lock1 = fromAccountId;
            lock2 = toAccountId;
        } else {
            lock1 = toAccountId;
            lock2 = fromAccountId;
        }

        // synchronizing from this point, since balances are checked
        synchronized (lock1) {
            synchronized (lock2) {
                BigDecimal fromValue = from.getBalance();
                if (fromValue.compareTo(amount) < 0)
                    return false;
                BigDecimal toValue = to.getBalance();
                from.setBalance(fromValue.add(amount.negate()));
                to.setBalance(toValue.add(amount));
                return true;
            }
        }
    }


}
