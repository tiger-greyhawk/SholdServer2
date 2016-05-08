package ru.rsoft.shold.backend.handlers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestBody;
import ru.rsoft.shold.backend.service.PatternPhotoService;
import ru.rsoft.shold.core.dto.PatternPhotoCreateDto;
import ru.rsoft.shold.core.dto.PatternPhotoDto;
import ru.rsoft.shold.core.entity.PatternPhoto;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;

/**
 * Created by Admin on 08.05.2016.
 */
@Component
@Path("/pattern/pattern_photo")
public class PatternPhotoHandler {
    private final PatternPhotoService patternPhotoService;

    @Autowired
    public PatternPhotoHandler(PatternPhotoService patternPhotoService) {
        this.patternPhotoService = patternPhotoService;
    }

    @Path("/{id}")
    @GET
    public PatternPhotoDto get(@PathParam("id") int id) {
        return patternPhotoService.findByPatternId(id);
    }

    @Path("/")
    @POST
    public PatternPhotoDto add(@RequestBody PatternPhotoCreateDto patternCreateDto) {
        return patternPhotoService.add(patternCreateDto);
    }
}
