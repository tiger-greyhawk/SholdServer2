package ru.rsoft.shold.backend.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.rsoft.shold.core.dto.PatternFormationCreateDto;
import ru.rsoft.shold.core.dto.PatternFormationDto;
import ru.rsoft.shold.core.entity.PatternFormation;
import ru.rsoft.shold.core.repository.PatternFormationRepository;
import ru.rsoft.shold.core.repository.PatternRepository;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by Admin on 09.05.2016.
 */
@Component
public class PatternFormationService {
    private final PatternRepository patternRepository;
    private final PatternFormationRepository patternFormationRepository;

    @Autowired
    public PatternFormationService(PatternRepository patternRepository, PatternFormationRepository patternFormationRepository) {
        this.patternRepository = patternRepository;
        this.patternFormationRepository = patternFormationRepository;
    }

    public List<PatternFormationDto> findByPatternId(Integer id){
        //return convert(requireNotNull(patternFormationRepository.findByPatternId(id)));
        return patternFormationRepository.findByPatternId(id).stream().map(this::convert).collect(Collectors.toList());
    }

    public List<PatternFormationDto> findAll() {
        return patternFormationRepository.findAll().stream().map(this::convert).collect(Collectors.toList());
    }

    public PatternFormationDto add(PatternFormationCreateDto patternFormationCreateDto) {
        return convert(patternFormationRepository.save(convert(patternFormationCreateDto)));
    }

    private PatternFormationDto convert(PatternFormation patternFormation) {
        return new PatternFormationDto(
                patternFormation.getId(),
                patternFormation.getPatternId(),
                patternFormation.getFileName(),
                patternFormation.getFile()
        );
    }

    private PatternFormation convert(PatternFormationCreateDto patternFormationCreateDto) {

        return new PatternFormation(
                null,
                patternFormationCreateDto.getPatternId(),
                patternFormationCreateDto.getFileName(),
                patternFormationCreateDto.getFile()

        );
    }

    private <T> T requireNotNull(T object) {
        if (object == null) {
            throw new WebApplicationException(Response.Status.NOT_FOUND);
        }
        return object;
    }

}
