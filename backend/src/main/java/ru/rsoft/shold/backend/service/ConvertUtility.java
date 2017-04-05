package ru.rsoft.shold.backend.service;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import ru.rsoft.shold.core.entity.Player;
import ru.rsoft.shold.core.repository.PlayerRepository;
import ru.rsoft.shold.core.repository.timing.PlanRepository;

/**
 * Created by Admin on 04.01.2017.
 */
public class ConvertUtility {

    PlayerRepository playerRepository;

    public ConvertUtility(PlayerRepository playerRepository) {
        this.playerRepository = playerRepository;
    }

    public Player getPrincipal() {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
//        List<PatternDto> temp = new ArrayList<PatternDto>();

        String username = ((UserDetails) principal).getUsername();
        Player player = playerRepository.findByNick(username);
        return player;
    }
}
