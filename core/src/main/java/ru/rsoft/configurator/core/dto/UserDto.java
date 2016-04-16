package ru.rsoft.configurator.core.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

/**
 *
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class UserDto {

    private final int id;
    @Nonnull
    private final String login;
    @Nonnull
    private final String password;

    public UserDto() {
        this(0, "", "");
    }

    public UserDto(int id, @Nonnull String login, @Nonnull String password) {
        this.id = id;
        this.login = login;
        this.password = password;
    }

    public int getId() {
        return id;
    }

    @Nonnull
    public String getLogin() {
        return login;
    }

    @Nonnull
    public String getPassword() {
        return password;
    }

    @Override
    public String toString() {
        return "UserDto{" +
                "id=" + id +
                ", login='" + login + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
