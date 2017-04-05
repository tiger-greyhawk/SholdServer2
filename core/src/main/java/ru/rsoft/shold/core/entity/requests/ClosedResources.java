package ru.rsoft.shold.core.entity.requests;


import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Date;

/**
 * Created by Admin on 24.04.2016.
 */
@Entity
@Table(name = "CLOSED_RESOURCES")
public class ClosedResources {
    @Id
    @Column(name = "ID")
    @GeneratedValue
    private int id;

    @Column (name = "", nullable = false)
    @Nonnull
    private int worldId;

    @Column (name = "TYPE", nullable = false)
    @Nonnull
    private String type;

    @Column (name = "NAME", nullable = false)
    @Nonnull
    private String name;


    @Column(name = "VILLAGE_ID", nullable = false)
    @Nonnull
    private int villageId;

    @Column(name = "AMOUNT", nullable = false)
    @Nonnull
    private int amount;

    @Column(name = "ON_WAY")
    @Nullable
    private int onWay;

    @Column(name = "MAX_QUANTITY", nullable = false)
    @Nonnull
    private int maxQuantity;

    @Column(name = "PLAYER_ID", nullable = false)
    @Nonnull
    private int playerId;

    @Column(name = "TIMESTAMP", nullable = false)
    @Nonnull
    //private Timestamp timestamp;
    private Date timestamp;

    private ClosedResources() {
        this.id = 0;
        this.worldId = 0;
        this.type = "resources";
        this.name = "nothing";
        this.amount = 0;
        this.onWay = 0;
        this.maxQuantity = 300;
        this.playerId = 0;
        this.timestamp = new Timestamp(new Date().getTime());
    }
/*
    public Resources(@Nonnull int worldId, @Nonnull String type, @Nonnull String name, @Nonnull Integer villageId, int amount,
                     int maxQuantity, @Nonnull Integer playerId, @Nonnull Date timestamp) {
        this.id = null;
        this.worldId = worldId;
        this.type = type;
        this.name = name;
        this.villageId = villageId;
        this.amount = amount;
        this.maxQuantity = maxQuantity;
        this.playerId = playerId;
        this.timestamp = timestamp;
    }
*/


    public ClosedResources(int id, @Nonnull int worldId, @Nonnull String type, @Nonnull String name, @Nonnull int villageId, int amount, int onWay,
                           int maxQuantity, @Nonnull int playerId, @Nonnull Date timestamp) {
        this.id = id;
        this.worldId = worldId;
        this.type = type;
        this.name = name;
        this.villageId = villageId;
        this.amount = amount;
        this.onWay = onWay;
        this.maxQuantity = maxQuantity;
        this.playerId = playerId;
        this.timestamp = timestamp;
    }

    public int getId() {
        return id;
    }

    @Nonnull
    public int getWorldId() {
        return worldId;
    }

    @Nonnull
    public String getType() {
        return type;
    }

    @Nonnull
    public String getName() {
        return name;
    }

    @Nonnull
    public int getVillageId() {
        return villageId;
    }

    @Nonnull
    public int getAmount() {
        return amount;
    }

    public void setAmount(@Nonnull int amount) {
        this.amount = amount;
    }

    @Nullable
    public int getOnWay() {
        return onWay;
    }

    public void setOnWay(@Nullable int onWay) {
        this.onWay = onWay;
    }

    @Nonnull
    public int getMaxQuantity() {
        return maxQuantity;
    }

    @Nonnull
    public int getPlayerId() {
        return playerId;
    }

    @Nonnull
    public Date getTimestamp() {
        return timestamp;
    }

    @Override
    public String toString() {
        return "Resources{" +
                "id=" + id +
                ", worldId=" + worldId +
                ", type='" + type + '\'' +
                ", name='" + name + '\'' +
                ", villageId=" + villageId +
                ", amount=" + amount +
                ", onWay=" + onWay +
                ", maxQuantity=" + maxQuantity +
                ", playerId=" + playerId +
                ", timestamp=" + timestamp +
                '}';
    }
}
