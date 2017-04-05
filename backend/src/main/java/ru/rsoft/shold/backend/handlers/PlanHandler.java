package ru.rsoft.shold.backend.handlers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestBody;
import ru.rsoft.shold.backend.service.PlanAttackService;
import ru.rsoft.shold.backend.service.PlanService;
import ru.rsoft.shold.backend.service.PlanTargetService;
import ru.rsoft.shold.core.dto.timing.*;
import ru.rsoft.shold.core.entity.timing.PlanTarget;

import javax.ws.rs.*;
import java.util.List;

/**
 * Created by Admin on 01.08.2016.
 */
@Component
@Path("/plan")
public class PlanHandler {

    private final PlanService planService;
    private final PlanTargetService planTargetService;
    private final PlanAttackService planAttackService;

    @Autowired
    public PlanHandler(PlanService planService, PlanTargetService planTargetService, PlanAttackService planAttackService) {
        this.planService = planService;
        this.planTargetService = planTargetService;
        this.planAttackService = planAttackService;

    }

    @Path("/")
    @GET
    public List<PlanDto> getAll() {
        return planService.getAll();
    }

    @Path("/target")
    @GET
    public List<PlanTargetDto> getAllTarget() {
        return planTargetService.getAllTarget();
    }

    @Path("/attacks/{id}")
    @GET
    public List<PlanAttackDto> getByPlanId(@PathParam("id") int id) {
        return planAttackService.findByPlanId(id);
    }

    @Path("/{secret}")
    @GET
    public List<PlanAttackDto> list(@PathParam("secret") String secret) {
        return planService.findBySecret(secret);
    }

    @Path("/start/{id}")
    @GET
    public PlanDto check(@PathParam("id") int id) {
        return planService.check(id);
    }

    @Path("/start/{secret}")
    @POST
    public PlanDto start(@PathParam("secret") String secret) {
        return planService.start(secret);
    }

    @Path("/")
    @POST
    public PlanDto add(@RequestBody PlanCreateDto planCreateDto) {
        return planService.add(planCreateDto);
    }

    @Path("/{id}")
    @PUT
    public PlanDto update(@PathParam("id") int id) {
        return planService.update(id);
    }


    @Path("/{id}")
    @DELETE
    public void delete(@PathParam("id") int id) {
        planService.delete(id);
    }
}
