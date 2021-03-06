package ru.rsoft.shold.core.entity;

import javax.annotation.Nonnull;
import javax.persistence.*;

/**
 * Created by Admin on 08.05.2016.
 */
@Entity
@Table(name = "PATTERN_FORMATION")
public class PatternFormation {

    @Id
    @Column(name = "ID")
    @GeneratedValue
    private Integer id;

    @Column(name = "PATTERN_ID", nullable = false)
    private Integer patternId;

    @Column (name = "FILENAME", nullable = false)
    @Nonnull
    private String fileName;

    @Lob
    @Column (name = "FILE", nullable = false)
    @Nonnull
    private String file;

    public PatternFormation() {this(null,null,"","");
    }

    public PatternFormation(Integer id, Integer patternId, @Nonnull String fileName, @Nonnull String file) {
        this.id = id;
        this.patternId = patternId;
        this.fileName = fileName;
        this.file = file;
    }

    public Integer getId() {
        return id;
    }

    public Integer getPatternId() {
        return patternId;
    }

    @Nonnull
    public String getFileName() {
        return fileName;
    }

    @Nonnull
    public String getFile() {
        return file;
    }

    @Override
    public String toString() {
        return "PatternFormation{" +
                "id=" + id +
                ", patternId=" + patternId +
                ", fileName='" + fileName + '\'' +
                ", file='" + file + '\'' +
                '}';
    }
}
