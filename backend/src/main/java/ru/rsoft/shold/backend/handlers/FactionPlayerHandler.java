package ru.rsoft.shold.backend.handlers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.rsoft.shold.backend.service.FactionPlayerService;
import ru.rsoft.shold.core.dto.FactionDto;
import ru.rsoft.shold.core.dto.FactionPlayerDto;
import ru.rsoft.shold.core.dto.PlayerDto;
import ru.rsoft.shold.core.entity.FactionPlayer;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import java.util.List;

/**
 * Created by Admin on 30.12.2016.
 */

@Component
@Path("/invite")
public class FactionPlayerHandler {

    private final FactionPlayerService factionPlayerService;


    @Autowired
    public FactionPlayerHandler(FactionPlayerService factionPlayerService)
    {
        this.factionPlayerService = factionPlayerService;
    }

    @Path("/")
    @GET
    public List<FactionPlayerDto> list() {
        return factionPlayerService.findByPlayer();
    }

    @Path("/{id}")
    @GET
    public List<PlayerDto> list(@PathParam("id") int id) {
        return factionPlayerService.findByFaction(id);
    }


}
