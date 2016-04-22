package ru.rsoft.configurator.core.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import ru.rsoft.configurator.core.entity.Player;

import javax.annotation.Nonnull;
import javax.persistence.*;

/**
 * Created by Admin on 16.04.2016.
 */

@JsonIgnoreProperties(ignoreUnknown = true)
public class PatternDto {

    @Id
    private final int id;

    @Nonnull
    private final String name;

    @Nonnull
    private final String fileName;

    @Nonnull
    private final String file;

    @Nonnull
    private final String photoName;

    @Nonnull
    private final String photo;

    @Nonnull
    private final int playerId;

    @Nonnull
    private final int accessFrom;

    private PatternDto() {
        this(1, "", "", "", "", "", 0, 1000);
    }

    public PatternDto(int id, @Nonnull String name, @Nonnull String fileName, @Nonnull String file, @Nonnull String photoName, @Nonnull String photo, @Nonnull int playerId, @Nonnull int accessFrom) {
        this.id = id;
        this.name = name;
        this.fileName = fileName;
        this.file = file;
        this.photoName = photoName;
        this.photo = photo;
        this.playerId = playerId;
        this.accessFrom = accessFrom;
    }

    public int getId() {
        return id;
    }

    @Nonnull
    public String getName() {
        return name;
    }

    @Nonnull
    public String getFileName() {
        return fileName;
    }

    @Nonnull
    public String getFile() {
        return file;
    }

    @Nonnull
    public String getPhotoName() {
        return photoName;
    }

    @Nonnull
    public String getPhoto() {
        return photo;
    }

    @Nonnull
    public int getPlayerId() {
        return playerId;
    }

    @Nonnull
    public int getAccessFrom() {
        return accessFrom;
    }

    @Override
    public String toString() {
        return "PatternDto{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", fileName='" + fileName + '\'' +
                ", file='" + file + '\'' +
                ", photoName='" + photoName + '\'' +
                ", photo='" + photo + '\'' +
                ", player=" + playerId +
                ", accessFrom=" + accessFrom +
                '}';
    }
}