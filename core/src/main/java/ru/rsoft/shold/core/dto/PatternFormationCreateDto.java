package ru.rsoft.shold.core.dto;

import javax.annotation.Nonnull;

/**
 * Created by Admin on 09.05.2016.
 */
public class PatternFormationCreateDto {

    private Integer patternId;

    @Nonnull
    private String fileName;

    @Nonnull
    private String file;

    public PatternFormationCreateDto() {this(null, "", "");
    }

    public PatternFormationCreateDto(Integer patternId, @Nonnull String fileName, @Nonnull String file) {
        this.patternId = patternId;
        this.fileName = fileName;
        this.file = file;
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
        return "PatternFormationCreateDto{" +
                "patternId=" + patternId +
                ", fileName='" + fileName + '\'' +
                ", file='" + file + '\'' +
                '}';
    }
}
