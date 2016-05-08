package ru.rsoft.shold.core.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ru.rsoft.shold.core.entity.Player;

import java.util.List;

/**
 * Created by Admin on 26.11.2015.
 */
public interface PlayerRepository extends JpaRepository<Player, Integer> {
    //@Query(nativeQuery = true, value = "select f.* from PLAYERS f left join FRIENDS on FRIENDS.FRIEND_ID = f.id join PLAYERS p on FRIENDS.PLAYER_ID = :id")
    //@Query(nativeQuery = true, value = "select * from PLAYERS a join FRIENDS on FRIENDS.PLAYER_ID = a.id join PLAYERS b on FRIENDS.FRIEND_ID = b.id ")
    @Query(nativeQuery = true, value = "select PLAYERS.* from PLAYERS, FRIENDS WHERE FRIENDS.FRIEND_ID = PLAYERS.id AND  FRIENDS.PLAYER_ID = :id")
    List<Player> findFriends(@Param("id") int id);

    Player findByNick(String username);
}
