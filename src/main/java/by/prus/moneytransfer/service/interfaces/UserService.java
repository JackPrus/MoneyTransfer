package by.prus.moneytransfer.service.interfaces;

import by.prus.moneytransfer.model.dto.UserDto;
import org.springframework.security.core.userdetails.UserDetailsService;
import by.prus.moneytransfer.model.entity.User;

import java.math.BigDecimal;
import java.util.List;
import java.util.Set;

public interface UserService extends UserDetailsService {

    List<UserDto> getAllUsers ();
    UserDto getUser(String userName);
    Set<UserDto> searchUsersByLine(String searchLine);
    User findUserById(Long userId);
    boolean moneyTransfer(Long userFrom, Long userTo, String amount);
}
