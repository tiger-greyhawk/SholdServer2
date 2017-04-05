package ru.rsoft.shold.backend.handlers;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestBody;
import ru.rsoft.shold.backend.security.CustomJdbcDaoImpl;

import ru.rsoft.shold.backend.security.UserDetailsServiceImpl;
import ru.rsoft.shold.backend.service.PlayerService;
import ru.rsoft.shold.core.dto.PlayerCreateDto;
import ru.rsoft.shold.core.dto.PlayerDto;
import ru.rsoft.shold.core.entity.Village;

import javax.ws.rs.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Created by Admin on 26.11.2015.
 */
@Component
@Path("/player")
public class PlayerHandler {
//    private final UserRepository userRepository;
//    private final PlayerRepository playerRepository;
//    private final VillageRepository villageRepository;
//    private final Village village;
/*
    @Autowired
    public PlayerHandler(UserRepository userRepository, PlayerRepository playerRepository, VillageRepository villageRepository) {//, Village village) {
        this.userRepository = userRepository;
        this.playerRepository = playerRepository;
        this.villageRepository = villageRepository;
//        this.village = village;
    }
*/
    //@Autowired
    //private final UserDetailsServiceImpl userDetailsServiceImpl;

    private final PlayerService playerService;

    //private final CustomJdbcDaoImpl customJdbcDao;


    /*@Autowired
    public PlayerHandler(PlayerService playerService, CustomJdbcDaoImpl customJdbcDao) {
        this.playerService = playerService;
        this.customJdbcDao = customJdbcDao;
    }*/


/*    @Autowired
    public PlayerHandler(PlayerService playerService, UserDetailsServiceImpl userDetailsServiceImpl) {
        this.playerService = playerService;
        this.userDetailsServiceImpl = userDetailsServiceImpl;
    }
*/

    @Autowired
    public PlayerHandler(PlayerService playerService) {
        this.playerService = playerService;
    }

    @Path("/")
    @GET
    public List<PlayerDto> list()  {
        return playerService.getAll();
    }

    public List<Village> villages = new ArrayList<Village>();

    @Path("/faction/{id}")
    @GET
    public List<PlayerDto> listByFaction(@PathParam("id") Integer id) {
        return playerService.getPlayersByFaction(id);
    }

    @Path("/{id}")
    @GET
    public PlayerDto get(@PathParam("id") Integer id) {
        return playerService.getOne(id);
    }

    @Path("/me")
    @GET
    public PlayerDto getMe() {
/*     try
     {
         JabberBot jabberBot = new JabberBot("test", "test", "jabber.shold.tk", "jabber.shold.tk", 5223);
         Thread botThread = new Thread(jabberBot);
         botThread.start();
     }
    catch(Exception e)
    {
        System.out.println(e.getMessage());
    }
        //jabberBot.run();
*/
        return playerService.getMe();
    }

    @Path("/me")
    @POST
    public void changePassword(@RequestBody PlayerCreateDto playerCreateDto, @RequestBody String pass){
        //customJdbcDao.myChangerPass(playerCreateDto, pass);
    }

    @Path("/")
    @POST
    public PlayerDto add(@RequestBody PlayerCreateDto playerCreateDto) {
        return playerService.addOne(playerCreateDto);
    }

    @Path("/{id}")
    @DELETE
    public void delete(@PathParam("id") int id) {
        playerService.deleteOne(id);
    }




}
