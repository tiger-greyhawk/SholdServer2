package ru.rsoft.shold.backend.service;

import org.igniterealtime.restclient.RestApiClient;
import org.igniterealtime.restclient.entity.AuthenticationToken;
import org.igniterealtime.restclient.entity.GroupEntity;
import org.igniterealtime.restclient.entity.MUCRoomEntity;
import org.igniterealtime.restclient.entity.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestBody;
import ru.rsoft.shold.core.dto.*;
import ru.rsoft.shold.core.entity.Faction;
import ru.rsoft.shold.core.entity.FactionInvite;
import ru.rsoft.shold.core.entity.FactionPlayer;
import ru.rsoft.shold.core.entity.Player;
import ru.rsoft.shold.core.repository.FactionInviteRepository;
import ru.rsoft.shold.core.repository.FactionPlayerRepository;
import ru.rsoft.shold.core.repository.FactionRepository;
import ru.rsoft.shold.core.repository.PlayerRepository;
import ru.rsoft.shold.core.repository.timing.PlanRepository;

import javax.ws.rs.PathParam;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by Admin on 07.10.2016.
 */
@Component
public class FactionService {

    private final FactionRepository factionRepository;
    private final PlayerRepository playerRepository;
    private final FactionInviteRepository factionInviteRepository;
    private final FactionPlayerRepository factionPlayerRepository;

    @Autowired
    public FactionService(FactionRepository factionRepository, PlayerRepository playerRepository, FactionInviteRepository factionInviteRepository, FactionPlayerRepository factionPlayerRepository) {
        this.factionRepository = factionRepository;
        this.playerRepository = playerRepository;
        this.factionInviteRepository = factionInviteRepository;
        this.factionPlayerRepository = factionPlayerRepository;
    }

    public List<FactionDto> findAll() {
        return factionRepository.findAll().stream().map(this::convert)
                .collect(Collectors.toList());
    }

    //factionPlayerRepository

    public List<PlayerDto> findPlayers(int factionId) {

        final List<PlayerDto> result = new ArrayList<>();
        for (final FactionPlayer factionPlayer : factionPlayerRepository.findByFactionId(factionId)) {
            final PlayerDto playerDto = convert(playerRepository.findOne(factionPlayer.getPlayerId()));
            result.add(playerDto);
        }
        return result;


//        final List<PlayerDto> result = new ArrayList<>();
//        for (final Player player : factionPlayerRepository.findByFactionId(factionId)) {
//            final PlayerDto playerDto = convert(player);
//            result.add(playerDto);
//        }
//        return result;


//        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
//        String username = ((UserDetails) principal).getUsername();
//        Player player = playerRepository.findByNick(username);
//        if (player.getFactionId() == factionId)
//            return playerRepository.findByFactionId(factionId).stream().map(this::convert)
//                    .collect(Collectors.toList());
//        else return playerRepository.findByFactionId(factionId).stream().map(this::convertNames)
//                .collect(Collectors.toList());
    }

    /*public List<PlayerDto> findPlayers(int factionId){
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String username = ((UserDetails)principal).getUsername();
        Player player = playerRepository.findByNick(username);
        if (player.getFactionId() == factionId)
        return playerRepository.findByFactionId(factionId).stream().map(this::convert)
                .collect(Collectors.toList());
        else return playerRepository.findByFactionId(factionId).stream().map(this::convertNames)
                .collect(Collectors.toList());
    }*/

    public FactionDto findOne(int factionId){
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String username = ((UserDetails)principal).getUsername();
        Player player = playerRepository.findByNick(username);
        if (player.getFactionId() == factionId)
            return convert(requireNotNull(factionRepository.findOne(factionId)));
        else return convertAnother(requireNotNull(factionRepository.findOne(factionId)));

    }



