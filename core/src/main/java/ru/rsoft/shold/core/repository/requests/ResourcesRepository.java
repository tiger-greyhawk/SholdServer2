package ru.rsoft.shold.core.repository.requests;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ru.rsoft.shold.core.entity.requests.Resources;

import java.util.Date;
import java.util.Set;

/**
 * Created by Admin on 24.04.2016.
 */
public interface ResourcesRepository extends JpaRepository<Resources, Integer> {
    Set<Resources> findByVillageId(int villageId);
    @Query(nativeQuery = true, value = "select * from RESOURCES where timestamp between :startDate and :endDate")
    Set<Resources> findByTimestampAfter(@Param("startDate") Date startDate, @Param("endDate") Date endDate);
}
