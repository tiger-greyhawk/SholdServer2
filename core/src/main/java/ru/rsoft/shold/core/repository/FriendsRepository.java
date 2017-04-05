package ru.rsoft.shold.core.repository;

import org.hibernate.annotations.Target;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;
import ru.rsoft.shold.core.entity.Friend;
import ru.rsoft.shold.core.entity.Player;

import javax.persistence.EntityResult;
import java.util.List;

/**
 * Created by Admin on 27.04.2016.
 */
public interface FriendsRepository extends JpaRepository<Friend, Integer> {
//    Set<Friend> findByPlayerId(int playerId);

    // Выбрать игроков из player где Id = всем (выбрать friendsId из friends где playerId = моему id) таблицы друзья, где playerId совпадает с моим.
    // select * from player where Id = all (select friendsId from friends where playerid=1)

    // `mag_id`, `mag_name` - `mags`
    // `tov_id`, `tov_name` - `tovs`
    // `mt_mag_id`, `mt_tov_id` - `mag_tov`
    // id, nick - player
    // id - friends
    // player_id, friend_id -
    //SELECT m.*, t.* FROM (mags m JOIN mag_tov mt ON m.mag_id=mt.mt_mag_id) JOIN tovs t ON mt.mt_tov_id=t.tov_id WHERE tov_name IN ('молоко', 'мясо', 'чай')
//    @Query(nativeQuery = true, value = "select * from FRIENDS where timestamp between :startDate and :endDate")
//    Set<Friend> findFriends(@Param("startDate") Date startDate, @Param("endDate") Date endDate);
//    @Query(nativeQuery = true, value = "select p.* from (PLAYERS f left join FRIENDS on FRIENDS.FRIEND_ID = f.id) join PLAYERS p on FRIENDS.PLAYER_ID = :id")
//    @Query(nativeQuery = true, value = "select * from PLAYERS, FRIENDS where FRIENDS.ID = :id")
//    @Query(nativeQuery = true, value = "select f.* from (PLAYERS f left join FRIENDS pf on f.id = pf.FRIEND_ID) join PLAYERS p on pf.PLAYER_ID = :id")
    //@Query(nativeQuery = true, value = "select FRIENDS.* from PLAYERS, FRIENDS WHERE FRIENDS.FRIEND_ID = PLAYERS.id AND  FRIENDS.PLAYER_ID = :id")
//    @Query(nativeQuery = true, value = "select FRIENDS.* from PLAYERS, FRIENDS WHERE FRIENDS.FRIEND_ID = PLAYERS.id AND FRIENDS.PLAYER_ID = :id")
    List<Friend> findByPlayerId(@Param("id") int id);
    @Query(nativeQuery = true, value = "select FRIENDS.* from FRIENDS WHERE FRIENDS.FRIEND_ID = :friendId AND  FRIENDS.PLAYER_ID = :playerId")
    Friend findByDualFriendPlayer(@Param("friendId") int friendId, @Param("playerId") int playerId);
    @Query(nativeQuery = true, value = "select FRIENDS.* from FRIENDS WHERE FRIENDS.FRIEND_ID = :playerId AND  FRIENDS.PLAYER_ID = :friendId")
    Friend findByDualPlayerFriend(@Param("playerId") int playerId, @Param("friendId") int friendId);
    @Query(nativeQuery = true, value = "select FRIENDS.* from FRIENDS WHERE (FRIENDS.FRIEND_ID = :playerId OR FRIENDS.PLAYER_ID = :playerId) AND CONFIRM = true")
    List<Friend> findFriendsOld(@Param("playerId") int playerId);
    //@Query(nativeQuery = true, value = "select f.* from (FRIENDS f1 WHERE EXIST (SELECT FRIENDS.* from FRIENDS f2 WHERE f2.PLAYER_ID = :playerId))")
    //@Query(nativeQuery = true, value = "SELECT DISTINCT first.* FROM FRIENDS first, FRIENDS second WHERE EXISTS (SELECT * FROM FRIENDS third WHERE second.PLAYER_ID = third.FRIEND_ID AND second.FRIEND_ID = third.PLAYER_ID)")
    //@Query(nativeQuery = true, value = "select DISTINCT f.* from (FRIENDS f left join FRIENDS pf on f.id = pf.FRIEND_ID) join FRIENDS p on pf.PLAYER_ID = :playerId")


