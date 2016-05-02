package ru.rsoft.configurator.core.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ru.rsoft.configurator.core.entity.Friends;
import ru.rsoft.configurator.core.entity.Player;

import java.util.Date;
import java.util.List;
import java.util.Set;

/**
 * Created by Admin on 27.04.2016.
 */
public interface FriendsRepository extends JpaRepository<Friends, Integer> {
//    Set<Friends> findByPlayerId(int playerId);

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
//    Set<Friends> findFriends(@Param("startDate") Date startDate, @Param("endDate") Date endDate);
//    @Query(nativeQuery = true, value = "select p.* from (PLAYERS f left join FRIENDS on FRIENDS.FRIEND_ID = f.id) join PLAYERS p on FRIENDS.PLAYER_ID = :id")
    @Query(nativeQuery = true, value = "select f.* from (PLAYERS f left join FRIENDS pf on f.id = pf.FRIEND_ID) join PLAYERS p on pf.PLAYER_ID = :id")
//    @Query(nativeQuery = true, value = "select * from PLAYERS, FRIENDS where FRIENDS.ID = :id")
    List<Player> findByPlayerId(@Param("id") int id);
}