    public FactionDto addOne(@RequestBody FactionCreateDto factionCreateDto) {
//        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
//        List<PatternDto> temp = new ArrayList<PatternDto>();

//        String username = ((UserDetails)principal).getUsername();
//        Player player = playerRepository.findByNick(username);

        Player player = new ConvertUtility(playerRepository).getPrincipal();
        String username = player.getNick();
        factionCreateDto.setOwner(username);
        if (factionRepository.findByOwner(username).size() > 0)
        //if (factionRepository.findByOwner(username).size() > 0)
            return null;

        String factionNameJabber = factionCreateDto.getName();
        factionNameJabber = factionNameJabber.replaceAll(" ", "_");
        AuthenticationToken authenticationToken = new AuthenticationToken("4SUv4p921x1z3xr7");
        RestApiClient restApiClient = new RestApiClient("http://192.168.10.110", 9090, authenticationToken);
        MUCRoomEntity chatRoomOfficer = new MUCRoomEntity(factionNameJabber+"_"+"_officer", factionNameJabber, "description");
        MUCRoomEntity chatRoomBasic = new MUCRoomEntity(factionNameJabber+"_"+"_basic", factionNameJabber, "description");
        chatRoomBasic.setSubject("Базовая комната");
        GroupEntity groupEntity = new GroupEntity(factionNameJabber, factionNameJabber+" Group");
        restApiClient.createGroup(groupEntity);
        restApiClient.addUserToGroup(username, factionNameJabber);
        restApiClient.createChatRoom(chatRoomOfficer);

        restApiClient.createChatRoom(chatRoomBasic);
        restApiClient.addMember(factionNameJabber + "_" + "_basic", factionNameJabber);
        restApiClient.addMember(factionNameJabber + "_" + "_officer", factionNameJabber);
        //restApiClient.addMember(factionNameJabber, username);
        //restApiClient.addMember(factionCreateDto.getName()+"_"+username+"_basic", username);
        //restApiClient.addMember(factionCreateDto.getName()+"_"+username+"_basic", username+"@jabber.shold.tk");   //  пробуем добавить группу, а не пользователя
        //chatRoomBasic.setMembers();
        FactionDto newFaction = convert(factionRepository.save(convert(factionCreateDto)));
        //factionPlayerRepository.addOne(newFaction.getId(), player.getId());
        FactionPlayerCreateDto factionPlayerCreateDto = new FactionPlayerCreateDto(player.getId(), newFaction.getId(),true, true,"");

        factionPlayerRepository.save(convert(factionPlayerCreateDto));
        return newFaction;
    }

    public FactionDto delete(int factionId)
    {
        Faction faction = factionRepository.findOne(factionId);
        FactionDto factionDto;
        Player player = new ConvertUtility(playerRepository).getPrincipal();
        if (player.getNick().equals(faction.getOwner())) {
            AuthenticationToken authenticationToken = new AuthenticationToken("4SUv4p921x1z3xr7");
            RestApiClient restApiClient = new RestApiClient("http://192.168.10.110", 9090, authenticationToken);
            restApiClient.deleteChatRoom(faction.getBasicChat());
            restApiClient.deleteChatRoom(faction.getOfficerChat());
            restApiClient.deleteGroup((faction.getName().replaceAll(" ", "_")));
            for (FactionPlayer factionForDel : factionPlayerRepository.findByFactionId(faction.getId()))
                factionPlayerRepository.delete(factionForDel);
            factionDto = convert(faction);
            factionRepository.delete(faction.getId());
            if (factionDto != null)
                return factionDto;
            else return null;
        }
        return null;
    }

    public FactionDto update(FactionDto factionDto) {
        final Faction factionToUpdate = factionRepository.findOne(factionDto.getId());
        //final Faction factionToUpdate = factionRepository.findOne(id);
        if (factionToUpdate == null) {
            // надо возвращать 404, если нет такого ресурса
            throw new WebApplicationException(Response.Status.NOT_FOUND);
        }
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String username = ((UserDetails)principal).getUsername();
        //int playerId = playerRepository.findByNick(username).getId();

        if (username == factionToUpdate.getOwner())

        return convert(factionRepository.save(factionToUpdate));
        else throw new WebApplicationException(Response.Status.FORBIDDEN);
    }

