package ru.rsoft.configurator.backend.handlers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestBody;
import ru.rsoft.configurator.backend.service.PatternService;
import ru.rsoft.configurator.core.dto.PatternDto;
import ru.rsoft.configurator.core.dto.PatternCreateDto;
import ru.rsoft.configurator.core.dto.VillageCreateDto;
import ru.rsoft.configurator.core.dto.VillageDto;
import ru.rsoft.configurator.core.entity.Player;
import ru.rsoft.configurator.core.entity.Pattern;
import ru.rsoft.configurator.core.repository.PatternRepository;
import ru.rsoft.configurator.core.repository.PlayerRepository;


import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by Admin on 18.04.2016.
 */
@Component
@Path("/pattern")
public class PatternHandler {

    private final PatternService patternService;


    @Autowired
    public PatternHandler(PatternService patternService) {
        this.patternService = patternService;

    }

    @Path("/")
    @GET
    public List<PatternDto> list() {
        return patternService.findAll();
    }

    @Path("/{id}")
    @GET
    public PatternDto get(@PathParam("id") int id) {
        return patternService.findById(id);
    }

    @Path("/")
    @POST
    public PatternDto add(@RequestBody PatternCreateDto patternCreateDto) {
        return patternService.add(patternCreateDto);

    }
}