package ru.rsoft.shold.core.dto.requests;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.annotation.Nonnull;
import java.sql.Timestamp;
import java.util.Date;

/**
 * Created by Admin on 24.06.2016.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class SendResourcesCreateDto {

    @Nonnull
    private final int resourceId;
    @Nonnull
    private final int amount;
    @Nonnull
    private final int playerId;
    @Nonnull
    private final Date timestamp;
    private final Date currentTimestamp;

    public SendResourcesCreateDto()
    {
        this(0, 0, 0, (new java.util.Date()));//, (new java.util.Date()));
    }

    public SendResourcesCreateDto(@Nonnull int resourceId, @Nonnull int amount, @Nonnull int playerId, @Nonnull Date timestamp){//, Date currentTimestamp) {
        this.resourceId = resourceId;
        this.amount = amount;
        this.playerId = playerId;
        this.timestamp = timestamp;
        //this.currentTimestamp = currentTimestamp;
        this.currentTimestamp = new Timestamp(new Date().getTime());
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
        return "SendResourcesCreateDto{" +
                "resourceId=" + resourceId +
                ", amount=" + amount +
                ", playerId=" + playerId +
                ", timestamp=" + timestamp +
                ", currentTimestamp=" + currentTimestamp +
                '}';
    }
}
