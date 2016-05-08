package ru.rsoft.shold.core.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ru.rsoft.shold.core.entity.Pattern;

import java.util.List;
import java.util.Set;

/**
 * Created by Admin on 18.04.2016.
 */

public interface PatternRepository extends JpaRepository<Pattern, Integer> {
    Set<Pattern> findByPlayerId(int playerId);
//    @Query(nativeQuery = true, value = "select PATTERNS.NAME, PATTERNS.TYPE_CASTLE, PATTERNS.PLAYER_ID from PATTERNS WHERE PATTERNS.PLAYER_ID = :playerId")
    @Query(nativeQuery = true, value = "select PATTERNS.NAME, PATTERNS.PLAYER_ID from PATTERNS WHERE PATTERNS.PLAYER_ID = :playerId")
    List<Pattern> findByPlayerIdWithoutFiles(@Param("playerId") int playerId);
    //Set<Pattern> findOne(int id);
//    Set<User> getId();

}