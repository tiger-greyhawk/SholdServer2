package ru.rsoft.configurator.core.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import ru.rsoft.configurator.core.entity.Player;
import ru.rsoft.configurator.core.repository.PlayerRepository;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Admin on 16.04.2016.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class PatternCreateDto {

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
    private final String typeCastle;

    @Nonnull
    private final int accessFrom;

    public PatternCreateDto() {
        this("", "", "", "", "", 0, "", 1000);
    }


    public PatternCreateDto(@Nonnull String name, @Nonnull String fileName, @Nonnull String file, @Nonnull String photoName, @Nonnull String photo, @Nonnull int playerId, String typeCastle, @Nonnull int accessFrom) {
        this.name = name;
        this.fileName = fileName;
        this.file = file;
        this.photoName = photoName;
        this.photo = photo;
        this.playerId = playerId;
        this.typeCastle = typeCastle;
        this.accessFrom = accessFrom;
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
                "name='" + name + '\'' +
                ", fileName='" + fileName + '\'' +
                ", file='" + file + '\'' +
                ", photoName='" + photoName + '\'' +
                ", photo='" + photo + '\'' +
                ", playerId=" + playerId + '\'' +
                ", typeCastle='" + typeCastle + '\'' +
                ", accessFrom=" + accessFrom +
                '}';
    }
}
