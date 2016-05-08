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
    private int id;

    @Column(name = "PATTERN_ID", nullable = false)
    private int patternId;

    @Column (name = "FILENAME", nullable = false)
    @Nonnull
    private String fileName;

    @Lob
    @Column (name = "FILE", nullable = false)
    @Nonnull
    private String file;

    public PatternFormation() {this(0,0,"","");
    }

    public PatternFormation(int id, int patternId, @Nonnull String fileName, @Nonnull String file) {
        this.id = id;
        this.patternId = patternId;
        this.fileName = fileName;
        this.file = file;
    }

    public int getId() {
        return id;
    }

    public int getPatternId() {
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
