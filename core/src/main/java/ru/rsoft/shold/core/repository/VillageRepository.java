package ru.rsoft.shold.core.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.rsoft.shold.core.entity.Village;

import java.util.Set;

/**
 * Created by Admin on 22.11.2015.
 */
@Repository
public interface VillageRepository extends JpaRepository<Village, Integer> {
    Set<Village> findByPlayerId(int playerId);
}
