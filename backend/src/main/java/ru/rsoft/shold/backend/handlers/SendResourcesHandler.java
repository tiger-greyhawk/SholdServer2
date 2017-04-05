package ru.rsoft.shold.backend.handlers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;
import ru.rsoft.shold.backend.service.SendResourcesService;
import ru.rsoft.shold.core.dto.requests.SendResourcesCreateDto;
import ru.rsoft.shold.core.dto.requests.SendResourcesDto;

import javax.ws.rs.*;
import java.util.List;

/**
 * Created by Admin on 24.06.2016.
 */
@Component
@Transactional(propagation = Propagation.REQUIRED)
@Path("/send/resources")
public class SendResourcesHandler {
    private final SendResourcesService sendResourcesService;

    @Autowired
    public SendResourcesHandler(SendResourcesService sendResourcesService) {
        this.sendResourcesService = sendResourcesService;
    }

    @Path("/")
    @GET
    public List<SendResourcesDto> list()  {
        return sendResourcesService.getAll();
    }

    @Path("/new/{current_timestamp}")
    @GET
    public List<SendResourcesDto> lastList(@PathParam("current_timestamp") long current_timestamp) {
        return sendResourcesService.lastList(current_timestamp);
    }

    @Path("/{id}")
    @GET
    public SendResourcesDto get(@PathParam("id") int id) {
        return sendResourcesService.getOne(id);
    }

    @Path("/list/{id}")
    @GET
    public List<SendResourcesDto> getList(@PathParam("id") int id) {
        return sendResourcesService.getList(id);
    }

    @Path("/")
    @POST
    public SendResourcesDto add(@RequestBody SendResourcesCreateDto resourcesCreateDto) {
        return sendResourcesService.add(resourcesCreateDto);
    }
/*
    @Path("/{id}")
    @PUT
    public SendResourcesDto update(@PathParam("id") int id, @RequestBody SendResourcesCreateDto resourcesCreateDto){
        return sendResourcesService.update(id, resourcesCreateDto);
    }
    */
}
