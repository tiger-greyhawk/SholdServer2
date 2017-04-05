package ru.rsoft.shold.backend.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestBody;
import ru.rsoft.shold.core.dto.FactionInviteCreateDto;
import ru.rsoft.shold.core.dto.FactionInviteDto;
import ru.rsoft.shold.core.entity.FactionInvite;
import ru.rsoft.shold.core.entity.Player;
import ru.rsoft.shold.core.repository.FactionInviteRepository;
import ru.rsoft.shold.core.repository.PlayerRepository;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by Admin on 15.12.2016.
 */

@Component
public class FactionInviteService {

    private final FactionInviteRepository factionInviteRepository;
    private final PlayerRepository playerRepository;

    @Autowired
    public FactionInviteService(FactionInviteRepository factionInviteRepository, PlayerRepository playerRepository)
    {
        this.playerRepository = playerRepository;
        this.factionInviteRepository = factionInviteRepository;
    }

    public FactionInviteDto inviteIntoFaction(@RequestBody FactionInviteCreateDto factionInviteCreateDto) {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String inviter = ((UserDetails)principal).getUsername();

        return convert(factionInviteRepository.save(convert(factionInviteCreateDto, inviter)));
    }

    public List<FactionInviteDto> findByPlayerId() {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String username = ((UserDetails)principal).getUsername();

        Player player = playerRepository.findByNick(username);
        return factionInviteRepository.findByPlayerId(player.getId()).stream().map(this::convert)
                .collect(Collectors.toList());


//        return convert(factionInviteRepository.save(convert(factionInviteCreateDto, inviter)));
    }

    private FactionInvite convert(FactionInviteCreateDto factionInviteCreateDto, String inviter){
        return new FactionInvite(
                factionInviteCreateDto.getFactionId(),
                factionInviteCreateDto.getPlayerId(),
                factionInviteCreateDto.isConfirm(),
                inviter,
                factionInviteCreateDto.getComment()
        );
    }

    private FactionInviteDto convert(FactionInvite factionInvite){
        return new FactionInviteDto(
                factionInvite.getId(),
                factionInvite.getFactionId(),
                factionInvite.getPlayerId(),
                factionInvite.isConfirm(),
                factionInvite.getInviter(),
                factionInvite.getComment()
        );
    }

}
