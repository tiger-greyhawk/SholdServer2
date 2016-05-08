package ru.rsoft.shold.core.entity;

import javax.annotation.Nonnull;
import javax.persistence.*;

/**
 * Created by Admin on 08.05.2016.
 */

@Entity
@Table(name = "PATTERN_PHOTO")
public class PatternPhoto {

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

    public PatternPhoto() {this(null,null,"","");
    }

    public PatternPhoto(Integer id, Integer patternId, @Nonnull String photoName, @Nonnull String photo) {
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
        return "PatternPhoto{" +
                "id=" + id +
                ", patternId=" + patternId +
                ", photoName='" + photoName + '\'' +
                ", photo='" + photo + '\'' +
                '}';
    }
}
