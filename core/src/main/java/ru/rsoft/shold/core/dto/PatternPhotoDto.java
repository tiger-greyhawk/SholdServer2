package ru.rsoft.shold.core.dto;

import javax.annotation.Nonnull;
import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Lob;

/**
 * Created by Admin on 08.05.2016.
 */
public class PatternPhotoDto {

    @Id
    @Column(name = "ID")
    @GeneratedValue
    private Integer id;

    @Column(name = "PATTERN_ID", nullable = false)
    private Integer patternId;

    @Column (name = "PHOTONAME", nullable = false)
    @Nonnull
    private String photoName;

    @Lob
    @Column (name = "PHOTO", nullable = false)
    @Nonnull
    private String photo;

    public PatternPhotoDto() {this(null,null,"","");
    }

    public PatternPhotoDto(Integer id, Integer patternId, @Nonnull String photoName, @Nonnull String photo) {
        this.id = id;
        this.patternId = patternId;
        this.photoName = photoName;
        this.photo = photo;
    }

    public Integer getId() {
        return id;
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
        return "PatternPhotoDto{" +
                "id=" + id +
                ", patternId=" + patternId +
                ", photoName='" + photoName + '\'' +
                ", photo='" + photo + '\'' +
                '}';
    }
}
