package ru.rsoft.shold.core.repository.timing;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.rsoft.shold.core.entity.timing.PlanTarget;

/**
 * Created by Admin on 14.08.2016.
 */
public interface PlanTargetRepository  extends JpaRepository<PlanTarget, Integer> {
    PlanTarget findByTargetVillageId(int villageId);
    PlanTarget findByPlanId(int planId);
}
