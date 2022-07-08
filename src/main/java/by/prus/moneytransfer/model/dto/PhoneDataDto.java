package by.prus.moneytransfer.model.dto;

import by.prus.moneytransfer.model.entity.User;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;

public class PhoneDataDto {

    private Long id;
    private Long phone;
    private User user;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getPhone() {
        return phone;
    }

    public void setPhone(Long phone) {
        this.phone = phone;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
