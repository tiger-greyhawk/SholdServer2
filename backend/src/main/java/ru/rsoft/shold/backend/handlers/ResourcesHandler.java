package ru.rsoft.shold.backend.handlers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;
import ru.rsoft.shold.backend.service.ResourcesService;
import ru.rsoft.shold.core.dto.requests.ResourcesCreateDto;
import ru.rsoft.shold.core.dto.requests.ResourcesDto;
import ru.rsoft.shold.core.entity.Player;
import ru.rsoft.shold.core.entity.Village;
import ru.rsoft.shold.core.entity.requests.Resources;
import ru.rsoft.shold.core.repository.PlayerRepository;
import ru.rsoft.shold.core.repository.VillageRepository;
import ru.rsoft.shold.core.repository.requests.ResourcesRepository;

import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

//import javax.ws.rs.Path;

/**
 * Created by Admin on 24.04.2016.
 */
@Component
@Transactional(propagation = Propagation.REQUIRED)
@Path("/requests/resources")
public class ResourcesHandler {

    private final ResourcesService resourcesService;

    @Autowired
    public ResourcesHandler(ResourcesService resourcesService) {
        this.resourcesService = resourcesService;
    }

    @Path("/")
    @GET
    public List<ResourcesDto> list()  {
        return resourcesService.getAll();
    }

    @Path("/new/{current_timestamp}")
    @GET
    public List<ResourcesDto> lastList(@PathParam("current_timestamp") long current_timestamp) {
        return resourcesService.lastList(current_timestamp);
    }

    @Path("/{id}")
    @GET
    public ResourcesDto get(@PathParam("id") int id) {
        return resourcesService.getOne(id);
    }

    @Path("/")
    @POST
    public ResourcesDto add(@RequestBody ResourcesCreateDto resourcesCreateDto) {
        return resourcesService.add(resourcesCreateDto);
    }

    @Path("/{id}")
    @PUT
    public ResourcesDto update(@PathParam("id") int id, @RequestBody ResourcesCreateDto resourcesCreateDto){
        return resourcesService.update(id, resourcesCreateDto);
    }
/* TODO
Сделать Entity "посылок по почте" на запросы ресурсов и Entity "статистики". Выдавать пользователю разницу между запрошенным кол-вом и посланным кол-вом.
Когда кол-во сравняется, или станет меньше нуля, удалять запрос и посылки из базы, и добавлять в Entity статистики.
 */
}


