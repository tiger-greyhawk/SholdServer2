package ru.rsoft.shold.core.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.Date;

/**
 * Created by Admin on 26.11.2015.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class PlayerDto {
    private final Integer id;
    //    private final int userId;
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


    public PlayerDto() {
        this(null, null, "", 3, "", new Date(), 0, 0);
    }

    public PlayerDto(Integer id, @Nonnull Integer userId, @Nonnull String nick, int invite, String motivater, Date lastAccess, @Nullable int factionId, @Nullable int factionIdInvited) {
        this.id = id;
        this.userId = userId;
        this.nick = nick;
        this.invite = invite;
        this.motivater = motivater;
        this.lastAccess = lastAccess;
        this.factionId = factionId;
        this.factionIdInvited = factionIdInvited;
    }

    public Integer getId() {
        return id;
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

    /*
        public List<Friend> getFriends() {
            return friends;
        }
    */

    @Override
    public String toString() {
        return "PlayerDto{" +
                "id=" + id +
                ", userId=" + userId +
                ", nick='" + nick + '\'' +
                ", invite=" + invite +
                ", motivater='" + motivater + '\'' +
                ", lastAccess=" + lastAccess +
                ", factionId=" + factionId +
                ", factionIdInvited=" + factionIdInvited +
                '}';
    }
}
