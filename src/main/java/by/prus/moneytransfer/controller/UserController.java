package by.prus.moneytransfer.controller;

import by.prus.moneytransfer.model.dto.UserDto;
import by.prus.moneytransfer.service.LinkCreator;
import by.prus.moneytransfer.service.UserServiceImpl;
import by.prus.moneytransfer.service.interfaces.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    UserService userService;
    @Autowired
    LinkCreator linkCreator;

    //http://localhost:8080/moneytransfer/users/{userId}?page=1&limit=3
    @PostAuthorize("hasAuthority('READ') or #userId==authentication.principal.userId")
    @GetMapping(
            path = "/{userId}",
            produces = {MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE}
            )
    public CollectionModel<UserDto> getAllUsers(
            @RequestParam(value = "page", defaultValue = "0") int page, //страницы начинаются с нуля
            @RequestParam(value = "limit", defaultValue = "25") int limit
    ){
        List<UserDto> userDtoList = userService.getAllUsers();
        CollectionModel<UserDto> returnValue = linkCreator.getNavigationTagLinks(userDtoList, page, limit);
        return returnValue;
    }

    //http://localhost:8080/moneytransfer/users/search/{searchLine}
    @PreAuthorize("hasAuthority('READ') or #userId==authentication.principal.userId")
    @GetMapping(
            path = "/search/{searchLIne}",
            produces = {MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE}
    )
    public CollectionModel<UserDto> searchUsers(
            @RequestParam(value = "page", defaultValue = "0") int page,
            @RequestParam(value = "limit", defaultValue = "25") int limit,
            @PathVariable String searchLIne
    ){
        Set<UserDto> userDtoSet = userService.searchUsersByLine(searchLIne);
        CollectionModel<UserDto> returnValue = linkCreator.getNavigationTagLinks(userDtoSet, page, limit);
        return returnValue;
    }

    //http://localhost:8080/moneytransfer/users/sendMoney/?userFrom=13&userTo=5/20.5
    @PreAuthorize("hasAuthority('MONEYSEND') or #userId==authentication.principal.userId")
    @PutMapping(
            path = "/sendMoney/{amount}",
            produces = {MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE}
    )
    public boolean sendMoney(
            @RequestParam(value = "userFrom") Long userFrom,
            @RequestParam(value = "userTo") Long userTo,
            @PathVariable String amount
    ){
        return userService.moneyTransfer(userFrom, userTo, amount);
    }
}
