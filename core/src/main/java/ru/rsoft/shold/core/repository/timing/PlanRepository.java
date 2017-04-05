package ru.rsoft.shold.core.repository.timing;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.rsoft.shold.core.entity.timing.Plan;

/**
 * Created by Admin on 17.07.2016.
 */
public interface PlanRepository extends JpaRepository<Plan, Integer> {
        //Plan findByTargetVillageId(int villageId);
        Plan findBySecret(String secret);
}
