package ru.rsoft.shold.backend.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import ru.rsoft.shold.core.entity.Friend;
import ru.rsoft.shold.core.entity.Player;
import ru.rsoft.shold.core.repository.FriendsRepository;
import ru.rsoft.shold.core.repository.PlayerRepository;

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

    @Autowired
    public PlayerService(PlayerRepository playerRepository, FriendsRepository friendsRepository) {
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
}
