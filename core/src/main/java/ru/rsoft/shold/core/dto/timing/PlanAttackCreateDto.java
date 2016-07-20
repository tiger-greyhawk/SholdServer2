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
public class PlanAttackCreateDto {

    @Nullable
    private String secret;
    @Nullable
    private String type;

    @Nonnull
    private String name;

    @Nullable
    private Integer villageId;

    @Nonnull
    private long timeTo;

    @Nonnull
    private int card;

    @Nonnull
    private Integer playerId;

    @Nonnull
    private Date timestamp;

    public PlanAttackCreateDto() {
        this("", "", "", 0, 100000000000L, 0, 0, (new java.util.Date()));
    }

    public PlanAttackCreateDto(String secret, String type, @Nonnull String name, Integer villageId,
                               @Nonnull long timeTo, @Nonnull int card, @Nonnull Integer playerId, @Nonnull Date timestamp) {
        this.secret = secret;
        this.type = type;
        this.name = name;
        this.villageId = villageId;
        this.timeTo = timeTo;
        this.card = card;
        this.playerId = playerId;
        this.timestamp = timestamp;
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
        return "PlanAttackCreateDto{" +
                "secret='" + secret + '\'' +
                "type='" + type + '\'' +
                ", name='" + name + '\'' +
                ", villageId=" + villageId +
                ", timeTo=" + timeTo +
                ", card=" + card +
                ", playerId=" + playerId +
                ", timestamp=" + timestamp +
                '}';
    }
}
