package ru.rsoft.configurator.backend.handlers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestBody;
import ru.rsoft.configurator.core.dto.PatternDto;
import ru.rsoft.configurator.core.dto.PatternCreateDto;
import ru.rsoft.configurator.core.dto.VillageCreateDto;
import ru.rsoft.configurator.core.dto.VillageDto;
import ru.rsoft.configurator.core.entity.Player;
import ru.rsoft.configurator.core.entity.Pattern;
import ru.rsoft.configurator.core.repository.PatternRepository;
import ru.rsoft.configurator.core.repository.PlayerRepository;


import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by Admin on 18.04.2016.
 */

@Component
@Path("/pattern")
public class PatternHandler {

    private final PlayerRepository playerRepository;
    private final PatternRepository patternRepository;


    @Autowired
    public PatternHandler(PlayerRepository playerRepository, PatternRepository patternRepository) {
        this.playerRepository = playerRepository;
        this.patternRepository = patternRepository;
    }

    @Path("/")
    @GET
    public List<PatternDto> list() {
        return patternRepository.findAll().stream().map(this::convert)
                .collect(Collectors.toList());
    }

    @Path("/{id}")
    @GET
    public PatternDto get(@PathParam("id") int id) {
            return convert(requireNotNull(patternRepository.findOne(id)));
    }

    @Path("/")
    @POST
    public PatternDto add(@RequestBody PatternCreateDto patternCreateDto) {
        return convert(patternRepository.save(convert(patternCreateDto)));
    }

    private <T> T requireNotNull(T object) {
        if (object == null) {
            throw new WebApplicationException(Response.Status.NOT_FOUND);
        }
        return object;
    }

    private PatternDto convert(Pattern pattern) {
        return new PatternDto(
                pattern.getId(),
                pattern.getName(),
                pattern.getFileName(),
                pattern.getFile(),
                pattern.getPhotoName(),
                pattern.getPhoto(),
                pattern.getPlayer() == null ? null : pattern.getPlayer().getId(),
                pattern.getAccessFrom()
//                village.getIdInWorld()
        );
    }
    private Pattern convert(PatternCreateDto patternCreateDto) {
        final Player player = requireNotNull(playerRepository.findOne(patternCreateDto.getPlayerId()));

        return new Pattern(
                patternCreateDto.getName(),
                patternCreateDto.getFileName(),
                patternCreateDto.getFile(),
                patternCreateDto.getPhotoName(),
                patternCreateDto.getPhoto(),
                player,
                patternCreateDto.getAccessFrom()

        );
    }
}