package by.prus.moneytransfer.service;

import by.prus.moneytransfer.controller.UserController;
import by.prus.moneytransfer.model.dto.UserDto;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.stereotype.Component;

import java.util.Collection;

@Component
public class LinkCreator {

    public CollectionModel<UserDto> getNavigationTagLinks(Collection<UserDto> workValue, int page, int limit){

        CollectionModel<UserDto> returnValue = CollectionModel.of(workValue);

        if (workValue.size()>=limit){
            Link nextPage = WebMvcLinkBuilder.linkTo(
                    WebMvcLinkBuilder.methodOn(UserController.class)
                            .getAllUsers(page+1, limit))
                    .withRel("nextPageRel");
            returnValue.add(nextPage);
        }
        if (page>1){
            Link previousPage = WebMvcLinkBuilder.linkTo(
                    WebMvcLinkBuilder.methodOn(UserController.class)
                            .getAllUsers(page-1, limit))
                    .withRel("previousPageRel");
            returnValue.add(previousPage);
        }
        return returnValue;
    }
}
