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
    private final int friendId;

    @Nullable
    private final String comment;

    public FriendsCreateDto(@Nonnull int playerId, @Nullable int friendId, @Nullable String comment) {
//    public FriendsCreateDto(@Nonnull int playerId, @Nullable String comment) {
        this.playerId = playerId;
        this.friendId = friendId;
        this.comment = comment;
    }

    @Nonnull
    public int getPlayerId() {
        return playerId;
    }

    @Nullable
    public int getFriendId() {
        return friendId;
    }

    @Nullable
    public String getComment() {
        return comment;
    }

    @Override
    public String toString() {
        return "FriendsCreateDto{" +
                "playerId=" + playerId +
                ", friendId=" + friendId +
                ", comment='" + comment + '\'' +
                '}';
    }
}
