package ru.rsoft.shold.backend.handlers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestBody;
import ru.rsoft.shold.backend.service.PatternFormationService;
import ru.rsoft.shold.core.dto.PatternFormationCreateDto;
import ru.rsoft.shold.core.dto.PatternFormationDto;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import java.util.List;

/**
 * Created by Admin on 09.05.2016.
 */
@Component
@Path("/pattern/pattern_formation")
public class PatternFormationHandler {

    private final PatternFormationService patternFormationService;

    @Autowired
    public PatternFormationHandler(PatternFormationService patternFormationService) {
        this.patternFormationService = patternFormationService;
    }

    @Path("/{id}")
    @GET
    public List<PatternFormationDto> get(@PathParam("id") int id) {
        return patternFormationService.findByPatternId(id);
    }

    @Path("/")
    @POST
    public PatternFormationDto add(@RequestBody PatternFormationCreateDto patternFormationCreateDto) {
        return patternFormationService.add(patternFormationCreateDto);
    }
}
