package ru.rsoft.shold.backend.handlers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestBody;
import ru.rsoft.shold.backend.service.FactionInviteService;
import ru.rsoft.shold.core.dto.FactionInviteCreateDto;
import ru.rsoft.shold.core.dto.FactionInviteDto;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import java.util.List;

/**
 * Created by Admin on 15.12.2016.
 */
@Component
@Path("/factioninvite/old")
public class FactionInviteHandler {

    private final FactionInviteService factionInviteService;

    @Autowired
    public FactionInviteHandler(FactionInviteService factionInviteService)
    {
        this.factionInviteService = factionInviteService;
    }

    @Path("/")
    @GET
    public List<FactionInviteDto> list() {

        return factionInviteService.findByPlayerId();
    }

    @Path("/")
    @POST
    public FactionInviteDto invite(@RequestBody FactionInviteCreateDto factionInviteCreateDto) {
        return factionInviteService.inviteIntoFaction(factionInviteCreateDto);
    }
}
