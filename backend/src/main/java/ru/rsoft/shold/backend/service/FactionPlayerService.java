package ru.rsoft.shold.backend.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import ru.rsoft.shold.core.dto.FactionPlayerDto;
import ru.rsoft.shold.core.dto.PlayerDto;
import ru.rsoft.shold.core.entity.FactionPlayer;
import ru.rsoft.shold.core.entity.Player;
import ru.rsoft.shold.core.repository.FactionPlayerRepository;
import ru.rsoft.shold.core.repository.PlayerRepository;

import javax.ws.rs.PathParam;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by Admin on 30.12.2016.
 */

@Component
@Transactional(propagation = Propagation.REQUIRED)
public class FactionPlayerService {

    private final FactionPlayerRepository factionPlayerRepository;
    private final PlayerRepository playerRepository;
    //private final FriendsRepository friendsRepository;
    //private final UserRepository userRepository;

    @Autowired
    //public PlayerService(UserRepository userRepository, PlayerRepository playerRepository, FriendsRepository friendsRepository) {
    public FactionPlayerService(PlayerRepository playerRepository, FactionPlayerRepository factionPlayerRepository) {
        //this.userRepository = userRepository;
        this.playerRepository = playerRepository;
        this.factionPlayerRepository = factionPlayerRepository;
        //this.friendsRepository = friendsRepository;
    }

    public List<FactionPlayerDto> findByPlayer() {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String username = ((UserDetails)principal).getUsername();
        Player player = playerRepository.findByNick(username);
        return factionPlayerRepository.findByPlayerId(player.getId()).stream().map(this::convert).collect(Collectors.toList());
        //return factionPlayerRepository.findAll().stream().map(this::convert).collect(Collectors.toList());
    }

    public List<PlayerDto> findByFaction(@PathParam("id") int id) {
//        return factionPlayerRepository.findAll().stream().map(this::convert).collect(Collectors.toList());
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String username = ((UserDetails)principal).getUsername();
        Player player = playerRepository.findByNick(username);
        List<PlayerDto> factionPlayers = new ArrayList<>();
        List<FactionPlayer> factionPlayerDtos = new ArrayList<>();
        for (FactionPlayer factionPlayer : factionPlayerRepository.findByFactionId(id)) {

            if (factionPlayerRepository.findByPlayerId(player.getId()).contains(factionPlayer)) {
                factionPlayerDtos.add(factionPlayer);
            }
                //return factionPlayerRepository.findByFactionId(id).stream().map(this::convert).collect(Collectors.toList());
                //for (FactionPlayer fp : factionPlayerRepository.findByFactionId(id))

                //return factionPlayerRepository.findByFactionIdnew(id).stream().map(this::convert).collect(Collectors.toList());
                //factionPlayers.add(factionPlayer);
            //}
        }

        /*TODO
        Полная херня. Слишком много запросов к базе!!!
         */
        for (FactionPlayer fp : factionPlayerDtos)
        {
            for (FactionPlayer p : factionPlayerRepository.findByFactionId(fp.getFactionId()))
                factionPlayers.add(convert(playerRepository.getOne(p.getPlayerId())));
        }
        return factionPlayers;
        //return factionPlayerRepository.findByFactionId(id).stream().map(this::convert).collect(Collectors.toList());
    }

    /**
     * добавить игрока во фракцию.
     * @return
     */
    public FactionPlayerDto addOne(){
        return null;
    }

    private FactionPlayerDto convert(FactionPlayer factionPlayer) {
        return new FactionPlayerDto(
                factionPlayer.getId(),
                factionPlayer.getPlayerId(),
                factionPlayer.getFactionId(),
                factionPlayer.isConfirm(),
                factionPlayer.isOfficer(),
                factionPlayer.getComment()
        );
    }

    private PlayerDto convert(Player player) {
//        if (village.getUser() == null) { userRepository.findOne(1);}
        return new PlayerDto(
                player.getId(),
//                village.getUser().getId(),
                player.getUserId(),

//                village.getBody() == null ? null : village.getBody().getId(),
                player.getNick(),
                player.getInvite(),
                player.getMotivater(),
                player.getLastAccess(),
                player.getFactionId(),
                player.getFactionIdInvited()
//                player.getFriends()

        );
    }
}