    public List<FactionPlayerDto> getFactionPlayers(@PathParam("id") int factionId) {
        //Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
//        List<PatternDto> temp = new ArrayList<PatternDto>();

        //String username = ((UserDetails)principal).getUsername();
        //Player player = playerRepository.findByNick(username);
        //factionCreateDto.setOwner(username);
        //FactionDto factionDto = convert(factionRepository.findOne(factionId));
        //FactionPlayerCreateDto factionPlayerCreateDto;
        //if (player.getNick().equals(factionDto.getOwner())) {
        //if (factionPlayerRepository.isOfficer(player.getId(), factionId)) {
            //if (factionPlayerRepository.findByPlayerId(playerDto.getId()) == null)
            {
                //factionPlayerCreateDto = new FactionPlayerCreateDto(playerDto.getId(), factionId, false, false, "");
                //return convert(factionPlayerRepository.save(convert(factionPlayerCreateDto)));
            }
        return factionPlayerRepository.findByFactionId(factionId).stream().map(this::convert)
                .collect(Collectors.toList());
        //return convert(factionPlayerRepository.findByFactionId(factionId));
        //}
        //return null;
        /*
        if (player.getId() == id){
            FactionPlayer factionPlayer = new FactionPlayer(0, player.getId(), factionDto.getId(), true, false, "");
            factionPlayerRepository.save(factionPlayer);
        }
        else if (factionDto.getId() == id) {}
        else throw new WebApplicationException(Response.Status.FORBIDDEN);


        return convert(factionPlayerRepository.save(convert(factionPlayer)));
        */
    }

    public FactionPlayerDto invite(@PathParam("id") int factionId, @RequestBody PlayerDto playerDto) {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
//        List<PatternDto> temp = new ArrayList<PatternDto>();

        String username = ((UserDetails)principal).getUsername();
        Player player = playerRepository.findByNick(username);
        //factionCreateDto.setOwner(username);
        FactionDto factionDto = convert(factionRepository.findOne(factionId));
        FactionPlayerCreateDto factionPlayerCreateDto;
        //if (player.getNick().equals(factionDto.getOwner())) {
        if (factionPlayerRepository.isOfficer(player.getId(), factionId)) {
            //if (factionPlayerRepository.findByPlayerId(playerDto.getId()) == null)
            {
                factionPlayerCreateDto = new FactionPlayerCreateDto(playerDto.getId(), factionId, false, false, "");
                return convert(factionPlayerRepository.save(convert(factionPlayerCreateDto)));
            }
        }
        return null;
        /*
        if (player.getId() == id){
            FactionPlayer factionPlayer = new FactionPlayer(0, player.getId(), factionDto.getId(), true, false, "");
            factionPlayerRepository.save(factionPlayer);
        }
        else if (factionDto.getId() == id) {}
        else throw new WebApplicationException(Response.Status.FORBIDDEN);


        return convert(factionPlayerRepository.save(convert(factionPlayer)));
        */
    }

    public FactionPlayerDto setOfficer(@PathParam("id") int factionId, @RequestBody PlayerDto playerDto) {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
//        List<PatternDto> temp = new ArrayList<PatternDto>();

        String username = ((UserDetails)principal).getUsername();
        Player player = playerRepository.findByNick(username);
        //factionCreateDto.setOwner(username);
        FactionDto factionDto = convert(factionRepository.findOne(factionId));
        //FactionPlayer factionPlayer;
        if (player.getNick().equals(factionDto.getOwner())) {
        //if (factionPlayerRepository.isOfficer(player.getId(), factionId)) {
            //if (factionPlayerRepository.findByPlayerId(playerDto.getId()) == null)
            {
                FactionPlayer factionPlayer = factionPlayerRepository.getPlayer(playerDto.getId(), factionId);
                factionPlayer.setOfficer(true);
                //factionPlayerCreateDto = new FactionPlayerCreateDto(playerDto.getId(), factionId, false, false, "");
                return convert(factionPlayerRepository.save(factionPlayer));
            }
        }
        return null;

    }

