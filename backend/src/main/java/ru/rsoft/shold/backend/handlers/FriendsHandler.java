package ru.rsoft.shold.backend.handlers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.rsoft.shold.backend.service.FriendsService;
import ru.rsoft.shold.core.dto.FriendsDto;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
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

    @Path("/{id}")
    @GET
    public List<FriendsDto> get(@PathParam("id") int id) {
        return friendsService.findByPlayerId(id);
    }
}