    //@Query(nativeQuery = true, value = "SELECT DISTINCT result.* FROM PLAYERS result WHERE EXISTS ( SELECT first.* FROM FRIENDS first WHERE EXISTS (SELECT * FROM FRIENDS second WHERE :playerId = first.FRIEND_ID AND second.FRIEND_ID = :playerId))")
    //@Query(nativeQuery = true, value = "SELECT DISTINCT first.* FROM FRIENDS first WHERE EXISTS (SELECT * FROM FRIENDS second WHERE :playerId = first.PLAYER_ID AND second.FRIEND_ID <> :playerId)")
    //    Запрос возвращает List<Player> но вида Object
    //@Query(nativeQuery = true, value = "SELECT DISTINCT first.* FROM PLAYERS first, FRIENDS second WHERE NOT EXISTS ( SELECT * FROM FRIENDS third WHERE second.PLAYER_ID = third.FRIEND_ID AND second.FRIEND_ID = third.PLAYER_ID) AND second.FRIEND_ID = :playerId AND first.ID = second.PLAYER_ID")
    @Query(nativeQuery = true, value = "SELECT DISTINCT * FROM FRIENDS second WHERE NOT EXISTS ( SELECT * FROM FRIENDS third WHERE second.PLAYER_ID = third.FRIEND_ID AND second.FRIEND_ID = third.PLAYER_ID) AND second.FRIEND_ID = :playerId")
    List<Friend> findRequestsToMe(@Param("playerId") int playerId);
    //@Query(nativeQuery = true, value = "SELECT DISTINCT first.* FROM FRIENDS first WHERE EXISTS (SELECT * FROM FRIENDS second WHERE :playerId <> second.PLAYER_ID AND second.FRIEND_ID = :playerId)")
    //    Запрос возвращает List<Player> но вида Object
    //@Query(nativeQuery = true, value = "SELECT DISTINCT first.* FROM PLAYERS first, FRIENDS second WHERE NOT EXISTS ( SELECT * FROM FRIENDS third WHERE second.PLAYER_ID = third.FRIEND_ID AND second.FRIEND_ID = third.PLAYER_ID) AND second.PLAYER_ID = :playerId AND first.ID = second.FRIEND_ID")
    @Query(nativeQuery = true, value = "SELECT DISTINCT * FROM FRIENDS second WHERE NOT EXISTS ( SELECT * FROM FRIENDS third WHERE second.PLAYER_ID = third.FRIEND_ID AND second.FRIEND_ID = third.PLAYER_ID) AND second.PLAYER_ID = :playerId")
    List<Friend> findMyRequests(@Param("playerId") int playerId);





    /////////    *********   ПЕРЕНЕСЕНО В PLAYER_REPOSITORY    !!!!!!!!!!!!!!!!!!!!!!  Надо делать там!!!!
    ////  VVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVV




    //SELECT DISTINCT first.* FROM PLAYERS first, FRIENDS second WHERE EXISTS ( SELECT * FROM FRIENDS third WHERE second.PLAYER_ID = third.FRIEND_ID AND second.FRIEND_ID = third.PLAYER_ID) AND second.PLAYER_ID = 2 AND first.ID = second.FRIEND_ID
    //    Запрос возвращает List<Player> но вида Object
    //@Query(nativeQuery = true, value = "SELECT DISTINCT first.ID, first.INVITE, first.MOTIVATER, first.NICK, first.USER_ID, first.LAST_ACCESS, first.FACTION_ID, first.FACTION_ID_INVITED FROM PLAYERS first, FRIENDS second WHERE EXISTS ( SELECT third.* FROM FRIENDS third WHERE second.PLAYER_ID = third.FRIEND_ID AND second.FRIEND_ID = third.PLAYER_ID) AND second.PLAYER_ID = :playerId AND first.ID = second.FRIEND_ID")
    //@Transactional
    //@EntityResult(entityClass = Player Entity.class)
    @Query(nativeQuery = true, value = "SELECT DISTINCT * FROM FRIENDS second WHERE EXISTS ( SELECT * FROM FRIENDS third WHERE second.PLAYER_ID = third.FRIEND_ID AND second.FRIEND_ID = third.PLAYER_ID) AND second.FRIEND_ID = :playerId")
    List<Friend> findFriends(@Param("playerId") int playerId);
}
