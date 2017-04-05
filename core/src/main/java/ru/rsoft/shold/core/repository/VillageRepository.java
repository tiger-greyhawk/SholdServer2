package ru.rsoft.shold.core.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ru.rsoft.shold.core.entity.Village;

import java.util.Set;

/**
 * Created by Admin on 22.11.2015.
 */
@Repository
public interface VillageRepository extends JpaRepository<Village, Integer> {
    @Query(nativeQuery = true, value = "select * from VILLAGES WHERE VILLAGES.ID_IN_WORLD = :idInWorld")
    Village findByIdInWorld(@Param("idInWorld") int idInWorld);
    Set<Village> findByPlayerId(int playerId);
}
