package ru.rsoft.shold.backend.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.rsoft.shold.core.dto.FriendsCreateDto;
import ru.rsoft.shold.core.dto.FriendsDto;
import ru.rsoft.shold.core.dto.PlayerDto;
import ru.rsoft.shold.core.entity.Friend;
import ru.rsoft.shold.core.entity.Player;
import ru.rsoft.shold.core.repository.FriendsRepository;
import ru.rsoft.shold.core.repository.PlayerRepository;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;
import java.util.ArrayList;
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

    public List<FriendsDto> findByPlayerId(int id) {
        final List<FriendsDto> result = new ArrayList<>();
        for (final Friend friend : friendsRepository.findByPlayerId(id)) {
            final FriendsDto friendsDto = convert(friend);
            result.add(friendsDto);
        }
        return result;
        /*
        return friendsRepository.findByPlayerId(id).stream()
                .map(this::convert)
                .collect(Collectors.toList());
        */
    }

    private PlayerDto convert(Player player) {
//        if (village.getUser() == null) { userRepository.findOne(1);}
        return new PlayerDto(
                player.getId(),
//                village.getUser().getId(),
                player.getUserId(),

//                village.getBody() == null ? null : village.getBody().getId(),
                player.getNick()
//                player.getFriends()

        );
    }

    private FriendsDto convert(Friend friend) {
        return new FriendsDto(
                friend.getId(),
                friend.getPlayerId() == 0 ? 0 : friend.getPlayerId(),
                friend.getFriendId() == 0 ? 0 : friend.getFriendId(),
                friend.getComment()

        );
    }

    private Friend convert(FriendsCreateDto friendsCreateDto) {
        final Player player = requireNotNull(playerRepository.findOne(friendsCreateDto.getPlayerId()));
        //final Player player = requireNotNull(playerRepository.findOne(patternCreateDto.getPlayerId()));
        //final Player player = requireNotNull(SecurityContextHolder.getContext().getAuthentication().getPrincipal());

        return new Friend(
                friendsCreateDto.getPlayerId(),
                friendsCreateDto.getFriendId(),
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
