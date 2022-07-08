package by.prus.moneytransfer.model.dto;

import by.prus.moneytransfer.model.entity.Account;
import by.prus.moneytransfer.model.entity.EmailData;
import by.prus.moneytransfer.model.entity.PhoneData;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

public class UserDto {

    private Long id;
    private String name;
    private LocalDate dateOfBirth;
    private String password;
    private Account account;
    private List<EmailData> emailDataList;
    private List<PhoneData> phoneData;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public List<EmailData> getEmailDataList() {
        return emailDataList;
    }

    public void setEmailDataList(List<EmailData> emailDataList) {
        this.emailDataList = emailDataList;
    }

    public List<PhoneData> getPhoneData() {
        return phoneData;
    }

    public void setPhoneData(List<PhoneData> phoneData) {
        this.phoneData = phoneData;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserDto userDto = (UserDto) o;
        return Objects.equals(id, userDto.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, dateOfBirth, password, account, emailDataList, phoneData);
    }
}