    public void elimination(@PathParam("id") int factionId, @RequestBody PlayerDto playerDto) {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String username = ((UserDetails)principal).getUsername();
        Player player = playerRepository.findByNick(username);
        FactionDto factionDto = convert(factionRepository.findOne(factionId));
        //FactionPlayer factionPlayer;
        if (player.getNick().equals(factionDto.getOwner())) {
            //if (factionPlayerRepository.findByPlayerId(playerDto.getId()) == null)
            {
                //factionPlayerCreateDto = new FactionPlayerCreateDto(playerDto.getId(), factionId, false, false, "");
                //factionPlayer = new FactionPlayer();
                for (FactionPlayer factionPlayer1 : factionPlayerRepository.findByFactionId(factionId))
                    if (factionPlayer1.getPlayerId() == playerDto.getId()) {
                        factionPlayerRepository.delete(factionPlayer1);
                    }


            }
        }
        //return null;
    }

//    private void inviteMe(){
//
//    }
//
//    private void inviteOther(){
//
//    }

    private FactionDto convert(Faction faction){
        return new FactionDto(
                faction.getId(),
                faction.getHouseId(),
                faction.getName(),
                faction.getOwner(),
                faction.getOfficer1(),
                faction.getOfficer2(),
                faction.getOfficer3(),
                faction.getOfficer4(),
                faction.getOfficer5(),
                faction.getOfficerChat(),
                faction.getBasicChat()
        );
    }

    private FactionDto convertAnother(Faction faction){
        return new FactionDto(
                faction.getId(),
                faction.getHouseId(),
                faction.getName(),
                faction.getOwner(),
                "",//faction.getOfficer1(),
                "",//faction.getOfficer2(),
                "",//faction.getOfficer3(),
                "",//faction.getOfficer4(),
                "",//faction.getOfficer5(),
                "",//faction.getOfficerChat(),
                ""//faction.getBasicChat()
        );
    }

    private Faction convert(FactionCreateDto factionCreateDto){
        return new Faction(
                0,
                factionCreateDto.getHouseId(),
                factionCreateDto.getName(),
                factionCreateDto.getOwner(),
                factionCreateDto.getOfficer1(),
                factionCreateDto.getOfficer2(),
                factionCreateDto.getOfficer3(),
                factionCreateDto.getOfficer4(),
                factionCreateDto.getOfficer5()
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

    private PlayerDto convertNames(Player player) {
//        if (village.getUser() == null) { userRepository.findOne(1);}
        return new PlayerDto(
                player.getId(),
//                village.getUser().getId(),
                0,

//                village.getBody() == null ? null : village.getBody().getId(),
                player.getNick(),
                0,
                "forbidden",
                new Date(0),
                0,
                0
//                player.getFriends()

        );
    }

    private FactionPlayer convert(FactionPlayerCreateDto factionPlayerCreateDto){
        return new FactionPlayer(
                0,
                factionPlayerCreateDto.getPlayerId(),
                factionPlayerCreateDto.getFactionId(),
                factionPlayerCreateDto.isConfirm(),
                factionPlayerCreateDto.isOfficer(),
                factionPlayerCreateDto.getComment()
        );
    }

    private FactionPlayerDto convert(FactionPlayer factionPlayer){
        return new FactionPlayerDto(
                factionPlayer.getId(),
                factionPlayer.getPlayerId(),
                factionPlayer.getFactionId(),
                factionPlayer.isConfirm(),
                factionPlayer.isOfficer(),
                factionPlayer.getComment()
        );
    }

    private <T> T requireNotNull(T object) {
        if (object == null) {
            throw new WebApplicationException(Response.Status.NOT_FOUND);
        }
        return object;
    }
}
