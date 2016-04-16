package ru.rsoft.configurator.core.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import ru.rsoft.configurator.core.entity.Village;


import java.util.Set;

/**
 * Created by Admin on 22.11.2015.
 */
public interface VillageRepository extends JpaRepository<Village, Integer> {
    Set<Village> findByPlayerId(int playerId);
}
