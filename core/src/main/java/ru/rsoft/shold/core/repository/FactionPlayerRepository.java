package ru.rsoft.shold.core.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ru.rsoft.shold.core.entity.FactionPlayer;
import ru.rsoft.shold.core.entity.Player;

import java.util.List;

/**
 * Created by Admin on 30.12.2016.
 */
public interface FactionPlayerRepository extends JpaRepository<FactionPlayer, Integer> {
    List<FactionPlayer> findByFactionId(@Param("id") int id);
    //List<Player> findByFactionIdnew(@Param("id") int id);

    List<FactionPlayer> findByPlayerId(@Param("id") int id);
    //FactionPlayer addOne(@Param("factionId")int factionId, @Param("playerId") int playerId);
    //@Query(nativeQuery = true, value = "select PLAYER.* from PLAYER WHERE PLAYER.ID = :playerId AND  FRIENDS.PLAYER_ID = :playerId")
    //List<Player> findByFactionIdnew(@Param("friendId") int friendId, @Param("playerId") int playerId);
    @Query(nativeQuery = true, value = "select FactionPlayer.Officer from FactionPlayer WHERE FactionPlayer.Player_Id = :playerId AND  FactionPlayer.Faction_Id = :factionId")
    boolean isOfficer(@Param("playerId") int playerId, @Param("factionId") int factionId);
    @Query(nativeQuery = true, value = "select FactionPlayer.* from FactionPlayer WHERE FactionPlayer.Player_Id = :playerId AND  FactionPlayer.Faction_Id = :factionId")
    FactionPlayer getPlayer(@Param("playerId") int playerId, @Param("factionId") int factionId);
}
