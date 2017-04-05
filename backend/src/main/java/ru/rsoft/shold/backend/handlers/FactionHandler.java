package ru.rsoft.shold.backend.handlers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestBody;
import ru.rsoft.shold.backend.service.FactionService;
import ru.rsoft.shold.core.dto.*;
import ru.rsoft.shold.core.entity.Faction;
import ru.rsoft.shold.core.entity.Player;

import javax.ws.rs.*;
//import javax.ws.rs.POST;
//import javax.ws.rs.Path;
//import javax.ws.rs.PathParam;
import java.util.List;

/**
 * Created by Admin on 07.10.2016.
 */
@Component
@Path("/faction")
public class FactionHandler {

    private final FactionService factionService;


    @Autowired
    public FactionHandler(FactionService factionService)
    {
        this.factionService = factionService;
    }

    @Path("/")
    @GET
    public List<FactionDto> list() {
        return factionService.findAll();
    }

/*    @Path("/{id}")
    @GET
    public List<PlayerDto> list(@PathParam("id") Integer id) {
        return factionService.findPlayers(id);
    }*/

    @Path("/{id}")
    @GET
    public FactionDto getOne(@PathParam("id") Integer id) {
        return factionService.findOne(id);
    }

    @Path("/players/{id}")
    @GET
    public List<PlayerDto> findPlayers(@PathParam("id") Integer id) {
        return factionService.findPlayers(id);
    }

    @Path("/")
    @POST
    public FactionDto add(@RequestBody FactionCreateDto factionCreateDto) {
        return factionService.addOne(factionCreateDto);
    }

    @Path("/")
    @PUT
    public FactionDto update(@RequestBody FactionDto factionDto) {
        return factionService.update(factionDto);
    }

    @Path("/{factionId}")
    @DELETE
    public FactionDto delete(@PathParam("factionId") Integer factionId) {
        return factionService.delete(factionId);
    }

    @Path("/invite/{factionId}")
    @GET
    public List<FactionPlayerDto> getFactionPlayers(@PathParam("factionId") int factionId) {
        return factionService.getFactionPlayers(factionId);
    }

    @Path("/invite/{factionId}")
    @POST
    public FactionPlayerDto invite(@PathParam("factionId") int factionId, @RequestBody PlayerDto playerDto) {
        return factionService.invite(factionId, playerDto);
    }

    @Path("/setofficer/{factionId}")
    @POST
    public FactionPlayerDto setOfficer(@PathParam("factionId") int factionId, @RequestBody PlayerDto playerDto) {
        return factionService.setOfficer(factionId, playerDto);
    }

    @Path("/invite/{factionId}")
    @DELETE
    public void expulsion(@PathParam("factionId") int factionId, @RequestBody PlayerDto playerDto) {
        factionService.elimination(factionId, playerDto);
    }
}
