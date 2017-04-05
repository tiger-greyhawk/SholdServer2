package ru.rsoft.shold.backend.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;
import ru.rsoft.shold.core.dto.FriendsDto;
import ru.rsoft.shold.core.dto.requests.ResourcesCreateDto;
import ru.rsoft.shold.core.dto.requests.ResourcesDto;
import ru.rsoft.shold.core.entity.*;
import ru.rsoft.shold.core.entity.requests.Resources;
import ru.rsoft.shold.core.repository.FactionPlayerRepository;
import ru.rsoft.shold.core.repository.FriendsRepository;
import ru.rsoft.shold.core.repository.PlayerRepository;
import ru.rsoft.shold.core.repository.VillageRepository;
import ru.rsoft.shold.core.repository.requests.ResourcesRepository;

import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Created by Admin on 22.06.2016.
 */

@Component
@Transactional(propagation = Propagation.REQUIRED)
public class ResourcesService {

    private final PlayerRepository playerRepository;
    private final VillageRepository villageRepository;
    private final ResourcesRepository resourcesRepository;
    private final FriendsRepository friendsRepository;
    private final FactionPlayerRepository factionPlayerRepository;

    @Autowired
    public ResourcesService(PlayerRepository playerRepository, VillageRepository villageRepository,
                            ResourcesRepository resourcesRepository, FriendsRepository friendsRepository, FactionPlayerRepository factionPlayerRepository) {
        this.playerRepository = playerRepository;
        this.villageRepository = villageRepository;
        this.resourcesRepository = resourcesRepository;
        this.friendsRepository = friendsRepository;
        this.factionPlayerRepository = factionPlayerRepository;
    }

    public List<ResourcesDto> getAll()  {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String username = ((UserDetails)principal).getUsername();
        int playerId = playerRepository.findByNick(username).getId();
        //List<Friend> friends = friendsRepository.findByPlayerId(playerId);
        final List<ResourcesDto> result1 = new ArrayList<>();
        final List<Friend> resultFriend = new ArrayList<>();
/*        for (final Friend friend : friendsRepository.findByPlayerId(playerId)) {
            //final FriendsDto friendsDto = convert(friend);
            resultFriend.add(friend);
            //}


            for (final Resources resources : resourcesRepository.findByPlayerId(friend.getFriendId())) {
                final ResourcesDto resourcesDto1 = convert(resources);
                result1.add(resourcesDto1);
            }

        }
        for (final Resources resources : resourcesRepository.findByPlayerId(playerId)) {
            final ResourcesDto resourcesDto1 = convert(resources);
            result1.add(resourcesDto1);
        }*/

        List<Player> playersInFaction = new ArrayList<>();
        for(final FactionPlayer factionPlayer : factionPlayerRepository.findByPlayerId(playerId)) {
            for (final Player playerInFaction : playerRepository.findByFactionId(factionPlayer.getFactionId())) {
                playersInFaction.add(playerInFaction);
            }
        }
            for(final Player friend : playerRepository.findFriends(playerId)){
                if (!playersInFaction.contains(friend))
                    playersInFaction.add(friend);
            }

        for(final Player friend : playersInFaction)
        for (final Resources resources : resourcesRepository.findByPlayerId(friend.getId())) {
            final ResourcesDto resourcesDto1 = convert(resources);
            result1.add(resourcesDto1);
        }

        ///* Так нельзя. Так будут возвращаться запросы в нескольких экземплярах если в одной фраке и в личных друзьях.
/*        final List<FactionPlayer> resultFactionPlayer = ew ArrayList<>();
        for (final FactionPlayer factionPlayer : factionPlayerRepository.findByPlayerId(playerId))
            for (final FactionPlayer friend : factionPlayerRepository.findByFactionId(factionPlayer.getFactionId())) {
                //final FriendsDto friendsDto = convert(friend);
                resultFactionPlayer.add(friend);
                //}

                //final int temp = friend.getFriendId();
                //final int temp2 = friend.getPlayerId();

                //for (final Resources resources : resourcesRepository.findByTimestampAfter(friend.getFriendId(), timestamp, new Date())) {
                //Set<Resources> listResTemp = resourcesRepository.findByTimestampAfter(temp, timestamp, new Date());
                for (final Resources resources : resourcesRepository.findByPlayerId(friend.getPlayerId())) {
                    //final int temp = friend.getFriendId();
                    //final int temp2 = friend.getPlayerId();
                    final ResourcesDto resourcesDto1 = convert(resources);
                    result1.add(resourcesDto1);
                }


            }
*/        return result1;
        //return resourcesRepository.findAll().stream().map(this::convert).collect(Collectors.toList());
    }

