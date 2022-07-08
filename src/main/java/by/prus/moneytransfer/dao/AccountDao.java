package by.prus.moneytransfer.dao;

import by.prus.moneytransfer.model.entity.Account;
import by.prus.moneytransfer.model.entity.EmailData;
import by.prus.moneytransfer.model.entity.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountDao extends CrudRepository<Account, Long> {
    Account findByUser(User user);
}
