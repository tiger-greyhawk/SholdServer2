package ru.rsoft.configurator.backend.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.rsoft.configurator.core.dto.FriendsCreateDto;
import ru.rsoft.configurator.core.dto.FriendsDto;
import ru.rsoft.configurator.core.dto.PlayerDto;
import ru.rsoft.configurator.core.entity.Friends;
import ru.rsoft.configurator.core.entity.Player;
import ru.rsoft.configurator.core.repository.FriendsRepository;
import ru.rsoft.configurator.core.repository.PlayerRepository;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by Admin on 02.05.2016.
 */
@Component
public class FriendsService {
    private final PlayerRepository playerRepository;
    private final FriendsRepository friendsRepository;

    @Autowired
    public FriendsService(PlayerRepository playerRepository, FriendsRepository friendsRepository) {
        this.playerRepository = playerRepository;
        this.friendsRepository = friendsRepository;
    }


    public List<FriendsDto> findAll() {
        return friendsRepository.findAll().stream().map(this::convert)
                .collect(Collectors.toList());
    }

    public FriendsDto add(FriendsCreateDto friendsCreateDto) {
        return convert(friendsRepository.save(convert(friendsCreateDto)));
    }

    public List<PlayerDto> findByPlayerId(int id) {
        return playerRepository.findFriends(id).stream().map(this::convert)
                .collect(Collectors.toList());
        //return convert(requireNotNull(friendsRepository.findOne(id)));
    }

    private PlayerDto convert(Player player) {
//        if (village.getUser() == null) { userRepository.findOne(1);}
        return new PlayerDto(
                player.getId(),
//                village.getUser().getId(),
                player.getUser() == null ? null : player.getUser().getId(),

//                village.getBody() == null ? null : village.getBody().getId(),
                player.getNick()

        );
    }

    private FriendsDto convert(Friends friends) {
        return new FriendsDto(
                friends.getId(),
//                friends.getPlayerId() == 0 ? 0 : friends.getPlayerId(),
                friends.getFriend() == null ? null : friends.getFriend(),
                friends.getComment()

        );
    }

    private Friends convert(FriendsCreateDto friendsCreateDto) {
        final Player player = requireNotNull(playerRepository.findOne(friendsCreateDto.getPlayerId()));
        //final Player player = requireNotNull(playerRepository.findOne(patternCreateDto.getPlayerId()));
        //final Player player = requireNotNull(SecurityContextHolder.getContext().getAuthentication().getPrincipal());

        return new Friends(
                friendsCreateDto.getPlayerId(),
                friendsCreateDto.getFriend(),
                friendsCreateDto.getComment()

        );
    }

    private <T> T requireNotNull(T object) {
        if (object == null) {
            throw new WebApplicationException(Response.Status.NOT_FOUND);
        }
        return object;
    }
}
