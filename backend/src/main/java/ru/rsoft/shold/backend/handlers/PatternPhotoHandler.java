package ru.rsoft.shold.backend.handlers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestBody;
import ru.rsoft.shold.backend.service.PatternPhotoService;
import ru.rsoft.shold.core.dto.PatternPhotoCreateDto;
import ru.rsoft.shold.core.dto.PatternPhotoDto;


import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import java.util.List;

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
    //public List<PatternPhotoDto> get(@PathParam("id") int id) {
    public PatternPhotoDto get(@PathParam("id") int id) {
        return patternPhotoService.findByPatternId(id);
    }

    @Path("/{id}")
    @POST
    public PatternPhotoDto add(@RequestBody PatternPhotoCreateDto patternPhotoCreateDto, @PathParam("id") int patternId) {
        return patternPhotoService.add(patternPhotoCreateDto, patternId);
    }
}
