package ru.rsoft.shold.core.repository.requests;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ru.rsoft.shold.core.entity.requests.ClosedResources;
import ru.rsoft.shold.core.entity.requests.Resources;

import java.util.Date;
import java.util.Set;

/**
 * Created by Admin on 24.04.2016.
 */
public interface ClosedResourcesRepository extends JpaRepository<ClosedResources, Integer> {
    Set<ClosedResources> findByVillageId(int villageId);
    @Query(nativeQuery = true, value = "select * from CLOSED_RESOURCES where timestamp between :startDate and :endDate && PLAYER_ID = :playerId")
    Set<ClosedResources> findByTimestampAfter(@Param("playerId") int playerId, @Param("startDate") Date startDate, @Param("endDate") Date endDate);
    Set<ClosedResources> findByPlayerId(@Param("playerId") int playerId);
}
