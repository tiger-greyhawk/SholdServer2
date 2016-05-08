package ru.rsoft.configurator.backend.handlers;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestBody;
import ru.rsoft.configurator.core.dto.*;
import ru.rsoft.configurator.core.entity.Player;
import ru.rsoft.configurator.core.entity.User;
import ru.rsoft.configurator.core.entity.Village;
import ru.rsoft.configurator.core.repository.PlayerRepository;
import ru.rsoft.configurator.core.repository.UserRepository;
import ru.rsoft.configurator.core.repository.VillageRepository;
import ru.rsoft.configurator.backend.handlers.VillageHandler;

import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Created by Admin on 26.11.2015.
 */
@Component
@Path("/player")
public class PlayerHandler {
    private final UserRepository userRepository;
    private final PlayerRepository playerRepository;
    private final VillageRepository villageRepository;
//    private final Village village;

    @Autowired
    public PlayerHandler(UserRepository userRepository, PlayerRepository playerRepository, VillageRepository villageRepository) {//, Village village) {
        this.userRepository = userRepository;
        this.playerRepository = playerRepository;
        this.villageRepository = villageRepository;
//        this.village = village;
    }
    @Path("/")
    @GET
    public List<PlayerDto> list()  {
        return playerRepository.findAll().stream().map(this::convert)
                .collect(Collectors.toList());
    }

    public List<Village> villages = new ArrayList<Village>();

    @Path("/{id}")
    @GET
    public PlayerDto get(@PathParam("id") Integer id) {
        return convert(requireNotNull(playerRepository.findOne(id)));
    }

    @Path("/me")
    @GET
    public PlayerDto getMe() {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
//        List<PatternDto> temp = new ArrayList<PatternDto>();

        String username = ((UserDetails)principal).getUsername();
        int playerId = playerRepository.findByNick(username).getId();
        return convert(requireNotNull(playerRepository.findOne(playerId)));
    }

    @Path("/")
    @POST
    public PlayerDto add(@RequestBody PlayerCreateDto playerCreateDto) {
        return convert(playerRepository.save(convert(playerCreateDto)));
    }

    @Path("/{id}")
    @DELETE
    public void delete(@PathParam("id") int id) {
        try {
            playerRepository.delete(id);
        } catch (EmptyResultDataAccessException e) {
            // надо возвращать 404, если нет такого ресурса
            throw new WebApplicationException(Response.Status.NOT_FOUND);
        }
    }



    private <T> T requireNotNull(T object) {
        if (object == null) {
            throw new WebApplicationException(Response.Status.NOT_FOUND);
        }
        return object;
    }

    private PlayerDto convert(Player player) {
//        if (village.getUser() == null) { userRepository.findOne(1);}
        return new PlayerDto(
                player.getId(),
//                village.getUser().getId(),
                player.getUser() == null ? null : player.getUser().getId(),

//                village.getBody() == null ? null : village.getBody().getId(),
                player.getNick()
//                player.getFriends()

        );
    }
    private Player convert(PlayerCreateDto playerCreateDto) {
        /**
         * Что будет, если user не объявлять как final?
         **/
        User user = requireNotNull(userRepository.findOne(playerCreateDto.getUserId()));
        //final User user = requireNotNull(SecurityContextHolder.getContext().getAuthentication().getPrincipal());

        if (user == null){user = (userRepository.findOne(1));}
        return new Player(
                user,
                playerCreateDto.getNick()
//                playerCreateDto.getFriends()

        );
    }
}
