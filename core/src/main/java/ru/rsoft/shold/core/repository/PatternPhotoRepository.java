package ru.rsoft.shold.core.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ru.rsoft.shold.core.entity.Pattern;
import ru.rsoft.shold.core.entity.PatternPhoto;

import java.util.List;
import java.util.Set;

/**
 * Created by Admin on 08.05.2016.
 */
public interface PatternPhotoRepository extends JpaRepository<PatternPhoto, Integer> {
    @Query(nativeQuery = true, value = "select * from PATTERN_PHOTO WHERE PATTERN_ID = :patternId")
    List<PatternPhoto> findByPatternId(@Param("patternId") int patternId);
}
