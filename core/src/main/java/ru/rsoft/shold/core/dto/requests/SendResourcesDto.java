package ru.rsoft.shold.core.dto.requests;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.annotation.Nonnull;
import java.util.Date;

/**
 * Created by Admin on 24.06.2016.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class SendResourcesDto {

    private final int id;
    @Nonnull
    private final int resourceId;
    @Nonnull
    private final int amount;
    @Nonnull
    private final int playerId;
    @Nonnull
    private final Date timestamp;
    private final Date currentTimestamp;

    public SendResourcesDto() {this(0, 0, 0, 0, (new java.util.Date()), (new java.util.Date()));
    }

    public SendResourcesDto(int id, @Nonnull int resourceId, @Nonnull int amount, @Nonnull int playerId, @Nonnull Date timestamp, Date currentTimestamp) {
        this.id = id;
        this.resourceId = resourceId;
        this.amount = amount;
        this.playerId = playerId;
        this.timestamp = timestamp;
        this.currentTimestamp = currentTimestamp;
    }

    public int getId() {
        return id;
    }

    @Nonnull
    public int getResourceId() {
        return resourceId;
    }

    @Nonnull
    public int getAmount() {
        return amount;
    }

    @Nonnull
    public int getPlayerId() {
        return playerId;
    }

    @Nonnull
    public Date getTimestamp() {
        return timestamp;
    }

    public Date getCurrentTimestamp() {
        return currentTimestamp;
    }

    @Override
    public String toString() {
        return "SendResourcesDto{" +
                "id=" + id +
                ", resourceId=" + resourceId +
                ", amount=" + amount +
                ", playerId=" + playerId +
                ", timestamp=" + timestamp +
                ", currentTimestamp=" + currentTimestamp +
                '}';
    }
}