    public List<ResourcesDto> lastList(@PathParam("current_timestamp") long current_timestamp) {
        final Date timestamp = new Date(current_timestamp);
        final Date current = new Date();
        //timestamp.getTime();
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String username = ((UserDetails)principal).getUsername();
        Player player = playerRepository.findByNick(username);
        player.setLastAccess(new Date());
        //PlayerCreateDto playerCreateDto = player;
        playerRepository.save(player);
        //Player player = playerRepository.findByNick(username);
        //player.setLastRequest(current);                                   ///   Временное решение для вида онлайн / не онлайн
        //playerRepository.save(player);
        int playerId = playerRepository.findByNick(username).getId();
        //timestamp.setTime();
        //@Query(nativeQuery = true, value = "select startDate from TaskMetrics where startDate between :startDate and :endDate")
        //List<ResourcesDto> listToReturn = resourcesRepository.findByTimestampAfter(playerId, timestamp, new Date()).stream().map(this::convert).collect(Collectors.toList());
        final List<ResourcesDto> listToReturn = new ArrayList<>();
        final List<Friend> result = new ArrayList<>();
        for (final Friend friend : friendsRepository.findByPlayerId(playerId)) {
            //final FriendsDto friendsDto = convert(friend);
            result.add(friend);
            //}

            //final int temp = friend.getFriendId();
            //final int temp2 = friend.getPlayerId();

            //for (final Resources resources : resourcesRepository.findByTimestampAfter(friend.getFriendId(), timestamp, new Date())) {
            //Set<Resources> listResTemp = resourcesRepository.findByTimestampAfter(temp, timestamp, new Date());
            for (final Resources resources : resourcesRepository.findByTimestampAfter(friend.getFriendId(), timestamp, new Date())) {
                //final int temp = friend.getFriendId();
                //final int temp2 = friend.getPlayerId();
                final ResourcesDto resourcesDto1 = convert(resources);
                listToReturn.add(resourcesDto1);
            }


        }

        ///* Так нельзя. Так будут возвращаться запросы в нескольких экземплярах если в одной фраке и в личных друзьях.
/*        final List<FactionPlayer> resultFactionPlayer = new ArrayList<>();
        for (final FactionPlayer factionPlayer : factionPlayerRepository.findByPlayerId(playerId))
        for (final FactionPlayer friend : factionPlayerRepository.findByFactionId(factionPlayer.getFactionId())) {
            //final FriendsDto friendsDto = convert(friend);
            resultFactionPlayer.add(friend);
            //}

            //final int temp = friend.getFriendId();
            //final int temp2 = friend.getPlayerId();

            //for (final Resources resources : resourcesRepository.findByTimestampAfter(friend.getFriendId(), timestamp, new Date())) {
            //Set<Resources> listResTemp = resourcesRepository.findByTimestampAfter(temp, timestamp, new Date());
            for (final Resources resources : resourcesRepository.findByTimestampAfter(friend.getPlayerId(), timestamp, new Date())) {
                //final int temp = friend.getFriendId();
                //final int temp2 = friend.getPlayerId();
                final ResourcesDto resourcesDto1 = convert(resources);
                listToReturn.add(resourcesDto1);
            }


        }
*/
        return listToReturn;
    }

    public ResourcesDto getOne(@PathParam("id") int id) {
        return convert(requireNotNull(resourcesRepository.findOne(id)));
    }

    public ResourcesDto add(@RequestBody ResourcesCreateDto resourcesCreateDto) {
        Resources temp = convert(resourcesCreateDto);
        return convert(resourcesRepository.save(temp));
    }

    public ResourcesDto update(@PathParam("id") int id, @RequestBody ResourcesCreateDto resourcesCreateDto){
        final Resources resourcesToUpdate = resourcesRepository.findOne(id);
        if (resourcesToUpdate == null) {
            // надо возвращать 404, если нет такого ресурса
            throw new WebApplicationException(Response.Status.NOT_FOUND);
        }
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String username = ((UserDetails)principal).getUsername();
        int playerId = playerRepository.findByNick(username).getId();

        final Resources update = convert(resourcesCreateDto);

        resourcesToUpdate.setAmount(resourcesToUpdate.getAmount() - update.getAmount());
        return convert(resourcesRepository.save(resourcesToUpdate));
    }



    private <T> T requireNotNull(T object) {
        if (object == null) {
            throw new WebApplicationException(Response.Status.NOT_FOUND);
        }
        return object;
    }

    private ResourcesDto convert(Resources resources) {
        //final Village village = requireNotNull(villageRepository.findOne(resources.getVillageId()));
        final Village village = requireNotNull(villageRepository.findOne(resources.getVillageId()));
        return new ResourcesDto(
                resources.getId(),
                resources.getWorldId(),
                resources.getType(),
                resources.getName(),
//                resources.getVillageId(),
                village.getIdInWorld(),
                resources.getAmount(),
                resources.getOnWay(),
                resources.getMaxQuantity(),
                resources.getPlayerId(),
//                village.getBody() == null ? null : village.getBody().getId(),
                resources.getTimestamp(),
                new Date()
        );
    }


    private Resources convert(ResourcesCreateDto resourcesCreateDto) {
        final Player player = requireNotNull(playerRepository.findOne(resourcesCreateDto.getPlayerId()));
        //final Village village = requireNotNull(villageRepository.findByIdInWorld(resourcesCreateDto.getVillageId()));

        return new Resources(
                0,
                resourcesCreateDto.getWorldId(),
                resourcesCreateDto.getType(),
                resourcesCreateDto.getName(),
                resourcesCreateDto.getVillageId(),
                resourcesCreateDto.getAmount(),
                resourcesCreateDto.getOnWay(),
                resourcesCreateDto.getMax_quantum(),
                player.getId(),
                new Date()
                //resourcesCreateDto.getTimestamp()

        );
    }
}
