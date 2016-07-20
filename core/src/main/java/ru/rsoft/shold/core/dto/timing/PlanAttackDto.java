package ru.rsoft.shold.core.dto.timing;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.Date;

/**
 * Created by Admin on 15.06.2016.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class PlanAttackDto {

    private final int id;
    @Nullable
    private  String secret;
    @Nullable
    private String type;
    @Nonnull
    private String name;
    @Nullable
    private int villageId;
    @Nonnull
    private long timeTo;
    @Nonnull
    private int card;
    @Nonnull
    private int playerId;
    @Nonnull
    private Date timestamp;
    private final Date currentTimestamp;

    public PlanAttackDto() {
        this(0, "", "", "", 0, 0L, 0, 0, (new java.util.Date()), (new java.util.Date()));
    }

    public PlanAttackDto(int id,String secret, String type, @Nonnull String name, int villageId, @Nonnull long timeTo,
                         @Nonnull int card, @Nonnull int playerId, @Nonnull Date timestamp, Date currentTimestamp) {
        this.id = id;
        this.secret = secret;
        this.type = type;
        this.name = name;
        this.villageId = villageId;
        this.timeTo = timeTo;
        this.card = card;
        this.playerId = playerId;
        this.timestamp = timestamp;
        this.currentTimestamp = currentTimestamp;
    }

    public int getId() {
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
    public int getVillageId() {
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
        return "PlanAttackDto{" +
                "id=" + id +
                ", secret='" + secret + '\'' +
                ", type='" + type + '\'' +
                ", name='" + name + '\'' +
                ", villageId=" + villageId +
                ", timeTo=" + timeTo +
                ", card=" + card +
                ", playerId=" + playerId +
                ", timestamp=" + timestamp +
                ", currentTimestamp=" + currentTimestamp +
                '}';
    }
}
