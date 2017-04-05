package ru.rsoft.shold.backend.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import ru.rsoft.shold.core.dto.timing.PlanTargetCreateDto;
import ru.rsoft.shold.core.dto.timing.PlanTargetDto;
import ru.rsoft.shold.core.entity.timing.PlanTarget;
import ru.rsoft.shold.core.repository.FriendsRepository;
import ru.rsoft.shold.core.repository.PlayerRepository;
import ru.rsoft.shold.core.repository.timing.PlanAttackRepository;
import ru.rsoft.shold.core.repository.timing.PlanRepository;
import ru.rsoft.shold.core.repository.timing.PlanTargetRepository;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by Admin on 14.08.2016.
 */

@Component
@Transactional(propagation = Propagation.REQUIRED)
public class PlanTargetService {

    @Autowired
    private final PlayerRepository playerRepository;
    @Autowired
    private final PlanAttackRepository planAttackRepository;
    @Autowired
    private final PlanTargetRepository planTargetRepository;
    @Autowired
    private final PlanRepository planRepository;
    @Autowired
    private final FriendsRepository friendsRepository;

    @Autowired
    public PlanTargetService(PlayerRepository playerRepository, PlanAttackRepository planAttackRepository,
                             PlanTargetRepository planTargetRepository, PlanRepository planRepository,
                             FriendsRepository friendsRepository) {
        this.playerRepository = playerRepository;
        this.planAttackRepository = planAttackRepository;
        this.planTargetRepository = planTargetRepository;
        this.planRepository = planRepository;
        this.friendsRepository = friendsRepository;
    }

    public List<PlanTargetDto> getAllTarget(){
        return planTargetRepository.findAll().stream().map(this::convert).collect(Collectors.toList());

    }

    public PlanTargetDto add(PlanTargetCreateDto planTargetCreateDto) {

        return convert(planTargetRepository.save(convert(planTargetCreateDto)));
    }

    private PlanTargetDto convert(PlanTarget planTarget) {
        //return new PlanDto()

        return new PlanTargetDto(
                planTarget.getId(),
                planTarget.getPlanId(),
                planTarget.getTargetVillageId(),
                planTarget.getTargetPlayerName(),
                planTarget.getTargetPlayerOnline(),
                planTarget.getTargetRoute(),
                planTarget.getTargetInfo(),
                planTarget.getTargetScreen()

        );
    }

    private PlanTarget convert(PlanTargetCreateDto planTarget) {
        //return new PlanDto()

        return new PlanTarget(
                0,
                planTarget.getPlanId(),
                planTarget.getTargetVillageId(),
                planTarget.getTargetPlayerName(),
                planTarget.getTargetPlayerOnline(),
                planTarget.getTargetRoute(),
                planTarget.getTargetInfo(),
                planTarget.getTargetScreen()

        );
    }
}
