package ru.rsoft.shold.core.repository.timing;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ru.rsoft.shold.core.entity.timing.PlanAttack;


import java.util.Date;
import java.util.List;
import java.util.Set;

/**
 * Created by Admin on 15.06.2016.
 */
public interface PlanAttackRepository extends JpaRepository<PlanAttack, Integer> {
    Set<PlanAttack> findByPlayerId(int villageId);
    List<PlanAttack> findBySecret(String secret);
    @Query(nativeQuery = true, value = "select * from PLANATTACK where timestamp between :startDate and :endDate")
    Set<PlanAttack> findByTimestampAfter(@Param("startDate") Date startDate, @Param("endDate") Date endDate);
}
