package ru.rsoft.shold.core.entity;

import javax.annotation.Nullable;
import javax.persistence.*;

/**
 * Created by Admin on 30.12.2016.
 */
@Entity
@Table(name = "FactionPlayer")
public class FactionPlayer {

    @Id
    @Column(name = "Id")
    @GeneratedValue
    private final int id;

    @Column(name = "Player_Id", nullable = false)
    private final int playerId;

    @Column(name = "Faction_Id", nullable = false)
    private final int factionId;

    @Column(name = "Confirm")
    private final boolean confirm;

    @Column(name = "Officer")
    private boolean officer;

    @Column(name = "Comment")
    @Nullable
    private final String comment;

    public FactionPlayer() {this(0, 0, 0, false, false, "");
    }

    public FactionPlayer(int id, int playerId, int factionId, boolean confirm, boolean officer, String comment) {
        this.id = id;
        this.playerId = playerId;
        this.factionId = factionId;
        this.confirm = confirm;
        this.officer = officer;
        this.comment = comment;
    }

    public int getId() {
        return id;
    }

    public int getPlayerId() {
        return playerId;
    }

    public int getFactionId() {
        return factionId;
    }

    public boolean isConfirm() {
        return confirm;
    }

    public boolean isOfficer() {
        return officer;
    }

    public void setOfficer(boolean officer) {
        this.officer = officer;
    }

    @Nullable
    public String getComment() {
        return comment;
    }

    @Override
    public String toString() {
        return "FactionPlayer{" +
                "id=" + id +
                ", playerId=" + playerId +
                ", factionId=" + factionId +
                ", confirm=" + confirm +
                ", officer=" + officer +
                ", comment='" + comment + '\'' +
                '}';
    }
}