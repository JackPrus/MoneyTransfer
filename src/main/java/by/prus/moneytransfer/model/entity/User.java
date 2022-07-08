package by.prus.moneytransfer.model.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "users", schema = "public")
public class User {

    @Id
    @GeneratedValue
    private Long id;
    @Column(length = 500)
    private String name;
    @DateTimeFormat(pattern = "dd.MM.yyyy")
    @Column(name = "date_of_birth", length = 500)
    private LocalDate dateOfBirth;
    @Column(nullable = false, length = 500)
    private String password;

    @JsonBackReference
    @OneToOne(mappedBy ="user", cascade = {CascadeType.ALL}, fetch = FetchType.EAGER)
    private Account account;
    @JsonBackReference
    @OneToMany(mappedBy = "user", cascade = {CascadeType.ALL}, fetch = FetchType.LAZY)
    private List<EmailData> emailDataList;
    @JsonBackReference
    @OneToMany(mappedBy = "user", cascade = {CascadeType.ALL}, fetch = FetchType.LAZY)
    private List<PhoneData> phoneData;

    public User() {
    }

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
}
