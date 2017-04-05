package ru.rsoft.shold.backend.handlers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestBody;
import ru.rsoft.shold.backend.service.PlanTargetService;
import ru.rsoft.shold.core.dto.timing.PlanTargetCreateDto;
import ru.rsoft.shold.core.dto.timing.PlanTargetDto;

import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;

/**
 * Created by Admin on 26.08.2016.
 */
@Component
@Path("/plantarget")
public class PlanTargetHandler {

    private final PlanTargetService planTargetService;


    @Autowired
    public PlanTargetHandler(PlanTargetService planTargetService) {
        this.planTargetService = planTargetService;
    }

    @Path("/")
    @POST
    public PlanTargetDto add(@RequestBody PlanTargetCreateDto planTargetCreateDto) {
        return planTargetService.add(planTargetCreateDto);
    }
}
