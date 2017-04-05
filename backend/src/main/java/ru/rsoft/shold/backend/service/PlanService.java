package ru.rsoft.shold.backend.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import ru.rsoft.shold.core.dto.timing.PlanAttackCreateDto;
import ru.rsoft.shold.core.dto.timing.PlanAttackDto;
import ru.rsoft.shold.core.dto.timing.PlanCreateDto;
import ru.rsoft.shold.core.dto.timing.PlanDto;
import ru.rsoft.shold.core.entity.Player;
import ru.rsoft.shold.core.entity.timing.Plan;
import ru.rsoft.shold.core.entity.timing.PlanAttack;
import ru.rsoft.shold.core.repository.FriendsRepository;
import ru.rsoft.shold.core.repository.PlayerRepository;
import ru.rsoft.shold.core.repository.timing.PlanAttackRepository;
import ru.rsoft.shold.core.repository.timing.PlanRepository;
import ru.rsoft.shold.core.repository.timing.PlanTargetRepository;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by Admin on 01.08.2016.
 */
@Component
@Transactional(propagation = Propagation.REQUIRED)
public class PlanService {

    @Autowired
    private final PlayerRepository playerRepository;
    @Autowired
    private final PlanAttackRepository planAttackRepository;
    @Autowired
    private final PlanRepository planRepository;
    @Autowired
    private final PlanTargetRepository planTargetRepository;
    @Autowired
    private final FriendsRepository friendsRepository;

    //@Autowired
    //private final PlanAttackService planAttackService;

    @Autowired
    public PlanService(PlayerRepository playerRepository, PlanAttackRepository planAttackRepository,
                       PlanRepository planRepository, PlanTargetRepository planTargetRepository, FriendsRepository friendsRepository) {
            //, PlanAttackService planAttackService) {
        this.playerRepository = playerRepository;
        this.planAttackRepository = planAttackRepository;
        this.planRepository = planRepository;
        this.planTargetRepository = planTargetRepository;
        this.friendsRepository = friendsRepository;
        //this.planAttackService = planAttackService;
    }

    public List<PlanDto> getAll(){
        return planRepository.findAll().stream().map(this::convert).collect(Collectors.toList());

    }

    public List<PlanAttackDto> findBySecret(String secret) {
        //Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        //String username = ((UserDetails)principal).getUsername();
        //int playerId = playerRepository.findByNick(username).getId();
        //return planAttackRepository.findByPlayerId(playerId).stream().map(this::convert)
        //        .collect(Collectors.toList());
        //planAttackService.convert()
        return planAttackRepository.findBySecret(secret).stream().map(this::convert)
                .collect(Collectors.toList());
    }

    public PlanDto start(String secret){
        Plan plan = planRepository.findBySecret(secret);
        //Plan plan = new Plan();
        PlanDto planDto = convert(plan);
        plan.setStarted(true);
        plan.setTimeStarted(new Date());
        planRepository.save(plan);
        return planDto;
    }

    public PlanDto check(int id){
        Plan plan = planRepository.findOne(id);
        //Plan plan = new Plan();
        PlanDto planDto = new PlanDto();
        if (plan != null && plan.isStarted())
        {
            planDto = convert(plan);
        }
        else
        {
            planDto = convert(new Plan());
        }
        //plan.setStarted(true);
        //plan.setTimeStarted(new Date());
        //planRepository.save(plan);
        return planDto;
    }

    public PlanDto add(PlanCreateDto planCreateDto) {
        return convert(planRepository.save(convert(planCreateDto)));
    }

    public PlanDto update(int id) {
        final Plan planToUpdate = planRepository.findOne(id);
        if (planToUpdate == null) {
            throw new WebApplicationException(Response.Status.NOT_FOUND);
        }
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String username = ((UserDetails)principal).getUsername();
        int playerId = playerRepository.findByNick(username).getId();

        //if (playerId == planToUpdate.getPlayerId())
        if (planToUpdate.isStarted() != true)
        {
            planToUpdate.setTimeStarted(new Date());
            planToUpdate.setStarted(true);
        }
        else {planToUpdate.setStarted(false);}

        return convert(planRepository.save(planToUpdate));
    }

    public void delete(int id) {

        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String username = ((UserDetails)principal).getUsername();
        int playerId = playerRepository.findByNick(username).getId();
        if (playerId == planRepository.findOne(id).getPlayerId())
        //village.setPlayerId(playerId);
        {
            planRepository.delete(id);
            planTargetRepository.delete(planTargetRepository.findByPlanId(id));
        }
        //return convert(friend);
    }

    private PlanAttackDto convert(PlanAttack planAttack) {
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


    private Plan convert(PlanCreateDto planCreateDto) {
        //return new PlanDto()

        return new Plan(
                0,
                planCreateDto.getSecret(),
                planCreateDto.getName(),
                planCreateDto.getViewable(),
                planCreateDto.getPlayerId(),
                new Date(),
                //planCreateDto.getWhenInit(),
                planCreateDto.getType(),
                planCreateDto.isStarted(),
                planCreateDto.getTimeStarted()

        );
    }

    private PlanDto convert(Plan plan) {
        //return new PlanDto()

        return new PlanDto(
                plan.getId(),
                plan.getSecret(),
                plan.getName(),
                plan.getViewable(),
                plan.getPlayerId(),
                plan.getWhenInit(),
                plan.getType(),
                plan.isStarted(),
                plan.getTimeStarted()

        );
    }

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
                planAttackCreateDto.getTimestamp()

        );
    }

    private <T> T requireNotNull(T object) {
        if (object == null) {
            throw new WebApplicationException(Response.Status.NOT_FOUND);
        }
        return object;
    }

}
