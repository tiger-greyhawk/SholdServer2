package ru.rsoft.shold.backend.handlers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestBody;
import ru.rsoft.shold.backend.service.PatternService;
import ru.rsoft.shold.core.dto.PatternCreateDto;
import ru.rsoft.shold.core.dto.PatternDto;
import ru.rsoft.shold.core.entity.Pattern;

import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import java.util.List;

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
        return patternService.findByPlayerId();
    }

/*
    @Path("/myList")
    @GET
    public List<PatternDto> listWithoutFiles() {
        return patternService.findPlayerIdWithoutFiles();
    }
*/
    @Path("/friends")
    @GET
    public List<PatternDto> list1() {
        return patternService.findByFriend();
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

    @Path("/{id}")
    @PUT
    public PatternDto update(@PathParam("id") int id, @RequestBody PatternCreateDto patternCreateDto) {
        return patternService.update(id, patternCreateDto);
    }

    @Path("/{id}")
    @DELETE
    public void delete(@PathParam("id") int id) {
        patternService.delete(id);
    }
}