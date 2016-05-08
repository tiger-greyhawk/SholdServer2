package ru.rsoft.shold.core.dto;

import javax.annotation.Nonnull;
import javax.persistence.Column;
import javax.persistence.Lob;

/**
 * Created by Admin on 08.05.2016.
 */
public class PatternPhotoCreateDto {

    @Column(name = "PATTERN_ID", nullable = false)
    private Integer patternId;

    @Column (name = "PHOTONAME", nullable = false)
    @Nonnull
    private String photoName;

    @Lob
    @Column (name = "PHOTO", nullable = false)
    @Nonnull
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
