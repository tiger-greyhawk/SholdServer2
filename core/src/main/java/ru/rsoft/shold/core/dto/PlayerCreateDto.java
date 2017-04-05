package ru.rsoft.shold.core.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.Date;

/**
 * Created by Admin on 26.11.2015.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class PlayerCreateDto {
    @Nonnull
    private final Integer userId;
    @Nonnull
    private final String nick;
    private int invite;
    private String motivater;
    private Date lastAccess;
    @Nullable
    private int factionId;
    @Nullable
    private int factionIdInvited;

//    private final List<Friend> friends;

    public PlayerCreateDto() {
        this(0, "", 3, "", new Date(), 0, 0);
    }

    public PlayerCreateDto(@Nonnull Integer userId, @Nonnull String nick, int invite, String motivater, Date lastAccess, @Nullable int factionId, @Nullable int factionIdInvited) {
        this.userId = userId;
        this.nick = nick;
        this.invite = invite;
        this.motivater = motivater;
        this.lastAccess = lastAccess;
        this.factionId = factionId;
        this.factionIdInvited = factionIdInvited;
    }

    @Nonnull
    public Integer getUserId() {
        return userId;
    }

    @Nonnull
    public String getNick() {
        return nick;
    }

    public int getInvite() {
        return invite;
    }

    public String getMotivater() {
        return motivater;
    }


    public Date getLastAccess() {
        return lastAccess;
    }
    @Nullable
    public int getFactionId() {
        return factionId;
    }

    @Nullable
    public int getFactionIdInvited() {
        return factionIdInvited;
    }

    @Override
    public String toString() {
        return "PlayerCreateDto{" +
                "userId=" + userId +
                ", nick='" + nick + '\'' +
                ", invite=" + invite +
                ", motivater='" + motivater + '\'' +
                ", lastAccess=" + lastAccess +
                ", factionId=" + factionId +
                ", factionIdInvited=" + factionIdInvited +
                '}';
    }
}
