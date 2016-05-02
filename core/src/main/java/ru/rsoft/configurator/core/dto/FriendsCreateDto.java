package ru.rsoft.configurator.core.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import ru.rsoft.configurator.core.entity.Player;


import javax.annotation.Nonnull;
import javax.annotation.Nullable;

/**
 * Created by Admin on 27.04.2016.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class FriendsCreateDto {

    @Nonnull
    private final int playerId;

    @Nonnull
    private final Player friend;

    @Nullable
    private final String comment;

    public FriendsCreateDto(@Nonnull int playerId, @Nonnull Player friend, String comment) {
        this.playerId = playerId;
        this.friend = friend;
        this.comment = comment;
    }

    @Nonnull
    public int getPlayerId() {
        return playerId;
    }

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
        return "FriendsCreateDto{" +
                "playerId=" + playerId +
                ", friend=" + friend +
                ", comment='" + comment + '\'' +
                '}';
    }
}
