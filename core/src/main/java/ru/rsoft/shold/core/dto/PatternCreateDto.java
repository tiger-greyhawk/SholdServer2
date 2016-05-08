package ru.rsoft.shold.core.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.annotation.Nonnull;

/**
 * Created by Admin on 16.04.2016.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class PatternCreateDto {

    @Nonnull
    private final Integer playerId;

    @Nonnull
    private final String name;

    @Nonnull
    private final String typeCastle;

    @Nonnull
    private final Integer accessFrom;

    public PatternCreateDto() {
        this(0, "", "", 0000);
    }


    public PatternCreateDto(@Nonnull int playerId, @Nonnull String name, String typeCastle, @Nonnull int accessFrom) {
        this.name = name;
        this.playerId = playerId;
        this.typeCastle = typeCastle;
        this.accessFrom = accessFrom;
    }


    @Nonnull
    public Integer getPlayerId() {
        return playerId;
    }

    @Nonnull
    public String getName() {
        return name;
    }

    @Nonnull
    public String getTypeCastle() {
        return typeCastle;
    }

    @Nonnull
    public int getAccessFrom() {
        return accessFrom;
    }

    @Override
    public String toString() {
        return "PatternCreateDto{" +
                "playerId=" + playerId +
                ", name='" + name + '\'' +
                ", typeCastle='" + typeCastle + '\'' +
                ", accessFrom=" + accessFrom +
                '}';
    }
}
