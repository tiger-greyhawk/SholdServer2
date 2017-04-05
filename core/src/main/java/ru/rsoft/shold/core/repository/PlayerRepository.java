package ru.rsoft.shold.core.repository;

import org.hibernate.annotations.NamedQuery;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
//import org.springframework.data.jpa.repository.

import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;
import ru.rsoft.shold.core.dto.FriendsDto;
import ru.rsoft.shold.core.entity.Player;

import java.util.List;

/**
 * Created by Admin on 26.11.2015.
 */
public interface PlayerRepository extends JpaRepository<Player, Integer> {
    //@Query(nativeQuery = true, value = "select f.* from PLAYERS f left join FRIENDS on FRIENDS.FRIEND_ID = f.id join PLAYERS p on FRIENDS.PLAYER_ID = :id")
    //@Query(nativeQuery = true, value = "select * from PLAYERS a join FRIENDS on FRIENDS.PLAYER_ID = a.id join PLAYERS b on FRIENDS.FRIEND_ID = b.id ")
    @Query(nativeQuery = true, value = "select PLAYERS.* from PLAYERS, FRIENDS WHERE FRIENDS.FRIEND_ID = PLAYERS.id AND  FRIENDS.PLAYER_ID = :id")
    List<Player> findFriendsOld(@Param("id") int id);

    //@Query(nativeQuery = true, value = "INSERT INTO USERS_MY (username, password, enabled) VALUES (:username, test, 1)")
    @Modifying
    @Query(nativeQuery = true, value = "INSERT INTO users (username, password, enabled) VALUES (:username, :password, 1)")
    //@Query(nativeQuery = true, value = "INSERT INTO USERS_MY (id, login, password) VALUES (10, :username, 'test')")
    @Transactional
    void addUser(@Param("username") String username, @Param("password") String password);

    //@Query(nativeQuery = true, value = "INSERT INTO authorities (username, authority) VALUES (:username, 'ROLE_USER')")
    @Modifying
    @Query(nativeQuery = true, value = "INSERT INTO authorities (username, authority) VALUES (:username, 'ROLE_USER')")
    //@Query(nativeQuery = true, value = "INSERT INTO USERS_MY (id, login, password) VALUES (11, :username, 'test')")
    @Transactional
    void addAuthoritie(@Param("username") String username);

    @Query(nativeQuery = true, value = "SELECT password FROM users WHERE username = :username")
    String getPassword(@Param("username") String username);

    Player findByNick(String username);

    @Query(nativeQuery = true, value = "SELECT first.* FROM PLAYERS first, FactionPlayer second WHERE second.Faction_Id = :factionId AND first.ID = second.Player_Id")
    //@Query(nativeQuery = true, value = "SELECT first.* FROM PLAYERS first WHERE EXISTS (SELECT second.* FROM FactionPlayer second WHERE second.Faction_Id = :factionId)")
    List<Player> findByFactionId(@Param("factionId") int factionId);

    @Query(nativeQuery = true, value = "SELECT DISTINCT first.* FROM PLAYERS first, FRIENDS second WHERE NOT EXISTS ( SELECT * FROM FRIENDS third WHERE second.PLAYER_ID = third.FRIEND_ID AND second.FRIEND_ID = third.PLAYER_ID) AND second.FRIEND_ID = :playerId AND first.ID = second.PLAYER_ID")
    List<Player> findRequestsToMe(@Param("playerId") int playerId);

    @Query(nativeQuery = true, value = "SELECT DISTINCT first.* FROM PLAYERS first, FRIENDS second WHERE NOT EXISTS ( SELECT * FROM FRIENDS third WHERE second.PLAYER_ID = third.FRIEND_ID AND second.FRIEND_ID = third.PLAYER_ID) AND second.PLAYER_ID = :playerId AND first.ID = second.FRIEND_ID")
    List<Player> findMyRequests(@Param("playerId") int playerId);

    //SELECT DISTINCT first.* FROM PLAYERS first, FRIENDS second WHERE EXISTS ( SELECT * FROM FRIENDS third WHERE second.PLAYER_ID = third.FRIEND_ID AND second.FRIEND_ID = third.PLAYER_ID) AND second.PLAYER_ID = 2 AND first.ID = second.FRIEND_ID
    @Query(nativeQuery = true, value = "SELECT DISTINCT first.* FROM PLAYERS first, FRIENDS second WHERE EXISTS ( SELECT third.* FROM FRIENDS third WHERE second.PLAYER_ID = third.FRIEND_ID AND second.FRIEND_ID = third.PLAYER_ID) AND second.PLAYER_ID = :playerId AND first.ID = second.FRIEND_ID")
    List<Player> findFriends(@Param("playerId") int playerId);

    //@Query(nativeQuery = true, value = "SELECT DISTINCT first.* FROM PLAYERS first, FRIENDS second WHERE EXISTS ( SELECT third.* FROM FRIENDS third WHERE second.PLAYER_ID = third.FRIEND_ID AND second.FRIEND_ID = third.PLAYER_ID) AND second.PLAYER_ID = :playerId AND first.ID = second.FRIEND_ID")
    //List<Player> findAllAllieses(@Param("playerId") int playerId);
}
