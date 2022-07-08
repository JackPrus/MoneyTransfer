package by.prus.moneytransfer;

import by.prus.moneytransfer.dao.AccountDao;
import by.prus.moneytransfer.dao.EmailDataDao;
import by.prus.moneytransfer.dao.PhoneDataDao;
import by.prus.moneytransfer.dao.UserDao;
import by.prus.moneytransfer.model.entity.Account;
import by.prus.moneytransfer.model.entity.EmailData;
import by.prus.moneytransfer.model.entity.PhoneData;
import by.prus.moneytransfer.model.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.security.AccessControlContext;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Component
public class InitialUserSetup {

    @Autowired
    private UserDao userDao;
    @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;
    @Autowired
    private AccountDao accountDao;
    @Autowired
    private EmailDataDao emailDataDao;
    @Autowired
    private PhoneDataDao phoneDataDao;


    @EventListener
    @Transactional
    public void onApplicationEvent(ApplicationReadyEvent event) {
        if (!areUsersCreated()) {
            User user1 = new User();
            user1.setName("Фролов Иван");
            user1.setDateOfBirth(LocalDate.of(1994, 9, 11));
            user1.setPassword(bCryptPasswordEncoder.encode("1234"));

            User user2 = new User();
            user2.setName("Петров Андрей");
            user2.setDateOfBirth(LocalDate.of(1992, 3, 11));
            user2.setPassword(bCryptPasswordEncoder.encode("1234"));

            User user3 = new User();
            user3.setName("Соколов Василий");
            user3.setDateOfBirth(LocalDate.of(1999, 3, 22));
            user3.setPassword(bCryptPasswordEncoder.encode("1234"));

            User user4 = new User();
            user4.setName("qwer");
            user4.setDateOfBirth(LocalDate.of(2002, 11, 15));
            user4.setPassword(bCryptPasswordEncoder.encode("1234"));

            Account account1 = new Account();
            account1.setBalance(new BigDecimal("15.50"));

            Account account2 = new Account();
            account2.setBalance(new BigDecimal("0.00"));

            Account account3 = new Account();
            account3.setBalance(new BigDecimal("114.20"));

            Account account4 = new Account();
            account4.setBalance(new BigDecimal("130.00"));

            EmailData emailData1 = new EmailData();
            emailData1.setEmail("frolow@mail.ru");

            EmailData emailData2 = new EmailData();
            emailData2.setEmail("petrov@mail.ru");

            EmailData emailData3 = new EmailData();
            emailData3.setEmail("sokolow@mail.ru");

            PhoneData phoneData1 = new PhoneData();
            phoneData1.setPhone(9204569855835L);

            PhoneData phoneData2 = new PhoneData();
            phoneData2.setPhone(4957707075225L);

            PhoneData phoneData3 = new PhoneData();
            phoneData3.setPhone(9201666161536L);

            user1.setAccount(account1);
            user1.setEmailDataList(new ArrayList<>(Arrays.asList(emailData1)));
            user1.setPhoneData(new ArrayList<>(Arrays.asList(phoneData1)));

            user2.setAccount(account2);
            user2.setEmailDataList(new ArrayList<>(Arrays.asList(emailData2)));
            user2.setPhoneData(new ArrayList<>(Arrays.asList(phoneData2)));

            user3.setAccount(account3);
            user3.setEmailDataList(new ArrayList<>(Arrays.asList(emailData3)));
            user3.setPhoneData(new ArrayList<>(Arrays.asList(phoneData3)));

            user4.setAccount(account4);
            user4.setEmailDataList(new ArrayList<>(Arrays.asList(emailData3)));
            user4.setPhoneData(new ArrayList<>(Arrays.asList(phoneData3)));

//            PhoneData phone1 = phoneDataDao.save(phoneData1);
//            PhoneData phone2 = phoneDataDao.save(phoneData2);
//            PhoneData phone3 = phoneDataDao.save(phoneData3);
//
//            EmailData email1 = emailDataDao.save(emailData1);
//            EmailData email2 = emailDataDao.save(emailData2);
//            EmailData email3 = emailDataDao.save(emailData3);
//
//            Account acc1 = accountDao.save(account1);
//            Account acc2 = accountDao.save(account2);
//            Account acc3 = accountDao.save(account3);
//
//            phoneDataDao.save(phone1);
//            phoneDataDao.save(phone2);
//            phoneDataDao.save(phone3);
//            emailDataDao.save(email1);
//            emailDataDao.save(email2);
//            emailDataDao.save(email3);
//            accountDao.save(acc1);
//            accountDao.save(acc2);
//            accountDao.save(acc3);

//            user1.setAccount(acc1);
//            user1.setEmailDataList(new ArrayList<EmailData>(Arrays.asList(email1)));
//            user1.setPhoneData(new ArrayList<>(Arrays.asList(phone1)));
//
//            user2.setAccount(acc2);
//            user2.setEmailDataList(new ArrayList<EmailData>(Arrays.asList(email2)));
//            user2.setPhoneData(new ArrayList<>(Arrays.asList(phone2)));
//
//            user3.setAccount(acc3);
//            user3.setEmailDataList(new ArrayList<EmailData>(Arrays.asList(email3)));
//            user3.setPhoneData(new ArrayList<>(Arrays.asList(phone3)));

            phoneData1.setUser(user1);
            phoneData2.setUser(user2);
            phoneData3.setUser(user3);
            emailData1.setUser(user1);
            emailData2.setUser(user2);
            emailData3.setUser(user3);
            account1.setUser(user1);
            account2.setUser(user2);
            account3.setUser(user3);
            account4.setUser(user4);

            userDao.save(user1);
            userDao.save(user2);
            userDao.save(user3);
            userDao.save(user4);
        }

    }

    private boolean areUsersCreated() {
        Pageable pageableRequest = PageRequest.of(0, 5);
        Page<User> userPage = userDao.findAll(pageableRequest);
        List<User> users = userPage.getContent();
        return users.size() >= 4;
    }
}
