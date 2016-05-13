package ru.rsoft.shold.core.dto;

import javax.annotation.Nonnull;

/**
 * Created by Admin on 09.05.2016.
 */
public class PatternFormationDto {

    private Integer id;

    private Integer patternId;

    @Nonnull
    private String fileName;

    @Nonnull
    private String file;

    public PatternFormationDto() { this(null, null,"","");
    }

    public PatternFormationDto(Integer id, Integer patternId, @Nonnull String fileName, @Nonnull String file) {
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
        return "PatternFormationDto{" +
                "id=" + id +
                ", patternId=" + patternId +
                ", fileName='" + fileName + '\'' +
                ", file='" + file + '\'' +
                '}';
    }
}
