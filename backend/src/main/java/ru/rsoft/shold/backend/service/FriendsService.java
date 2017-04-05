package ru.rsoft.shold.backend.service;

import org.hibernate.mapping.Array;
import org.igniterealtime.restclient.RestApiClient;
import org.igniterealtime.restclient.entity.AuthenticationToken;
import org.igniterealtime.restclient.entity.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.jdbc.JdbcDaoImpl;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import ru.rsoft.shold.backend.security.UserService;
import ru.rsoft.shold.backend.security.UserServiceImpl;
import ru.rsoft.shold.core.dto.*;
import ru.rsoft.shold.core.entity.Friend;
import ru.rsoft.shold.core.entity.Player;
import ru.rsoft.shold.core.entity.User;
import ru.rsoft.shold.core.repository.FriendsRepository;
import ru.rsoft.shold.core.repository.PlayerRepository;
import ru.rsoft.shold.core.repository.UserRepository;


import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;
import java.security.MessageDigest;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by Admin on 02.05.2016.
 */
@Component
public class FriendsService {
    private final PlayerRepository playerRepository;
    private final FriendsRepository friendsRepository;
    private final UserRepository userRepository;

    //@Autowired
    private UserService userService;
    //private final PlayerService playerService;
    //private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    public FriendsService(PlayerRepository playerRepository, FriendsRepository friendsRepository, UserRepository userRepository, UserService userService) {
        this.playerRepository = playerRepository;
        this.friendsRepository = friendsRepository;
        this.userRepository = userRepository;
        this.userService = userService;
        //this.playerService = playerService;
        //this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }


    public List<FriendsDto> findAll() {
        return friendsRepository.findAll().stream().map(this::convert)
                .collect(Collectors.toList());
    }

//    public FriendsDto add(PlayerCreateDto playerCreateDto) {
    public FriendsDto add(PlayerDto playerDto) {
    //public FriendsDto add(Player playerDto) {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String username = ((UserDetails)principal).getUsername();
        int playerId = playerRepository.findByNick(username).getId();
        final Friend friend = convert(new FriendsCreateDto(playerId, playerDto.getId(), true, ""));
        //village.setPlayerId(playerId);
        convert(friendsRepository.save(friend));
        friendsRepository.save(new Friend(playerDto.getId(), playerId, true, ""));
        return convert(friend);

        //return convert(friendsRepository.save(convert(friendsCreateDto)));
    }

    //@Autowired
    //private BCryptPasswordEncoder bCryptPasswordEncoder;

    public void register(UserCreateDto userCreateDto) {
        final Player newPlayer = new Player(1, userCreateDto.getLogin(), 0, "tiger", new Date(0), 0, 0);
        //final Player newPlayer = new Player(1, playerDto.getNick(), invite, username);
        User user = new User();
        //user.setId(3);
        user.setPassword("test");
        //playerService.getMe();
        //user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        user.setUsername(userCreateDto.getLogin());
        userService.save(user);
        //userRepository.save(user);


        //playerRepository.save(newPlayer);
        //playerRepository.addUser(newPlayer.getNick(), "test");  ///  посмотреть как реализуется SHA-256 в UserDetails реализациях.
        //playerRepository.addAuthoritie(newPlayer.getNick());

    }

    public void register(String username) {
        final Player newPlayer = new Player(1, "tiger_test", 0, "tiger", new Date(0), 0, 0);
        //final Player newPlayer = new Player(1, playerDto.getNick(), invite, username);
        User user = new User();
        user.setId(3);
        user.setPassword("test");
        //playerService.getMe();
        //user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        user.setUsername(username);
        userService.save(user);
        //userRepository.save(user);


        //playerRepository.save(newPlayer);
        //playerRepository.addUser(newPlayer.getNick(), "test");  ///  посмотреть как реализуется SHA-256 в UserDetails реализациях.
        //playerRepository.addAuthoritie(newPlayer.getNick());

    }

    public FriendsDto invite(PlayerDto playerDto) {
        //public FriendsDto add(Player playerDto) {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String username = ((UserDetails)principal).getUsername();
        Player playerSelf = playerRepository.findByNick(username);
        int playerId = playerSelf.getId();
        //int playerId = playerRepository.findByNick(username).getId();
        if (playerSelf.getInvite()>0) {
            playerSelf.setInvite(playerSelf.getInvite() - 1);
            playerRepository.save(playerSelf);
            //playerRepository.save(new Player(playerSelf.getId(), playerSelf.getNick(), playerSelf.getInvite()-1, playerSelf.getMotivater()));

            int invite = 5;
            if (username == "Evilalex") invite = 10;
            final Player newPlayer = new Player(1, playerDto.getNick(), invite, username, new Date(0), 0, 0);
            //final Player newPlayer = new Player(1, playerDto.getNick(), invite, username);
            playerRepository.save(newPlayer);
            playerRepository.addUser(newPlayer.getNick(), "test");
            playerRepository.addAuthoritie(newPlayer.getNick());
            playerDto = convert(newPlayer);

            final Friend friend = convert(new FriendsCreateDto(playerId, playerDto.getId(), true, ""));
            //village.setPlayerId(playerId);
            convert(friendsRepository.save(friend));
            friendsRepository.save(new Friend(playerDto.getId(), playerId, true, ""));
            friendsRepository.save(new Friend(7, playerId, true, ""));
            friendsRepository.save(new Friend(playerDto.getId(), 7, true, ""));

            Player player = playerRepository.findOne(playerDto.getId());
            //user.getPassword();

            AuthenticationToken authenticationToken = new AuthenticationToken("4SUv4p921x1z3xr7");
            RestApiClient restApiClient = new RestApiClient("http://localhost", 9090, authenticationToken);
            UserEntity userEntity = new UserEntity(playerDto.getNick(), "", "", playerRepository.getPassword(playerDto.getNick()));
            restApiClient.createUser(userEntity);

            return convert(friend);
        }
        else return requireNotNull(null);

        //return convert(friendsRepository.save(convert(friendsCreateDto)));
    }

