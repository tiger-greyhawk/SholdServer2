package ru.rsoft.configurator.core.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.rsoft.configurator.core.entity.Pattern;

import java.util.Set;

/**
 * Created by Admin on 18.04.2016.
 */

public interface PatternRepository extends JpaRepository<Pattern, Integer> {
    Set<Pattern> findByPlayerId(int playerId);
    //Set<Pattern> findOne(int id);
//    Set<User> getId();

}