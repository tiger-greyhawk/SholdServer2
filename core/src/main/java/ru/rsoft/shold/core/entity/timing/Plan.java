package ru.rsoft.shold.core.entity.timing;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.persistence.*;
import java.util.Date;

/**
 * Created by Admin on 17.07.2016.
 */

@Entity
@Table(name = "PLAN")
public class Plan {

    @Id
    @Column(name = "ID")
    @GeneratedValue
    private int id;

    @Column (name = "SECRET")  //  неотображаемый (???) ключ коллективной атаки
    @Nullable
    private String secret;

    @Column (name = "NAME", nullable = false)  // отображаемое название коллективной атаки
    @Nonnull
    private String name;

    @Column (name = "VIEWABLE", nullable = false)  // кому видно
    @Nonnull
    private String viewable;

    @Column (name = "PLAYER_ID", nullable = false)  // Кто начал планировать
    @Nonnull
    private int playerId;

    @Column (name = "WHEN_INIT", nullable = false)  // когда начали планировать
    @Nonnull
    private Date whenInit;

    @Column (name = "TYPE", nullable = true)  // цель удара (захват/снос/фейки/поджоги/грабежи)
    @Nullable
    private String type;

    @Column(name = "STARTED", nullable = false)
    @Nonnull
    private boolean started;

    @Column(name = "TIME_STARTED", nullable = false)
    @Nonnull
    private Date timeStarted;




    public Plan() {this(0, "", "name", "all", 0, new Date(), "capture", false, new Date());
    }

    public Plan(int id, String secret, @Nonnull String name, @Nonnull String viewable,
                @Nonnull int playerId, @Nonnull Date whenInit, String type,
                @Nonnull boolean started, @Nonnull Date timeStarted) {
        this.id = id;
        this.secret = secret;
        this.name = name;
        this.viewable = viewable;
        this.playerId = playerId;
        this.whenInit = whenInit;
        this.type = type;
        this.started = started;
        this.timeStarted = timeStarted;
    }

    public int getId() {
        return id;
    }

    @Nullable
    public String getSecret() {
        return secret;
    }

    @Nonnull
    public String getName() {
        return name;
    }

    @Nonnull
    public String getViewable() {
        return viewable;
    }

    @Nonnull
    public int getPlayerId() {
        return playerId;
    }

    @Nonnull
    public Date getWhenInit() {
        return whenInit;
    }

    @Nullable
    public String getType() {
        return type;
    }

    @Nonnull
    public boolean isStarted() {
        return started;
    }

    public void setStarted(@Nonnull boolean started) {
        this.started = started;
    }

    @Nonnull
    public Date getTimeStarted() {
        return timeStarted;
    }

    public void setTimeStarted(@Nonnull Date timeStarted) {
        this.timeStarted = timeStarted;
    }

    @Override
    public String toString() {
        return "Plan{" +
                "id=" + id +
                ", secret='" + secret + '\'' +
                ", name='" + name + '\'' +
                ", viewable='" + viewable + '\'' +
                ", playerId=" + playerId +
                ", whenInit=" + whenInit +
                ", type='" + type + '\'' +
                ", started=" + started +
                ", timeStarted=" + timeStarted +
                '}';
    }
}
