package ru.rsoft.shold.core.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

/**
 * Created by Admin on 22.11.2015.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class VillageCreateDto {

    @Nullable
    private final Integer playerId;
    @Nullable
    private final String name;
    @Nonnull
    private final Integer idInWorld;

    public VillageCreateDto() {
        this(0, "", 0);
    }

    public VillageCreateDto(@Nullable Integer playerId, @Nullable String name,
                             @Nonnull Integer idInWorld) {
        this.playerId = playerId;
        this.name = name;
        this.idInWorld = idInWorld;
    }

    @Nullable
    public Integer getPlayerId() {
        return playerId;
    }



    @Nullable
    public String getName() {
        return name;
    }

    @Nonnull
    public Integer getIdInWorld() {
        return idInWorld;
    }

    @Override
    public String toString() {
        return "VillageCreateDto{" +
                "playerId=" + playerId +
                ", name=" + name +
                ", idInWorld='" + idInWorld + '\'' +
                '}';
    }
}
