package ru.rsoft.shold.core.entity;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.persistence.*;
import java.util.Date;

/**
 * Created by Admin on 26.11.2015.
 */
@Entity
@Table(name = "PLAYERS")
public class Player implements java.io.Serializable{
    @Id
    @GeneratedValue(strategy =  GenerationType.IDENTITY)
    @Column(name = "ID")
    private int id;

    @Column(name = "USER_ID")
    private int userId;

    @Column(name = "NICK", unique = true, nullable = false)
    @Nonnull
    private String nick;

    @Column(name = "INVITE")
    private int invite;

    @Column(name = "MOTIVATER")
    private String motivater;

    @Column(name = "LAST_ACCESS")
    private Date lastAccess;

    @Column(name = "FACTION_ID")
    @Nullable
    private int factionId;

    @Column(name = "FACTION_ID_INVITED")
    @Nullable
    private int factionIdInvited;

    public Player() {
        this(0, "", 3, "", new Date(), 0, 0);
    }
    public Player(@Nonnull Integer userId, @Nonnull String nick, int invite, String motivater, Date lastAccess, @Nullable int factionId, @Nullable int factionIdInvited) {
        this.userId = userId;
        this.nick = nick;
        this.invite = invite;
        this.motivater = motivater;
        this.lastAccess = new Date();
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

    public void setInvite(int invite) {
        this.invite = invite;
    }

    public String getMotivater() {
        return motivater;
    }

    public Date getLastAccess() {
        return lastAccess;
    }

    public void setLastAccess(Date lastAccess) {
        this.lastAccess = lastAccess;
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
        return "Player{" +
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