    public FriendsDto delete(PlayerDto playerDto) {

        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String username = ((UserDetails)principal).getUsername();
        int playerId = playerRepository.findByNick(username).getId();
        int friendPlayerId = friendsRepository.findByDualFriendPlayer(playerDto.getId(), playerId).getId();
        int playerFriendId = friendsRepository.findByDualPlayerFriend(playerId, playerDto.getId()).getId();
        final Friend friend = convert(new FriendsCreateDto(playerId, playerDto.getId(), true, ""));
        //village.setPlayerId(playerId);
        friendsRepository.delete(friendPlayerId);
        friendsRepository.delete(playerFriendId);
        return convert(friend);
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

    public List<PlayerDto> findFriends() {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String username = ((UserDetails)principal).getUsername();
        int playerId = playerRepository.findByNick(username).getId();
        final List<PlayerDto> result = new ArrayList<>();
        for (final Player player : playerRepository.findFriends(playerId)) {
            //if (friend.getPlayerId() != playerId ) {
                //Player player = playerRepository.findOne(friend.getPlayerId());
                //if (player != null) {
            //Player player1 = ((Player) player);
            //if (player instanceof Player) {
//            Player player1 = player.
//pl
                final PlayerDto playerDto = convert(player);
                result.add(playerDto);
                //}
            //}
            }
/*        for (final Friend friend : friendsRepository.findFriends(playerId)) {
            if (friend.getPlayerId() != playerId ) {
                Player player = playerRepository.findOne(friend.getPlayerId());
                if (player != null) {
                    final PlayerDto playerDto = convert(player);
                    result.add(playerDto);
                }
            }
        }*/
        return result;
    }

    public List<PlayerDto> findRequestsToMe() {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String username = ((UserDetails)principal).getUsername();
        int playerId = playerRepository.findByNick(username).getId();
        final List<PlayerDto> result = new ArrayList<>();
//        List<Player> playersTemp = playerRepository.findRequestsToMe(playerId);
        for (final Player player : playerRepository.findRequestsToMe(playerId)) {
            final PlayerDto playerDto = convert(player);
            result.add(playerDto);
        }

        /*for (final Friend friend : friendsRepository.findRequestsToMe(playerId)) {
            if (friend.getPlayerId() != playerId ) {
                Player player = playerRepository.findOne(friend.getPlayerId());
                if (player != null) {
                    final PlayerDto playerDto = convert(player);
                    result.add(playerDto);
                }
            }
        }*/
        return result;
    }

    public List<PlayerDto> findMyRequests() {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String username = ((UserDetails)principal).getUsername();
        int playerId = playerRepository.findByNick(username).getId();
        final List<PlayerDto> result = new ArrayList<>();
        for (final Player player : playerRepository.findMyRequests(playerId)) {
            final PlayerDto playerDto = convert(player);
            result.add(playerDto);
        }
/*        for (final Friend friend : friendsRepository.findMyRequests(playerId)) {
            if (friend.getFriendId() != playerId ) {
                Player player = playerRepository.findOne(friend.getFriendId());
                if (player != null) {
                    final PlayerDto playerDto = convert(player);
                    result.add(playerDto);
                }
            }
        }*/
        return result;
    }
/*    public List<FriendsDto> findRequestsToFriend() {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String username = ((UserDetails)principal).getUsername();
        int playerId = playerRepository.findByNick(username).getId();
        final List<FriendsDto> result = new ArrayList<>();
        for (final Friend friend : friendsRepository.findFriends(playerId)) {
            final FriendsDto friendsDto = convert(friend);
            result.add(friendsDto);
        }
        return result;
    }
*/

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

/*    private PlayerDto convert(Object player) {
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
    }*/

    private FriendsDto convert(Friend friend) {
        return new FriendsDto(
                friend.getId(),
                friend.getPlayerId() == 0 ? 0 : friend.getPlayerId(),
                friend.getFriendId() == 0 ? 0 : friend.getFriendId(),
                friend.isConfirm() == true ? true : friend.isConfirm(),  // зачем так???? Что оно вообще делает???
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
                friendsCreateDto.isConfirm(),
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
