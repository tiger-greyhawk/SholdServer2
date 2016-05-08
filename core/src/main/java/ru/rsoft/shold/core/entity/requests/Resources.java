package ru.rsoft.shold.core.entity.requests;


import javax.annotation.Nonnull;
import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Date;

/**
 * Created by Admin on 24.04.2016.
 */
@Entity
@Table(name = "RESOURCES")
public class Resources {
    @Id
    @Column(name = "ID")
    @GeneratedValue
    private Integer id;

    @Column (name = "TYPE", nullable = false)
    @Nonnull
    private String type;

    @Column (name = "NAME", nullable = false)
    @Nonnull
    private String name;


    @Column(name = "VILLAGE_ID", nullable = false)
    @Nonnull
    private Integer villageId;

    @Column(name = "AMOUNT", nullable = false)
    @Nonnull
    private int amount;

    @Column(name = "MAX_QUANTITY", nullable = false)
    @Nonnull
    private int maxQuantity;

    @Column(name = "PLAYER_ID", nullable = false)
    @Nonnull
    private Integer playerId;

    @Column(name = "TIMESTAMP", nullable = false)
    @Nonnull
    //private Timestamp timestamp;
    private Date timestamp;

    private Resources() {
        this.id = null;
        this.type = "resources";
        this.name = "nothing";
        this.amount = 0;
        this.maxQuantity = 300;
        this.playerId = 0;
        this.timestamp = new Timestamp(new Date().getTime());
    }

    public Resources(@Nonnull String type, @Nonnull String name, @Nonnull Integer villageId, int amount,
                     int maxQuantity, @Nonnull Integer playerId, @Nonnull Date timestamp) {
        this.id = null;
        this.type = type;
        this.name = name;
        this.villageId = villageId;
        this.amount = amount;
        this.maxQuantity = maxQuantity;
        this.playerId = playerId;
        this.timestamp = timestamp;
    }

    public Resources(Integer id, @Nonnull String type, @Nonnull String name, @Nonnull Integer villageId, int amount,
                     int maxQuantity, @Nonnull Integer playerId, @Nonnull Date timestamp) {
        this.id = id;
        this.type = type;
        this.name = name;
        this.villageId = villageId;
        this.amount = amount;
        this.maxQuantity = maxQuantity;
        this.playerId = playerId;
        this.timestamp = timestamp;
    }

    public int getId() {
        return id;
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
    public Integer getVillageId() {
        return villageId;
    }

    @Nonnull
    public int getAmount() {
        return amount;
    }

    @Nonnull
    public int getMaxQuantity() {
        return maxQuantity;
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
        return "Resources{" +
                "id=" + id +
                ", type='" + type + '\'' +
                ", name='" + name + '\'' +
                ", villageId=" + villageId +
                ", amount=" + amount +
                ", maxQuantity=" + maxQuantity +
                ", playerId=" + playerId +
                ", timestamp=" + timestamp +
                '}';
    }
}
