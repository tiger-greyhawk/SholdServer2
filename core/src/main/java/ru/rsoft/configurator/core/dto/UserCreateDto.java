package ru.rsoft.configurator.core.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.Objects;

/**
 *
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class UserCreateDto {
    @Nonnull
    private final String login;
    @Nonnull
    private final String password;

    public UserCreateDto() {
        this("", "");
    }

    public UserCreateDto(@Nonnull String login, @Nonnull String password) {
        this.login = Objects.requireNonNull(login, "login should not be null");
        this.password = Objects.requireNonNull(password, "password should not be null");
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
        return "UserCreateDto{" +
                "login='" + login + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
