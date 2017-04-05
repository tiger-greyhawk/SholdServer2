package ru.rsoft.shold.core.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.annotation.Nonnull;
import javax.persistence.Column;
import javax.persistence.Lob;

/**
 * Created by Admin on 08.05.2016.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class PatternPhotoCreateDto {

    private Integer patternId;

    private String photoName;

    private String photo;

    public PatternPhotoCreateDto() {this(null, "", "");
    }

    public PatternPhotoCreateDto(Integer patternId, @Nonnull String photoName, @Nonnull String photo) {
        this.patternId = patternId;
        this.photoName = photoName;
        this.photo = photo;
    }

    public Integer getPatternId() {
        return patternId;
    }

    @Nonnull
    public String getPhotoName() {
        return photoName;
    }

    @Nonnull
    public String getPhoto() {
        return photo;
    }

    @Override
    public String toString() {
        return "PatternPhotoCreateDto{" +
                "patternId=" + patternId +
                ", photoName='" + photoName + '\'' +
                ", photo='" + photo + '\'' +
                '}';
    }
}
