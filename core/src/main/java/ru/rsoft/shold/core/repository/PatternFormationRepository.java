package ru.rsoft.shold.core.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ru.rsoft.shold.core.entity.PatternFormation;

import java.util.List;

/**
 * Created by Admin on 09.05.2016.
 */
public interface PatternFormationRepository extends JpaRepository<PatternFormation, Integer> {
    @Query(nativeQuery = true, value = "select * from PATTERN_FORMATION WHERE PATTERN_ID = :patternId")
    List<PatternFormation> findByPatternId(@Param("patternId") int patternId);
}
