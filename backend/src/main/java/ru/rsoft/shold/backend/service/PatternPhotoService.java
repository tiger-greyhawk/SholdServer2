package ru.rsoft.shold.backend.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import ru.rsoft.shold.core.dto.PatternCreateDto;
import ru.rsoft.shold.core.dto.PatternDto;
import ru.rsoft.shold.core.dto.PatternPhotoCreateDto;
import ru.rsoft.shold.core.dto.PatternPhotoDto;
import ru.rsoft.shold.core.entity.Pattern;
import ru.rsoft.shold.core.entity.PatternPhoto;
import ru.rsoft.shold.core.entity.Player;
import ru.rsoft.shold.core.repository.PatternPhotoRepository;
import ru.rsoft.shold.core.repository.PatternRepository;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by Admin on 08.05.2016.
 */
@Component
public class PatternPhotoService {
    private final PatternRepository patternRepository;
    private final PatternPhotoRepository patternPhotoRepository;

    @Autowired
    public PatternPhotoService(PatternRepository patternRepository, PatternPhotoRepository patternPhotoRepository) {
        this.patternRepository = patternRepository;
        this.patternPhotoRepository = patternPhotoRepository;
    }

/*
    public PatternPhotoDto findByPatternId(){
        return convert(patternPhotoRepository.findByPatternId());
    }
*/
    public PatternPhotoDto findByPatternId(Integer id){
        return convert(requireNotNull(patternPhotoRepository.findByPatternId(id)));
    }

    public List<PatternPhotoDto> findAll() {
        return patternPhotoRepository.findAll().stream().map(this::convert).collect(Collectors.toList());
    }

    public PatternPhotoDto add(PatternPhotoCreateDto patternPhotoCreateDto) {
        return convert(patternPhotoRepository.save(convert(patternPhotoCreateDto)));
    }

    private PatternPhotoDto convert(PatternPhoto patternPhoto) {
        return new PatternPhotoDto(
                patternPhoto.getId(),
                patternPhoto.getPatternId(),
                patternPhoto.getPhotoName(),
                patternPhoto.getPhoto()
        );
    }

    private PatternPhoto convert(PatternPhotoCreateDto patternPhotoCreateDto) {

        return new PatternPhoto(
                null,
                patternPhotoCreateDto.getPatternId(),
                patternPhotoCreateDto.getPhotoName(),
                patternPhotoCreateDto.getPhoto()

        );
    }

    private <T> T requireNotNull(T object) {
        if (object == null) {
            throw new WebApplicationException(Response.Status.NOT_FOUND);
        }
        return object;
    }

}
