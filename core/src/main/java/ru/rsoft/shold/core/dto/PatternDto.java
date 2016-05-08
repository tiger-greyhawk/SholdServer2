package ru.rsoft.shold.core.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.annotation.Nonnull;
import javax.persistence.Id;

/**
 * Created by Admin on 16.04.2016.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class PatternDto {

    @Id
    private final Integer id;

    @Nonnull
    private final Integer playerId;

    @Nonnull
    private final String name;

    @Nonnull
    private final String typeCastle;

    @Nonnull
    private final Integer accessFrom;

    private PatternDto() {
        this(null, null, "", "", 0000);
    }

/*
    public PatternDto(int id, String name, String typeCastle, int playerId) {
        this(id, name, "", "", "", "", playerId, typeCastle, 1000);
    }
*/
    public PatternDto(Integer id, @Nonnull Integer playerId, @Nonnull String name, @Nonnull String typeCastle, @Nonnull int accessFrom) {
        this.id = id;
        this.playerId = playerId;
        this.name = name;
        this.typeCastle = typeCastle;
        this.accessFrom = accessFrom;
    }

    public Integer getId() {
        return id;
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
        return "PatternDto{" +
                "id=" + id +
                ", playerId=" + playerId +
                ", name='" + name + '\'' +
                ", typeCastle='" + typeCastle + '\'' +
                ", accessFrom=" + accessFrom +
                '}';
    }
}