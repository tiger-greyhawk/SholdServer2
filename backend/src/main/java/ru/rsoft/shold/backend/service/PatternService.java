package ru.rsoft.shold.backend.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import ru.rsoft.shold.core.dto.PatternCreateDto;
import ru.rsoft.shold.core.dto.PatternDto;
import ru.rsoft.shold.core.entity.Friend;
import ru.rsoft.shold.core.entity.Pattern;
import ru.rsoft.shold.core.entity.Player;
import ru.rsoft.shold.core.entity.User;
import ru.rsoft.shold.core.repository.FriendsRepository;
import ru.rsoft.shold.core.repository.PatternRepository;
import ru.rsoft.shold.core.repository.PlayerRepository;

import javax.ws.rs.PathParam;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


@Component
@Transactional(propagation = Propagation.REQUIRED)
public class PatternService {
    private final PlayerRepository playerRepository;
    private final PatternRepository patternRepository;
    private final FriendsRepository friendsRepository;

    @Autowired
    public PatternService(PlayerRepository playerRepository, PatternRepository patternRepository, FriendsRepository friendsRepository) {
        this.playerRepository = playerRepository;
        this.patternRepository = patternRepository;
        this.friendsRepository = friendsRepository;
    }

    public List<PatternDto> findByPlayerId() {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String username = ((UserDetails)principal).getUsername();
        int playerId = playerRepository.findByNick(username).getId();
        return patternRepository.findByPlayerId(playerId).stream().map(this::convert)
                .collect(Collectors.toList());
    }
/*
    public List<PatternDto> findPlayerIdWithoutFiles() {
        //    ******************  НАДО ВЕРНУТЬ СПИСОК шаблонов без файлов ********************************************************************      ////////
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String username = ((UserDetails)principal).getUsername();

        Player player = playerRepository.findByNick(username);

        List<Pattern> temp1 = new ArrayList<Pattern>();
        //PatternDto temp = convertMin(temp1);
        List<PatternDto> temp2 = new ArrayList<PatternDto>();

        int playerId = player.getId();
//        temp1 = patternRepository.findByPlayerIdWithoutFiles(playerId);
//        temp1.forEach(
                temp2.addAll(patternRepository.findByPlayerIdWithoutFiles(playerId).stream().map(this::convertMin).collect(Collectors.toList()));

//        );
//        temp2.addAll(patternRepository.findByPlayerIdWithoutFiles(playerId).stream().map(this::convertMin)
//                .collect(Collectors.toList()));
//        return patternRepository.findByPlayerIdWithoutFiles(playerId).stream().map(this::convert)
//                .collect(Collectors.toList());
//        temp1 = new Pattern("","",player);
        return temp2;
    }
*/
    public List<PatternDto> findByFriend() {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        List<PatternDto> temp = new ArrayList<PatternDto>();

        String username = ((UserDetails)principal).getUsername();
        int playerId = playerRepository.findByNick(username).getId();
        List<Friend> friends = friendsRepository.findByPlayerId(playerId);
//        return friends.forEach(friends1 -> patternRepository.findByPlayerId(friends1.getFriendId()).stream().map(this::convert)
//                .collect(Collectors.toList()));
        friends.forEach(friends1 ->
                temp.addAll(patternRepository.findByPlayerId((friends1.getFriendId())).stream().map(this::convert)
                        .collect(Collectors.toList())));
        return temp;
    }

    public PatternDto add(PatternCreateDto patternCreateDto) {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String username = ((UserDetails)principal).getUsername();
        int playerId = playerRepository.findByNick(username).getId();
        patternCreateDto = new PatternCreateDto(playerId, patternCreateDto.getName(), patternCreateDto.getTypeCastle(), patternCreateDto.getAccessFrom(), patternCreateDto.getComment());

        return convert(patternRepository.save(convert(patternCreateDto)));
    }

    public PatternDto update(int id, PatternCreateDto patternCreateDto) {
        final Pattern patternToUpdate = patternRepository.findOne(id);
        if (patternToUpdate == null) {
            // надо возвращать 404, если нет такого ресурса
            throw new WebApplicationException(Response.Status.NOT_FOUND);
        }
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String username = ((UserDetails)principal).getUsername();
        int playerId = playerRepository.findByNick(username).getId();

        if (playerId == patternToUpdate.getPlayerId())
        {patternToUpdate.setComment(patternCreateDto.getComment());}

        else
        if (patternCreateDto.getComment().indexOf(patternToUpdate.getComment()) == -1)
            patternToUpdate.setComment(patternToUpdate.getComment() + "\r\n -------------- \r\n" + patternCreateDto.getComment() +
                "\r\n(added by " + username +")");  //playerRepository.findOne(patternToUpdate.getPlayerId()).getNick()
        else patternToUpdate.setComment(patternToUpdate.getComment() + "\r\n -------------- \r\n" +
                patternCreateDto.getComment().substring(patternToUpdate.getComment().length()) +
                "\r\n(added by " + username +")");

        return convert(patternRepository.save(patternToUpdate));
    }

    public void delete(int id) {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String username = ((UserDetails)principal).getUsername();

        int playerId = playerRepository.findByNick(username).getId();
        if (playerId == patternRepository.findOne(id).getPlayerId())
        try {
            patternRepository.delete(id);
        } catch (EmptyResultDataAccessException e) {
            // надо возвращать 404, если нет такого ресурса
            throw new WebApplicationException(Response.Status.NOT_FOUND);
        }
        else new WebApplicationException(Response.Status.FORBIDDEN);
    }

    public PatternDto findById(int id) {
        return convert(requireNotNull(patternRepository.findOne(id)));
    }

    private PatternDto convert(Pattern pattern) {
        return new PatternDto(
                pattern.getId(),
                pattern.getPlayerId(),
                pattern.getName(),
                pattern.getTypeCastle(),
                pattern.getAccessFrom(),
                pattern.getComment()
//                village.getIdInWorld()
        );
    }
/*
    private PatternDto convertMin(Pattern pattern) {
        return new PatternDto(
                pattern.getId(),
                pattern.getPlayerId(),
                pattern.getName(),
                pattern.getTypeCastle(),
                pattern.getAccessFrom()
        );
    }
*/
    private Pattern convert(PatternCreateDto patternCreateDto) {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        List<PatternDto> temp = new ArrayList<PatternDto>();

        String username = ((UserDetails)principal).getUsername();
        int playerId = playerRepository.findByNick(username).getId();
        final Player player = requireNotNull(playerRepository.findOne(playerId));
        //final Player player = requireNotNull(playerRepository.findOne(patternCreateDto.getPlayerId()));
        //final Player player = requireNotNull(SecurityContextHolder.getContext().getAuthentication().getPrincipal());

        return new Pattern(
                null,
                player.getId(),
                patternCreateDto.getName(),
                patternCreateDto.getTypeCastle(),
                patternCreateDto.getAccessFrom(),
                patternCreateDto.getComment()

        );
    }

    private <T> T requireNotNull(T object) {
        if (object == null) {
            throw new WebApplicationException(Response.Status.NOT_FOUND);
        }
        return object;
    }

}
