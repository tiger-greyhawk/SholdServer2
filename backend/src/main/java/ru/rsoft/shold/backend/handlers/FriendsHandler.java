package ru.rsoft.shold.backend.handlers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestBody;
import ru.rsoft.shold.backend.service.FriendsService;
import ru.rsoft.shold.core.dto.*;
import ru.rsoft.shold.core.entity.Player;

import javax.ws.rs.*;
import java.util.List;

/**
 * Created by Admin on 02.05.2016.
 */
@Component
@Path("/friends")
public class FriendsHandler {

    private final FriendsService friendsService;

    @Autowired
    public FriendsHandler(FriendsService friendsService) {
        this.friendsService = friendsService;
    }

    @Path("/")
    @GET
    public List<FriendsDto> list() {
        return friendsService.findAll();
    }

    @Path("/my")
    @GET
    public List<PlayerDto> findFriends() {
        return friendsService.findFriends();
    }

    @Path("/requeststome")
    @GET
    public List<PlayerDto> findRequestsToMe() {
        return friendsService.findRequestsToMe();
    }

    @Path("/myrequests")
    @GET
    public List<PlayerDto> findMyRequests() {
        return friendsService.findMyRequests();
    }

    @Path("/{id}")
    @GET
    public List<FriendsDto> get(@PathParam("id") int id) {
        return friendsService.findByPlayerId(id);
    }

    @Path("/")
    @POST
    //public FriendsDto add(@RequestBody PlayerCreateDto playerCreateDto)
    public FriendsDto add(@RequestBody PlayerDto playerDto)
    {
        return (friendsService.add(playerDto));
    }

    @Path("/")
    @DELETE
    public FriendsDto delete(@RequestBody PlayerDto playerCreateDto)
    {
        return friendsService.delete(playerCreateDto);
    }

    @Path("/register/{username}")
    @GET
    public void register(@PathParam("username") String username)
    {
        //return
        friendsService.register(username);
    }

    @Path("/register")
    @POST
    public void registerNewUser(@RequestBody UserCreateDto userCreateDto)
    {
        //return
        friendsService.register(userCreateDto);
    }

    @Path("/invite")
    @POST
    public FriendsDto invite(@RequestBody PlayerDto playerDto)
    {
        return friendsService.invite(playerDto);
    }
}
