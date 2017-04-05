package ru.rsoft.shold.backend.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import ru.rsoft.shold.core.dto.timing.PlanAttackCreateDto;
import ru.rsoft.shold.core.dto.timing.PlanAttackDto;
import ru.rsoft.shold.core.entity.Friend;
import ru.rsoft.shold.core.entity.Player;
import ru.rsoft.shold.core.entity.timing.PlanAttack;
import ru.rsoft.shold.core.repository.FriendsRepository;
import ru.rsoft.shold.core.repository.PlayerRepository;
import ru.rsoft.shold.core.repository.timing.PlanAttackRepository;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by Admin on 15.06.2016.
 */
@Component
@Transactional(propagation = Propagation.REQUIRED)
public class PlanAttackService {

    @Autowired
    private final PlayerRepository playerRepository;
    @Autowired
    private final PlanAttackRepository planAttackRepository;
    @Autowired
    private final FriendsRepository friendsRepository;

    @Autowired
    public PlanAttackService(PlayerRepository playerRepository, PlanAttackRepository planAttackRepository, FriendsRepository friendsRepository) {
        this.playerRepository = playerRepository;
        this.planAttackRepository = planAttackRepository;
        this.friendsRepository = friendsRepository;
    }

    public List<PlanAttackDto> findByPlayerId() {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String username = ((UserDetails)principal).getUsername();
        int playerId = playerRepository.findByNick(username).getId();
        return planAttackRepository.findByPlayerId(playerId).stream().map(this::convert)
                .collect(Collectors.toList());
    }

    public List<PlanAttackDto> findByFriend() {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        List<PlanAttackDto> temp = new ArrayList<PlanAttackDto>();

        String username = ((UserDetails)principal).getUsername();
        int playerId = playerRepository.findByNick(username).getId();
        List<Friend> friends = friendsRepository.findByPlayerId(playerId);
//        return friends.forEach(friends1 -> patternRepository.findByPlayerId(friends1.getFriendId()).stream().map(this::convert)
//                .collect(Collectors.toList()));
        friends.forEach(friends1 ->
                temp.addAll(planAttackRepository.findByPlayerId(friends1.getFriendId()).stream().map(this::convert)
                        .collect(Collectors.toList())));
        return temp;
    }

    public PlanAttackDto add(PlanAttackCreateDto planAttackCreateDto) {
        return convert(planAttackRepository.save(convert(planAttackCreateDto)));
    }



    public PlanAttackDto findById(int id) {
        return convert(requireNotNull(planAttackRepository.findOne(id)));
    }

    public List<PlanAttackDto> findBySecret(String secret) {
        //List<PlanAttackDto> tempDto = new ArrayList<>();
        //List<PlanAttack> temp = new ArrayList<>();
        //temp = planAttackRepository.findBySecret(secret);
        //temp.forEach(temp1 -> tempDto.addAll);
        //tempDto.addAll(planAttackRepository.findBySecret(secret).stream().map(this::convert).collect(Collectors.toList()));
        //return tempDto;
        return planAttackRepository.findBySecret(secret).stream().map(this::convert).collect(Collectors.toList());

    }

    public List<PlanAttackDto> findByPlanId(int id) {
        return planAttackRepository.findByPlanId(id).stream().map(this::convert).collect(Collectors.toList());
    }

    public void delete(int id) {

        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String username = ((UserDetails) principal).getUsername();
        int playerId = playerRepository.findByNick(username).getId();
        if (playerId == planAttackRepository.findOne(id).getPlayerId())
        //village.setPlayerId(playerId);
        {
            planAttackRepository.delete(id);
        }
    }

    public PlanAttackDto convert(PlanAttack planAttack) {
        return new PlanAttackDto(
                planAttack.getId(),
                planAttack.getPlanId(),
                planAttack.getName(),
                planAttack.getSecret(),
                planAttack.getPlayerId(),
                planAttack.getVillageId(),
                planAttack.getVassalId(),
                planAttack.getType(),
                planAttack.getTimeTo(),
                planAttack.getCard(),
                planAttack.getReadyIn(),
                planAttack.getOrderIn(),
                planAttack.getTimestamp(),
                new Date()
                //planAttack.getCurrentTimestamp()
//                village.getIdInWorld()
        );
    }
    /*
        private PatternDto convertMin(Pattern pattern) {
            return new PatternDto(
                    pattern.getId(),
                    pattern.getPlayerId(),
                    pattern.getName(),
                    pattern.getTypeCastle(),
                    pattern.getAccessFrom()
            );
        }
    */
    private PlanAttack convert(PlanAttackCreateDto planAttackCreateDto) {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        List<PlanAttackDto> temp = new ArrayList<PlanAttackDto>();

        String username = ((UserDetails)principal).getUsername();
        int playerId = playerRepository.findByNick(username).getId();
        final Player player = requireNotNull(playerRepository.findOne(playerId));
        //final Player player = requireNotNull(playerRepository.findOne(patternCreateDto.getPlayerId()));
        //final Player player = requireNotNull(SecurityContextHolder.getContext().getAuthentication().getPrincipal());

        return new PlanAttack(
                0,
                planAttackCreateDto.getPlanId(),
                planAttackCreateDto.getName(),
                planAttackCreateDto.getSecret(),
                planAttackCreateDto.getPlayerId(),
                planAttackCreateDto.getVillageId(),
                planAttackCreateDto.getVassalId(),
                planAttackCreateDto.getType(),
                planAttackCreateDto.getTimeTo(),
                planAttackCreateDto.getCard(),
                planAttackCreateDto.getReadyIn(),
                planAttackCreateDto.getOrderIn(),
                new Date()
                //planAttackCreateDto.getTimestamp()

        );
    }

    private <T> T requireNotNull(T object) {
        if (object == null) {
            throw new WebApplicationException(Response.Status.NOT_FOUND);
        }
        return object;
    }

}
