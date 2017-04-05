package ru.rsoft.shold.backend.service;

import org.igniterealtime.restclient.RestApiClient;
import org.igniterealtime.restclient.entity.AuthenticationToken;
import org.igniterealtime.restclient.entity.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;
import ru.rsoft.shold.core.dto.PlayerCreateDto;
import ru.rsoft.shold.core.dto.PlayerDto;
import ru.rsoft.shold.core.entity.Friend;
import ru.rsoft.shold.core.entity.Player;
import ru.rsoft.shold.core.entity.User;
import ru.rsoft.shold.core.repository.FriendsRepository;
import ru.rsoft.shold.core.repository.PlayerRepository;
import ru.rsoft.shold.core.repository.UserRepository;

import javax.ws.rs.PathParam;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by Admin on 01.05.2016.
 */
@Component
@Transactional(propagation = Propagation.REQUIRED)
public class PlayerService {

    private final PlayerRepository playerRepository;
    private final FriendsRepository friendsRepository;
    private final UserRepository userRepository;

    @Autowired
    public PlayerService(UserRepository userRepository, PlayerRepository playerRepository, FriendsRepository friendsRepository) {
        this.userRepository = userRepository;
        this.playerRepository = playerRepository;
        this.friendsRepository = friendsRepository;
    }

    /**
     * Retrieve a player's friends by player id.
     *
     * @param playerId player to retrieve friends for
     * @return a new list of {@link Player}
     */
    public List<Player> getPlayerFriends(final int playerId) {
        final List<Friend> friends = friendsRepository.findByPlayerId(playerId);
        final List<Integer> friendIds = friends.parallelStream()
                .map(Friend::getPlayerId)
                .collect(Collectors.toList());
        return playerRepository.findAll(friendIds);
    }

    public List<PlayerDto> getPlayersByFaction(final int factionId) {
        int factionIdTemp = getMe().getFactionId();
        if (factionId == factionIdTemp)
        return playerRepository.findByFactionId(factionId).stream().map(this::convert).collect(Collectors.toList());
        else return playerRepository.findByFactionId(factionId).stream().map(this::convertNick).collect(Collectors.toList());
        //return playerRepository.findByFactionId(factionId);
    }

    public List<PlayerDto> getAll()  {
        return playerRepository.findAll().stream().map(this::convertNick)
                .collect(Collectors.toList());
    }

    public PlayerDto getOne(@PathParam("id") Integer id) {
        return convert(requireNotNull(playerRepository.findOne(id)));
    }

    public PlayerDto getMe() {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
//        List<PatternDto> temp = new ArrayList<PatternDto>();

        String username = ((UserDetails)principal).getUsername();
        Player player = playerRepository.findByNick(username);
        player.setLastAccess(new Date());
        //PlayerCreateDto playerCreateDto = player;
        convert(playerRepository.save(player));
        int playerId = playerRepository.findByNick(username).getId();
        return convert(requireNotNull(playerRepository.findOne(playerId)));
    }

    public PlayerDto addOne(@RequestBody PlayerCreateDto playerCreateDto) {


        return convert(playerRepository.save(convert(playerCreateDto)));
    }

    public void deleteOne(@PathParam("id") int id) {
        try {
            new WebApplicationException(Response.Status.FORBIDDEN);
            //playerRepository.delete(id);
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
                player.getUserId(),

//                village.getBody() == null ? null : village.getBody().getId(),
                player.getNick(),
                player.getInvite(),
                player.getMotivater(),
                player.getLastAccess(),
                player.getFactionId(),
                player.getFactionIdInvited()
//                player.getFriends()

        );
    }

    private PlayerDto convertNick(Player player) {
//        if (village.getUser() == null) { userRepository.findOne(1);}
        return new PlayerDto(
                player.getId(),
//                village.getUser().getId(),
                0,

//                village.getBody() == null ? null : village.getBody().getId(),
                player.getNick(),
                0,
                "forbidden",
                new Date(0),
                0,
                0

        );
    }

    private Player convert(PlayerCreateDto playerCreateDto) {
        /**
         * Что будет, если user не объявлять как final?
         **/

        /*TODO
        Подумать о привязке к Spring Security, а не к UserRepo.
         */
        User user = requireNotNull(userRepository.findOne(playerCreateDto.getUserId()));
        //final User user = requireNotNull(SecurityContextHolder.getContext().getAuthentication().getPrincipal());

        if (user == null){user = (userRepository.findOne(1));}
        return new Player(
                user.getId(),
                playerCreateDto.getNick(),
                playerCreateDto.getInvite(),
                playerCreateDto.getMotivater(),
                playerCreateDto.getLastAccess(),
                playerCreateDto.getFactionId(),
                playerCreateDto.getFactionIdInvited()
                //playerCreateDto.getLastRequest()
        );
    }
}
