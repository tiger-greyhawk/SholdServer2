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
    private final Integer id_in_world;

    public VillageCreateDto() {
        this(0, "", 0);
    }

    public VillageCreateDto(@Nullable Integer playerId, @Nullable String name,
                             @Nonnull Integer id_in_world) {
        this.playerId = playerId;
        this.name = name;
        this.id_in_world = id_in_world;
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
        return id_in_world;
    }

    @Override
    public String toString() {
        return "VillageCreateDto{" +
                "playerId=" + playerId +
                ", name=" + name +
                ", id in world='" + id_in_world + '\'' +
                '}';
    }
}
