package ru.rsoft.configurator.core.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

/**
 * Created by Admin on 26.11.2015.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class PlayerDto {
    private final Integer id;
    //    private final int userId;
    @Nonnull
    private final Integer userId;
    @Nonnull
    private final String nick;


    public PlayerDto() {
        this(null, null, "");
    }

    public PlayerDto(Integer id, @Nonnull Integer userId, @Nonnull String nick) {
        this.id = id;
        this.userId = userId;
        this.nick = nick;
    }

    public Integer getId() {
        return id;
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
        return "PlayerDto{" +
                "id=" + id +
                ", userId=" + userId +
                ", nick='" + nick + '\'' +
                '}';
    }
}
