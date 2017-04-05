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
    private int id;

    @Column(name = "PLAN_ID")
    @Nonnull
    private Integer planId;

    @Column (name = "NAME", nullable = false)
    @Nonnull
    private String name;

    @Column (name = "SECRET")
    @Nullable
    private String secret;

    @Column(name = "PLAYER_ID", nullable = false)  // игрок
    @Nonnull
    private int playerId;

    @Column(name = "VILLAGE_ID", nullable = true) // дера
    @Nullable
    private int villageId;

    @Column(name = "VASSAL_ID", nullable = true) // вассал
    @Nullable
    private int vassalId;

    @Column (name = "TYPE", nullable = true) // состав ударки/кэпки
    @Nullable
    private String type;

    @Column(name = "TIMETO", nullable = false) // время
    @Nonnull
    private long timeTo;

    @Column(name = "CARD", nullable = false) // карта
    @Nonnull
    private int card;

    @Column(name = "READY_IN", nullable = false) // готовность
    @Nonnull
    private long readyIn;

    @Column(name = "ORDER_IN", nullable = true) // очередность входа
    @Nullable
    private int orderIn;

    @Column(name = "TIMESTAMP", nullable = false)
    @Nonnull
    //private Timestamp timestamp;
    private Date timestamp;

    public PlanAttack() {
    }

    public PlanAttack(int planId, String name, String secret, @Nonnull int playerId, int villageId, int vassalId, String type, @Nonnull long timeTo,
                      @Nonnull int card, long readyIn, int orderIn, @Nonnull Date timestamp) {
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
    }

    public PlanAttack(int id, int planId, String name, String secret, @Nonnull int playerId, int villageId, int vassalId, String type, @Nonnull long timeTo,
                      @Nonnull int card, long readyIn, int orderIn, @Nonnull Date timestamp) {
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
    }

    public int getId() {
        return id;
    }

    @Nonnull
    public Integer getPlanId() {
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

    @Override
    public String toString() {
        return "PlanAttack{" +
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
                '}';
    }
}
