package ru.rsoft.shold.backend.handlers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;
import ru.rsoft.shold.core.dto.VillageCreateDto;
import ru.rsoft.shold.core.dto.VillageDto;
import ru.rsoft.shold.core.entity.Player;
import ru.rsoft.shold.core.entity.Village;
import ru.rsoft.shold.core.repository.PlayerRepository;
import ru.rsoft.shold.core.repository.VillageRepository;

import javax.persistence.PersistenceException;
import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Created by Admin on 22.11.2015.
 */

@Component
@Transactional(propagation = Propagation.REQUIRED)
@Path("/village")
public class VillageHandler {

    private final PlayerRepository playerRepository;
    private final VillageRepository villageRepository;


    @Autowired
    public VillageHandler(PlayerRepository playerRepository, VillageRepository villageRepository) {
        this.playerRepository = playerRepository;
        this.villageRepository = villageRepository;
    }

    @Path("/")
    @GET
    public List<VillageDto> list()  {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String username = ((UserDetails)principal).getUsername();
        int playerId = playerRepository.findByNick(username).getId();

        final Set<Village> villages = villageRepository.findByPlayerId(playerId);
        final List<VillageDto> result = new ArrayList<>(villages.size());
        for (final Village village : villages) {
            final VillageDto villageDto = convert(village);
            result.add(villageDto);
        }
        return result;

//        return villageRepository.findAll().stream().map(this::convert).collect(Collectors.toList());
    }

    @Path("/{id}")
    @GET
    public VillageDto get(@PathParam("id") int id) {
        return convert(requireNotNull(villageRepository.findOne(id)));
    }

/*
    @Path("/{user}")
    @GET
    public List<VillageDto> get(@PathParam("user") Integer user) {
//        int num;
//        final Set<User> i = userRepository.findByLogin(user);
        //num = Integer.parseInt(user);

        return villageRepository.findByUserId(user).stream().map(this::convert)
                .collect(Collectors.toList());
    }
*/

    @Path("/")
    @POST
    public VillageDto add(@RequestBody VillageCreateDto villageCreateDto) {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String username = ((UserDetails)principal).getUsername();
        int playerId = playerRepository.findByNick(username).getId();
        final Village village = convert(new VillageCreateDto(playerId, villageCreateDto.getName(), villageCreateDto.getIdInWorld()));
        //village.setPlayerId(playerId);
        return convert(villageRepository.save(village));
        //return convert(villageRepository.save(convert(villageCreateDto)));
    }

    @Path("/player/{id}")
    @GET
    public List<VillageDto> getByPlayerId(@PathParam("id") int playerID) {
        final Set<Village> villages = villageRepository.findByPlayerId(playerID);
        final List<VillageDto> result = new ArrayList<>(villages.size());
        for (final Village village : villages) {
            final VillageDto villageDto = convert(village);
            result.add(villageDto);
        }
        return result;

/*
        final List<VillageDto> result = new ArrayList<>();
        for (final Village village : villageRepository.findByPlayerId(playerID)) {
            result.add(convert(village));
        }
        return result;
*/
/*
        return villageRepository.findByPlayerId(playerID).parallelStream()
            .map(this::convert)
            .collect(Collectors.toList());
*/
    }

    @Path("/{id}")
    @DELETE
    public void delete(@PathParam("id") int id) {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String username = ((UserDetails)principal).getUsername();

        int playerId = playerRepository.findByNick(username).getId();
        if (playerId == villageRepository.findOne(id).getPlayerId())
            try {
                villageRepository.delete(id);
            }
            catch (org.hibernate.exception.ConstraintViolationException e){
                throw new WebApplicationException("require", Response.Status.CONFLICT );
            }
            catch (EmptyResultDataAccessException e)
                    //()
            {
                // надо возвращать 404, если нет такого ресурса
                throw new WebApplicationException(Response.Status.NOT_FOUND);
            }

        else new WebApplicationException(Response.Status.FORBIDDEN);
    }

    private <T> T requireNotNull(T object) {
        if (object == null) {
            throw new WebApplicationException(Response.Status.NOT_FOUND);
        }
        return object;
    }

    private VillageDto convert(Village village) {
        return new VillageDto(
                village.getId(),
//                village.getUser().getId(),
                village.getPlayerId(),
//                village.getBody() == null ? null : village.getBody().getId(),
                village.getName(),
                village.getIdInWorld()
        );
    }
    private Village convert(VillageCreateDto villageCreateDto) {
        final Player player = requireNotNull(playerRepository.findOne(villageCreateDto.getPlayerId()));

        return new Village(
                player.getId(),
                villageCreateDto.getName(),
                villageCreateDto.getIdInWorld()
        );
    }
}