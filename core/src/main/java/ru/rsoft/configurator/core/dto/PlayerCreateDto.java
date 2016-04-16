package ru.rsoft.configurator.core.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

/**
 * Created by Admin on 26.11.2015.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class PlayerCreateDto {
    @Nonnull
    private final Integer userId;
    @Nonnull
    private final String nick;

    public PlayerCreateDto() {
        this(0, "");
    }

    public PlayerCreateDto(@Nonnull Integer userId, @Nonnull String nick) {
        this.userId = userId;
        this.nick = nick;
    }

    @Nonnull
    public Integer getUserId() {
        return userId;
    }

    @Nonnull
    public String getNick() {
        return nick;
    }

    @Override
    public String toString() {
        return "PlayerCreateDto{" +
                "userId=" + userId +
                ", nick='" + nick + '\'' +
                '}';
    }
}
