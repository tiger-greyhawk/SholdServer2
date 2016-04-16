package ru.rsoft.configurator.backend.handlers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestBody;
import ru.rsoft.configurator.core.dto.UserCreateDto;
import ru.rsoft.configurator.core.dto.UserDto;
import ru.rsoft.configurator.core.entity.User;
//import ru.rsoft.configurator.core.repository.DesignRepository;

import ru.rsoft.configurator.core.repository.UserRepository;


import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * Обработчик ресурса чертежей.
 * <p/>
 * Тип возвращаемого значения указывается в хедере "Accept" запроса.
 * Варианты: "application/json" и "application/xml"
 * Другие можно добавить, подключив дополнительные библиотеки.
 * <p/>
 * Тип передаваемого значения указывается в хедере "Content-Type" запроса.
 * Варианты: "application/json" и "application/xml"
 * Другие можно добавить, подключив дополнительные библиотеки.
 */
@Path("/user")
@Component
public class UserHandler {

    private final UserRepository userRepository;

    @Autowired
    public UserHandler(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Path("/")
    @POST
    public UserDto add(@RequestBody UserCreateDto userCreateDto) {
        Objects.requireNonNull(userCreateDto, "empty request body");
        final User receivedUser = convert(userCreateDto);
        final User addedUser = userRepository.save(receivedUser);
        return convert(addedUser);
    }

    @Path("/")
    @GET
    public List<UserDto> list() {
        return userRepository.findAll().stream()
                .map(this::convert)
                .collect(Collectors.toList());
    }

    @Path("/{id}")
    @PUT
    public UserDto update(@PathParam("id") int id, @RequestBody UserCreateDto userCreateDto) {
        final User userToUpdate = userRepository.findOne(id);
        if (userToUpdate == null) {
            // надо возвращать 404, если нет такого ресурса
            throw new WebApplicationException(Response.Status.NOT_FOUND);
        }
        userToUpdate.setLogin(userCreateDto.getLogin());
        userToUpdate.setPassword(userCreateDto.getPassword());
        return convert(userRepository.save(userToUpdate));
    }

    @Path("/{id}")
    @GET
    public UserDto get(@PathParam("id") int id) {
        final User user = userRepository.findOne(id);
        if (user == null) {
            // надо возвращать 404, если нет такого ресурса

            throw new WebApplicationException(Response.Status.NOT_FOUND);
        }
        return convert(user);
    }


    @Path("/{id}")
    @DELETE
    public void delete(@PathParam("id") int id) {
        try {
            userRepository.delete(id);
        } catch (EmptyResultDataAccessException e) {
            // надо возвращать 404, если нет такого ресурса
            throw new WebApplicationException(Response.Status.NOT_FOUND);
        }
    }

    private UserDto convert(User user) {
        return new UserDto(user.getId(), user.getLogin(), user.getPassword());
    }

    private User convert(UserCreateDto userDto) {
        return new User(userDto.getLogin(), userDto.getPassword());
    }

}
