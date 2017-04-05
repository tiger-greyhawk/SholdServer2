package ru.rsoft.shold.core.repository.requests;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ru.rsoft.shold.core.entity.requests.SendResources;

import java.util.Date;
import java.util.List;
import java.util.Set;

/**
 * Created by Admin on 24.06.2016.
 */
public interface SendResourcesRepository extends JpaRepository<SendResources, Integer> {
    List<SendResources> findByResourcesId(int resourcesId);
    @Query(nativeQuery = true, value = "select * from SEND_RESOURCES where timestamp between :startDate and :endDate")
    Set<SendResources> findByTimestampAfter(@Param("startDate") Date startDate, @Param("endDate") Date endDate);
}
