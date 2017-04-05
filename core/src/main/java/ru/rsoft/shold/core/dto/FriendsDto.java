package ru.rsoft.shold.core.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

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
    private final int playerId;

    @Nullable
    private final int friendId;

    @Nullable
    private  final boolean confirm;

    @Nullable
    private final String comment;

    public FriendsDto(int id, @Nonnull int playerId, @Nullable int friendId, @Nullable boolean confirm, @Nullable String comment) {
//    public FriendsDto(int id, @Nonnull int playerId, @Nullable String comment) {
        this.id = id;
        this.playerId = playerId;
        this.friendId = friendId;
        this.confirm = confirm;
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

    @Nonnull
    public int getPlayerId() {
        return playerId;
    }

    @Nonnull
    public int getFriendId() {
        return friendId;
    }

    @Nullable
    public boolean isConfirm() {
        return confirm;
    }

    @Nullable
    public String getComment() {
        return comment;
    }

    @Override
    public String toString() {
        return "FriendsDto{" +
                "id=" + id +
                ", playerId=" + playerId +
                ", friendId=" + friendId +
                ", confirm=" + confirm +
                ", comment='" + comment + '\'' +
                '}';
    }
}
