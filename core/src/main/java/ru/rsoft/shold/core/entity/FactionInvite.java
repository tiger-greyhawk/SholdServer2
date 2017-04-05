package ru.rsoft.shold.core.entity;

import javax.annotation.Nullable;
import javax.persistence.*;

/**
 * Created by Admin on 15.12.2016.
 */

@Entity
@Table(name = "FACTION_INVITE")  //  приглашение игроку вступить во фракцию
public class FactionInvite {

    @Id
    @GeneratedValue(strategy =  GenerationType.IDENTITY)
    @Column(name = "ID")
    private int id;

    @Column(name = "FACTION_ID")
    private int factionId;

    @Column(name = "PLAYER_ID")
    private int playerId;

    @Column (name = "CONFIRM")
    private boolean confirm;

    @Column (name = "INVITER")
    private String inviter;  // кто пригласил или свой ник, если подача заявки

    @Column (name = "COMMENT")
    @Nullable
    private String comment;

    public FactionInvite() {
        this(0, 0, false, "", "");
    }

    public FactionInvite(int factionId, int playerId, boolean confirm, String inviter, String comment) {
        //this.id = id;
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
        return "FactionInvite{" +
                "id=" + id +
                ", factionId=" + factionId +
                ", playerId=" + playerId +
                ", confirm=" + confirm +
                ", inviter='" + inviter + '\'' +
                ", comment='" + comment + '\'' +
                '}';
    }
}
