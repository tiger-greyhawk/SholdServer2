package ru.rsoft.shold.core.entity.timing;

import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Date;
/**
 * Created by Admin on 15.06.2016.
 */
@Entity
@Table(name = "PLANATTACK")
public class PlanAttack {

    @Id
    @Column(name = "ID")
    @GeneratedValue
    private Integer id;

    @Column (name = "SECRET")
    @Nullable
    private String secret;

    @Column (name = "TYPE", nullable = true)
    @Nullable
    private String type;

    @Column (name = "NAME", nullable = false)
    @Nonnull
    private String name;


    @Column(name = "VILLAGE_ID", nullable = true)
    @Nullable
    private int villageId;

    @Column(name = "TIMETO", nullable = false)
    @Nonnull
    private long timeTo;

    @Column(name = "CARD", nullable = false)
    @Nonnull
    private int card;

    @Column(name = "PLAYER_ID", nullable = false)
    @Nonnull
    private int playerId;

    @Column(name = "TIMESTAMP", nullable = false)
    @Nonnull
    //private Timestamp timestamp;
    private Date timestamp;

    public PlanAttack() {
    }

    public PlanAttack(String secret,String type, @Nonnull String name, Integer villageId, @Nonnull long timeTo,
                      @Nonnull int card, @Nonnull Integer playerId, @Nonnull Date timestamp) {
        this.secret = secret;
        this.type = type;
        this.name = name;
        this.villageId = villageId;
        this.timeTo = timeTo;
        this.card = card;
        this.playerId = playerId;
        this.timestamp = timestamp;
    }

    public PlanAttack(Integer id, String secret, String type, @Nonnull String name, Integer villageId, @Nonnull long timeTo,
                      @Nonnull int card, @Nonnull Integer playerId, @Nonnull Date timestamp) {
        this.id = id;
        this.secret = secret;
        this.type = type;
        this.name = name;
        this.villageId = villageId;
        this.timeTo = timeTo;
        this.card = card;
        this.playerId = playerId;
        this.timestamp = timestamp;
    }

    public Integer getId() {
        return id;
    }

    @Nullable
    public String getSecret() {
        return secret;
    }

    @Nullable
    public String getType() {
        return type;
    }

    @Nonnull
    public String getName() {
        return name;
    }

    @Nullable
    public Integer getVillageId() {
        return villageId;
    }

    @Nonnull
    public long getTimeTo() {
        return timeTo;
    }

    @Nonnull
    public int getCard() {
        return card;
    }

    @Nonnull
    public Integer getPlayerId() {
        return playerId;
    }

    @Nonnull
    public Date getTimestamp() {
        return timestamp;
    }

    @Override
    public String toString() {
        return "PlanAttack{" +
                "id=" + id +
                ", secret='" + secret + '\'' +
                ", type='" + type + '\'' +
                ", name='" + name + '\'' +
                ", villageId=" + villageId +
                ", timeTo=" + timeTo +
                ", card=" + card +
                ", playerId=" + playerId +
                ", timestamp=" + timestamp +
                '}';
    }
}
