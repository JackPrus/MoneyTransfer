package by.prus.moneytransfer.service;

import by.prus.moneytransfer.dao.UserDao;
import by.prus.moneytransfer.model.dto.UserDto;
import by.prus.moneytransfer.model.entity.Account;
import by.prus.moneytransfer.model.entity.PhoneData;
import by.prus.moneytransfer.model.entity.User;
import by.prus.moneytransfer.security.UserPrincipal;
import by.prus.moneytransfer.service.interfaces.AccountService;
import by.prus.moneytransfer.service.interfaces.UserService;
import org.hibernate.service.spi.ServiceException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserDao userDao;
    @Autowired
    ModelMapper modelMapper;
    @Autowired
    AccountService accountService;

    @Override
    public List<UserDto> getAllUsers() {
        List<User> users = (List<User>) userDao.findAll();
        List<UserDto> dtoUsers = users.stream().map(user -> modelMapper.map(user, UserDto.class)).collect(Collectors.toList());
        return dtoUsers;
    }

    @Override
    public UserDto getUser(String userName) {
        User user = userDao.findByName(userName);
        if (user != null) {
            UserDto dto = new UserDto();
            dto.setId(user.getId());
            dto.setAccount(user.getAccount());
            dto.setDateOfBirth(dto.getDateOfBirth());
            dto.setName(user.getName());
            dto.setPassword(user.getPassword());
            dto.setPhoneData(user.getPhoneData());
            dto.setEmailDataList(user.getEmailDataList());
            return dto;
        } else {
            throw new ServiceException("User not found");
        }
    }

    @Override
    public Set<UserDto> searchUsersByLine(String searchLine) {
        List<User> userList = new ArrayList<>();
        if (Utils.isEmail(searchLine)) {
            userList.addAll(userDao.searchByEmail(searchLine));
        }
        if (Utils.isPhone(searchLine)) {
            userList.addAll(userDao.searchByPhone(Long.parseLong(searchLine)));
        }
        if (Utils.isDate(searchLine)) {
            userList.addAll(userDao.findAllByDateOfBirthAfter(Utils.getLocalDate(searchLine)));
        }
        userList.addAll(userDao.searchByNamePart(searchLine));
        Set<UserDto> resultSet = userList.stream().map(user -> modelMapper.map(user, UserDto.class)).collect(Collectors.toSet());
        return resultSet;
    }

    @Override
    public User findUserById(Long userId) {
        return userDao.findById(userId).orElse(null);
    }

    @Transactional
    @Override
    public boolean moneyTransfer(Long userFrom, Long userTo, String amount) {
        BigDecimal decimalAmount = new BigDecimal(amount);
        User uFrom = findUserById(userFrom);
        User uTo = findUserById(userTo);
        Account accfrom = accountService.findByUser(uFrom);
        Account accTo = accountService.findByUser(uTo);
        if (accountService.transfer(accfrom, accTo, decimalAmount)){
            accountService.update(accfrom);
            accountService.update(accTo);
            return true;
        }
        return false;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User userEntity = userDao.findByName(username);
        if (userEntity == null) {
            throw new UsernameNotFoundException(username);
        }
        return new UserPrincipal(userEntity);
    }


}
