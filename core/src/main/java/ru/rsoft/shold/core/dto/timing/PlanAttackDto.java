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

    private int id;
    @Nonnull
    private int planId;
    @Nonnull
    private String name;
    @Nullable
    private String secret;
    @Nonnull
    private int playerId;
    @Nullable
    private int villageId;
    @Nullable
    private int vassalId;
    @Nullable
    private String type;
    @Nonnull
    private long timeTo;
    @Nonnull
    private int card;
    @Nonnull
    private long readyIn;
    @Nullable
    private int orderIn;
    @Nonnull
    private Date timestamp;
    private final Date currentTimestamp;

    public PlanAttackDto() {
        this(0, 0, "", "", 0, 0, 0, "", 0L, 1, 0L, 0, (new java.util.Date()), (new java.util.Date()));
    }

    public PlanAttackDto(int id, int planId, String name, String secret, int playerId,
                         int villageId, int vassalId, String type,  @Nonnull long timeTo,
                         @Nonnull int card, @Nonnull long readyIn, int orderIn,
                         @Nonnull Date timestamp, Date currentTimestamp) {
        this.id = id;
        this.planId = planId;
        this.name = name;
        this.secret = secret;
        this.playerId = playerId;
        this.villageId = villageId;
        this.vassalId = vassalId;
        this.type = type;
        this.timeTo = timeTo;
        this.card = card;
        this.readyIn = readyIn;
        this.orderIn = orderIn;
        this.timestamp = timestamp;
        this.currentTimestamp = currentTimestamp;
    }

    public int getId() {
        return id;
    }

    @Nonnull
    public int getPlanId() {
        return planId;
    }

    @Nonnull
    public String getName() {
        return name;
    }

    @Nullable
    public String getSecret() {
        return secret;
    }

    @Nonnull
    public int getPlayerId() {
        return playerId;
    }

    @Nullable
    public int getVillageId() {
        return villageId;
    }

    @Nullable
    public int getVassalId() {
        return vassalId;
    }

    @Nullable
    public String getType() {
        return type;
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
    public long getReadyIn() {
        return readyIn;
    }

    @Nullable
    public int getOrderIn() {
        return orderIn;
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
                ", planId=" + planId +
                ", name='" + name + '\'' +
                ", secret='" + secret + '\'' +
                ", playerId=" + playerId +
                ", villageId=" + villageId +
                ", vassalId=" + vassalId +
                ", type='" + type + '\'' +
                ", timeTo=" + timeTo +
                ", card=" + card +
                ", readyIn=" + readyIn +
                ", orderIn=" + orderIn +
                ", timestamp=" + timestamp +
                ", currentTimestamp=" + currentTimestamp +
                '}';
    }
}
