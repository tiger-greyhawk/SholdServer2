package ru.rsoft.configurator.backend.handlers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestBody;
import ru.rsoft.configurator.core.dto.VillageCreateDto;
import ru.rsoft.configurator.core.dto.requests.ResourcesCreateDto;
import ru.rsoft.configurator.core.dto.VillageDto;
import ru.rsoft.configurator.core.dto.requests.ResourcesDto;
import ru.rsoft.configurator.core.entity.Player;
import ru.rsoft.configurator.core.entity.Village;
import ru.rsoft.configurator.core.entity.requests.Resources;
import ru.rsoft.configurator.core.repository.PlayerRepository;
import ru.rsoft.configurator.core.repository.VillageRepository;
import ru.rsoft.configurator.core.repository.requests.ResourcesRepository;

import javax.ws.rs.*;
import javax.ws.rs.core.Response;
//import javax.ws.rs.Path;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by Admin on 24.04.2016.
 */
@Component
@Path("/requests/resources")
public class ResourcesHandler {

    private final PlayerRepository playerRepository;
    private final VillageRepository villageRepository;
    private final ResourcesRepository resourcesRepository;

    @Autowired
    public ResourcesHandler(PlayerRepository playerRepository, VillageRepository villageRepository, ResourcesRepository resourcesRepository) {
        this.playerRepository = playerRepository;
        this.villageRepository = villageRepository;
        this.resourcesRepository = resourcesRepository;
    }

    @Path("/")
    @GET
    public List<ResourcesDto> list()  {
        return resourcesRepository.findAll().stream().map(this::convert)
                .collect(Collectors.toList());
    }

    @Path("/new/{current_timestamp}")
    @GET
    public List<ResourcesDto> lastList(@PathParam("current_timestamp") long current_timestamp) {
        final Date timestamp = new Date(current_timestamp);
        timestamp.getTime();
        //timestamp.setTime();
        //@Query(nativeQuery = true, value = "select startDate from TaskMetrics where startDate between :startDate and :endDate")
        return resourcesRepository.findByTimestampAfter(timestamp, new Date()).stream().map(this::convert)
                .collect(Collectors.toList());
    }

    @Path("/{id}")
    @GET
    public ResourcesDto get(@PathParam("id") int id) {
        return convert(requireNotNull(resourcesRepository.findOne(id)));
    }

    @Path("/")
    @POST
    public ResourcesDto add(@RequestBody ResourcesCreateDto resourcesCreateDto) {
        return convert(resourcesRepository.save(convert(resourcesCreateDto)));
    }

    private <T> T requireNotNull(T object) {
        if (object == null) {
            throw new WebApplicationException(Response.Status.NOT_FOUND);
        }
        return object;
    }

    private ResourcesDto convert(Resources resources) {
        return new ResourcesDto(
                resources.getId(),
                resources.getType(),
                resources.getName(),
                resources.getVillage() == null ? null : resources.getVillage().getId(),
                resources.getAmount(),
                resources.getMax_quantum(),
                resources.getPlayer() == null ? null : resources.getPlayer().getId(),
//                village.getBody() == null ? null : village.getBody().getId(),
                resources.getTimestamp(),
                new Date()
        );
    }


    private Resources convert(ResourcesCreateDto resourcesCreateDto) {
        final Player player = requireNotNull(playerRepository.findOne(resourcesCreateDto.getPlayerId()));
        final Village village = requireNotNull(villageRepository.findOne(resourcesCreateDto.getVillageId()));

        return new Resources(
                resourcesCreateDto.getType(),
                resourcesCreateDto.getName(),
                village,
                resourcesCreateDto.getAmount(),
                resourcesCreateDto.getMax_quantum(),
                player,
                resourcesCreateDto.getTimestamp()

        );
    }
}

