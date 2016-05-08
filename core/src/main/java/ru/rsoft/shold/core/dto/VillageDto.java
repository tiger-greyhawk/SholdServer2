package ru.rsoft.shold.core.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

/**
 * Created by Admin on 22.11.2015.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class VillageDto {
    private final int id;
//    private final int userId;
    @Nullable
    private final Integer userId;
    @Nullable
    private final String name;
    @Nonnull
    private final Integer id_in_world;

    public VillageDto() {
        this(0, 0, "", 0);
    }

    public VillageDto(int id, @Nullable Integer userId, @Nullable String name,
                       @Nonnull Integer id_in_world) {
        this.id = id;
        this.userId = userId;
        this.name = name;
        this.id_in_world = id_in_world;
    }

    public int getId() {
        return id;
    }

    @Nullable
    public Integer getUserId() {
        return userId;
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
        return "VillageDto{" +
                "id=" + id +
                ", UserId=" + userId +
                ", name=" + name +
                ", id in world='" + id_in_world + '\'' +
                '}';
    }
}