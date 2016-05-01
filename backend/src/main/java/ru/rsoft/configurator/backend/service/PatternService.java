package ru.rsoft.configurator.backend.service;

import org.springframework.stereotype.Component;
import ru.rsoft.configurator.core.dto.PatternCreateDto;
import ru.rsoft.configurator.core.dto.PatternDto;
import ru.rsoft.configurator.core.entity.Pattern;
import ru.rsoft.configurator.core.entity.Player;
import ru.rsoft.configurator.core.repository.PatternRepository;
import ru.rsoft.configurator.core.repository.PlayerRepository;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;
import java.util.List;
import java.util.stream.Collectors;


@Component
public class PatternService {
    private final PlayerRepository playerRepository;
    private final PatternRepository patternRepository;

    private PatternService(){
        this.playerRepository = null;
        this.patternRepository = null;
    }

    public PatternService(PlayerRepository playerRepository, PatternRepository patternRepository) {
        this.playerRepository = playerRepository;
        this.patternRepository = patternRepository;
    }

    public List<PatternDto> findAll() {
        return patternRepository.findAll().stream().map(this::convert)
                .collect(Collectors.toList());
    }

    public PatternDto add(PatternCreateDto patternCreateDto) {
        return convert(patternRepository.save(convert(patternCreateDto)));
    }

    public PatternDto findById(int id) {
        return convert(requireNotNull(patternRepository.findOne(id)));
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
        //final Player player = requireNotNull(playerRepository.findOne(patternCreateDto.getPlayerId()));
        //final Player player = requireNotNull(SecurityContextHolder.getContext().getAuthentication().getPrincipal());

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

    private <T> T requireNotNull(T object) {
        if (object == null) {
            throw new WebApplicationException(Response.Status.NOT_FOUND);
        }
        return object;
    }

}
