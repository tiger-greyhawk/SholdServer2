package ru.rsoft.shold.backend.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;
import ru.rsoft.shold.core.dto.requests.SendResourcesCreateDto;
import ru.rsoft.shold.core.dto.requests.SendResourcesDto;
import ru.rsoft.shold.core.entity.Player;
import ru.rsoft.shold.core.entity.requests.Resources;
import ru.rsoft.shold.core.entity.requests.SendResources;
import ru.rsoft.shold.core.repository.PlayerRepository;
import ru.rsoft.shold.core.repository.requests.ResourcesRepository;
import ru.rsoft.shold.core.repository.requests.SendResourcesRepository;

import javax.ws.rs.PathParam;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;
import java.security.Timestamp;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by Admin on 24.06.2016.
 */
@Component
@Transactional(propagation = Propagation.REQUIRED)
public class SendResourcesService {

    private final PlayerRepository playerRepository;
    private final SendResourcesRepository sendResourcesRepository;
    private final ResourcesRepository resourcesRepository;

    @Autowired
    public SendResourcesService(PlayerRepository playerRepository, SendResourcesRepository sendResourcesRepository, ResourcesRepository resourcesRepository) {
        this.playerRepository = playerRepository;
        this.sendResourcesRepository = sendResourcesRepository;
        this.resourcesRepository = resourcesRepository;
    }

    public List<SendResourcesDto> getAll()  {
        return sendResourcesRepository.findAll().stream().map(this::convert)
                .collect(Collectors.toList());
    }

    public List<SendResourcesDto> lastList(@PathParam("current_timestamp") long current_timestamp) {
        final Date timestamp = new Date(current_timestamp);
        timestamp.getTime();
        //timestamp.setTime();
        //@Query(nativeQuery = true, value = "select startDate from TaskMetrics where startDate between :startDate and :endDate")
        return sendResourcesRepository.findByTimestampAfter(timestamp, new Date()).stream().map(this::convert)
                .collect(Collectors.toList());
    }

    public List<SendResourcesDto> getList(@PathParam("id") int id) {

        //timestamp.setTime();
        //@Query(nativeQuery = true, value = "select startDate from TaskMetrics where startDate between :startDate and :endDate")
        return sendResourcesRepository.findByResourcesId(id).stream().map(this::convert)
                .collect(Collectors.toList());
    }

    public SendResourcesDto getOne(@PathParam("id") int id) {
        return convert(requireNotNull(sendResourcesRepository.findOne(id)));
    }

    public SendResourcesDto add(@RequestBody SendResourcesCreateDto sendResourcesCreateDto) {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String username = ((UserDetails)principal).getUsername();
        int playerId = playerRepository.findByNick(username).getId();
        //SendResourcesCreateDto toConvert
        sendResourcesCreateDto = new SendResourcesCreateDto(
                sendResourcesCreateDto.getResourceId(),
                sendResourcesCreateDto.getAmount(),
                playerId,
                sendResourcesCreateDto.getTimestamp());
        SendResources temp = convert(sendResourcesCreateDto);
        //temp.setPlayerId(playerId);
        Resources resToUpdate = resourcesRepository.findOne(temp.getResourcesId());
        if (resToUpdate.getOnWay()+temp.getAmount() >= resToUpdate.getAmount())
            resourcesRepository.delete(resToUpdate);
            else
        {
            resToUpdate.setOnWay(resToUpdate.getOnWay() + temp.getAmount());
            resourcesRepository.save(resToUpdate);
        }
        return convert(sendResourcesRepository.save(temp));
    }


    private <T> T requireNotNull(T object) {
        if (object == null) {
            throw new WebApplicationException(Response.Status.NOT_FOUND);
        }
        return object;
    }

    private SendResourcesDto convert(SendResources sendResources) {
        //final Village village = requireNotNull(villageRepository.findOne(resources.getVillageId()));
        return new SendResourcesDto(
                sendResources.getId(),
                sendResources.getResourcesId(),
                sendResources.getAmount(),
                sendResources.getPlayerId(),
                sendResources.getTimestamp(),
                new Date()
        );
    }


    private SendResources convert(SendResourcesCreateDto sendResourcesCreateDto) {
        final Player player = requireNotNull(playerRepository.findOne(sendResourcesCreateDto.getPlayerId()));
//        final Village village = requireNotNull(villageRepository.findByIdInWorld(resourcesCreateDto.getVillageId()));

        return new SendResources(
                0,
                sendResourcesCreateDto.getResourceId(),
                sendResourcesCreateDto.getAmount(),
                player.getId(),
                new Date()
                //sendResourcesCreateDto.getTimestamp()

        );
    }
}
