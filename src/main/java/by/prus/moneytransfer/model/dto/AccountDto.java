package by.prus.moneytransfer.model.dto;

import by.prus.moneytransfer.model.entity.User;

import javax.persistence.*;
import javax.validation.constraints.Digits;
import java.math.BigDecimal;

public class AccountDto {

    private Long id;
    private User user;
    private BigDecimal balance;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }
}
