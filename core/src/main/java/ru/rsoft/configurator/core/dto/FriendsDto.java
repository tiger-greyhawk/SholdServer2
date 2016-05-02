package ru.rsoft.configurator.core.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import ru.rsoft.configurator.core.entity.Player;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.persistence.Id;

/**
 * Created by Admin on 27.04.2016.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class FriendsDto {
    @Id
    private final int id;

//    @Nonnull
//    private final int playerId;

    @Nonnull
    private final Player friend;

    @Nullable
    private final String comment;

    public FriendsDto(int id, @Nonnull Player friend, @Nullable String comment) {
        this.id = id;
//        this.playerId = playerId;
        this.friend = friend;
        this.comment = comment;
    }

/*
    public FriendsDto(int id, @Nonnull int playerId, @Nonnull Player friend, @Nullable String comment) {
        this.id = id;
        this.playerId = playerId;
        this.friend = friend;
        this.comment = comment;
    }
*/
    public int getId() {
        return id;
    }
/*
    @Nonnull
    public int getPlayerId() {
        return playerId;
    }
*/
    @Nonnull
    public Player getFriend() {
        return friend;
    }

    @Nullable
    public String getComment() {
        return comment;
    }

    @Override
    public String toString() {
        return "FriendsDto{" +
                "id=" + id +
//                ", playerId=" + playerId +
                ", friend=" + friend +
                ", comment='" + comment + '\'' +
                '}';
    }
}
