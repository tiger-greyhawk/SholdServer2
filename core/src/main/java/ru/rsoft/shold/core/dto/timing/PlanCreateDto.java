package ru.rsoft.shold.core.dto.timing;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.Date;

/**
 * Created by Admin on 01.08.2016.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class PlanCreateDto {

    @Nullable
    private String secret;

    @Nonnull
    private String name;

    @Nonnull
    private String viewable;

    @Nonnull
    private int playerId;

    @Nonnull
    private Date whenInit;

    @Nullable
    private String type;

    @Nonnull
    private boolean started;

    @Nonnull
    private Date timeStarted;

    public PlanCreateDto() {
    }

    public PlanCreateDto(String secret, @Nonnull String name,
                         @Nonnull String viewable, @Nonnull int playerId,
                         @Nonnull Date whenInit, String type,
                         @Nonnull boolean started, @Nonnull Date timeStarted) {
        this.secret = secret;
        this.name = name;
        this.viewable = viewable;
        this.playerId = playerId;
        this.whenInit = whenInit;
        this.type = type;
        this.started = started;
        this.timeStarted = timeStarted;
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

    @Nonnull
    public Date getTimeStarted() {
        return timeStarted;
    }

    @Override
    public String toString() {
        return "PlanCreateDto{" +
                "secret='" + secret + '\'' +
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
