package ru.rsoft.shold.backend.handlers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestBody;
import ru.rsoft.shold.backend.service.PlanAttackService;
import ru.rsoft.shold.core.dto.timing.PlanAttackCreateDto;
import ru.rsoft.shold.core.dto.timing.PlanAttackDto;


import javax.ws.rs.*;
import java.util.List;

/**
 * Created by Admin on 15.06.2016.
 */
@Component
@Path("/planattack")
public class PlanAttackHandler {

    private final PlanAttackService planAttackService;


    @Autowired
    public PlanAttackHandler(PlanAttackService planAttackService) {
        this.planAttackService = planAttackService;

    }

    @Path("/")
    @GET
    public List<PlanAttackDto> list() {
        return planAttackService.findByPlayerId();
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
    public List<PlanAttackDto> list1() {
        return planAttackService.findByFriend();
    }

    @Path("/{id}")
    @GET
    public PlanAttackDto get(@PathParam("id") int id) {
        return planAttackService.findById(id);
    }

    @Path("/secret/{secret}")
    @GET
    public List<PlanAttackDto> getSecret(@PathParam("secret") String secret) {
        return planAttackService.findBySecret(secret);
    }



    @Path("/")
    @POST
    public PlanAttackDto add(@RequestBody PlanAttackCreateDto planAttackCreateDto) {
        return planAttackService.add(planAttackCreateDto);
    }

    @Path("/{id}")
    @DELETE
    public void delete(@PathParam("id") int id) {
        planAttackService.delete(id);
    }
}
