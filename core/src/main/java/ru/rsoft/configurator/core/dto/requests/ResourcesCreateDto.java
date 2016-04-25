package ru.rsoft.configurator.core.dto.requests;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.annotation.Nonnull;
import java.sql.Timestamp;
import java.util.Date;

/**
 * Created by Admin on 24.04.2016.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class ResourcesCreateDto {

    @Nonnull
    private final String type;
    @Nonnull
    private final String name;
    @Nonnull
    private final int villageId;
    @Nonnull
    private final int amount;
    @Nonnull
    private final int max_quantum;
    @Nonnull
    private final int playerId;
    @Nonnull
    //private final Timestamp timestamp;
    private final Date timestamp;

    public ResourcesCreateDto() {
        //this("", "", 0, 0, 0, 0, new Timestamp(new java.util.Date().getTime()));
        this("", "", 0, 0, 0, 0, (new java.util.Date()));
    }

    public ResourcesCreateDto(@Nonnull String type, @Nonnull String name, @Nonnull int villageId, @Nonnull int amount, @Nonnull int max_quantum, @Nonnull int playerId, @Nonnull Date timestamp) {
        this.type = type;
        this.name = name;
        this.villageId = villageId;
        this.amount = amount;
        this.max_quantum = max_quantum;
        this.playerId = playerId;
        this.timestamp = timestamp;
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

    @Nonnull
    public int getMax_quantum() {
        return max_quantum;
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
        return "ResourcesCreateDto{" +
                "type='" + type + '\'' +
                ", name='" + name + '\'' +
                ", villageId=" + villageId +
                ", amount=" + amount +
                ", max_quantum=" + max_quantum +
                ", playerId=" + playerId +
                ", timestamp=" + timestamp +
                '}';
    }
}
