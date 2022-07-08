package by.prus.moneytransfer.model.dto;

import by.prus.moneytransfer.model.entity.User;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;

public class EmailDataDto {

    private Long id;
    private User user;
    private String email;

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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
