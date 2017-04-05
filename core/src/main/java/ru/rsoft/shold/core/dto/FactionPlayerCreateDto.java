package ru.rsoft.shold.core.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

/**
 * Created by Admin on 30.12.2016.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class FactionPlayerCreateDto {

    @Nonnull
    private final int playerId;

    @Nonnull
    private final int factionId;

    private final boolean confirm;

    private final boolean officer;

    @Nullable
    private final String comment;

    public FactionPlayerCreateDto() { this(0,0,false,false,"");
    }

    public FactionPlayerCreateDto(@Nonnull int playerId, @Nonnull int factionId, boolean confirm, boolean officer, String comment) {
        this.playerId = playerId;
        this.factionId = factionId;
        this.confirm = confirm;
        this.officer = officer;
        this.comment = comment;
    }

    @Nonnull
    public int getPlayerId() {
        return playerId;
    }

    @Nonnull
    public int getFactionId() {
        return factionId;
    }

    public boolean isConfirm() {
        return confirm;
    }

    public boolean isOfficer() {
        return officer;
    }

    @Nullable
    public String getComment() {
        return comment;
    }

    @Override
    public String toString() {
        return "FactionPlayerCreateDto{" +
                "playerId=" + playerId +
                ", factionId=" + factionId +
                ", confirm=" + confirm +
                ", officer=" + officer +
                ", comment='" + comment + '\'' +
                '}';
    }
}
