package ru.rsoft.shold.core.entity.requests;

import javax.annotation.Nonnull;
import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Date;

/**
 * Created by Admin on 24.06.2016.
 */
@Entity
@Table(name = "SEND_RESOURCES")
public class SendResources {

    @Id
    @Column(name = "ID")
    @GeneratedValue
    private int id;

    @Column(name = "RESOURCES_ID", nullable = false)
    @Nonnull
    private int resourcesId;

    @Column(name = "AMOUNT", nullable = false)
    @Nonnull
    private int amount;

    @Column(name = "PLAYER_ID", nullable = false)
    @Nonnull
    private Integer playerId;

    @Column(name = "TIMESTAMP", nullable = false)
    @Nonnull
    private Date timestamp;

    public SendResources() {
        this.id = 0;
        this.resourcesId = 0;
        this.amount = 0;
        this.playerId = 0;
        this.timestamp = new Timestamp(new Date().getTime());
    }

    public SendResources(int id, @Nonnull int resourcesId, @Nonnull int amount, @Nonnull Integer playerId, @Nonnull Date timestamp) {
        this.id = id;
        this.resourcesId = resourcesId;
        this.amount = amount;
        this.playerId = playerId;
        this.timestamp = timestamp;
    }

    public int getId() {
        return id;
    }

    @Nonnull
    public int getResourcesId() {
        return resourcesId;
    }

    @Nonnull
    public int getAmount() {
        return amount;
    }

    @Nonnull
    public Integer getPlayerId() {
        return playerId;
    }

    public void setPlayerId(@Nonnull Integer playerId) {
        this.playerId = playerId;
    }

    @Nonnull
    public Date getTimestamp() {
        return timestamp;
    }

    @Override
    public String toString() {
        return "SendResources{" +
                "id=" + id +
                ", resourcesId=" + resourcesId +
                ", amount=" + amount +
                ", playerId=" + playerId +
                ", timestamp=" + timestamp +
                '}';
    }
}
