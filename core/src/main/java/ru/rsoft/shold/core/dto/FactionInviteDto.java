package ru.rsoft.shold.core.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.annotation.Nullable;

/**
 * Created by Admin on 15.12.2016.
 */

@JsonIgnoreProperties(ignoreUnknown = true)
public class FactionInviteDto {

    private int id;
    private int factionId;
    private int playerId;
    private boolean confirm;
    private String inviter;  // кто пригласил или свой ник, если подача заявки
    @Nullable
    private String comment;

    public FactionInviteDto(int id, int factionId, int playerId, boolean confirm, String inviter, String comment) {
        this.id = id;
        this.factionId = factionId;
        this.playerId = playerId;
        this.confirm = confirm;
        this.inviter = inviter;
        this.comment = comment;
    }

    public int getId() {
        return id;
    }

    public int getFactionId() {
        return factionId;
    }

    public int getPlayerId() {
        return playerId;
    }

    public boolean isConfirm() {
        return confirm;
    }

    public String getInviter() {
        return inviter;
    }

    @Nullable
    public String getComment() {
        return comment;
    }

    @Override
    public String toString() {
        return "FactionInviteDto{" +
                "id=" + id +
                ", factionId=" + factionId +
                ", playerId=" + playerId +
                ", confirm=" + confirm +
                ", inviter='" + inviter + '\'' +
                ", comment='" + comment + '\'' +
                '}';
    }
}
