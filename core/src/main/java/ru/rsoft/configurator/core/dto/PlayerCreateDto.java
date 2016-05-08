package ru.rsoft.configurator.core.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import ru.rsoft.configurator.core.entity.Friends;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.List;

/**
 * Created by Admin on 26.11.2015.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class PlayerCreateDto {
    @Nonnull
    private final Integer userId;
    @Nonnull
    private final String nick;

//    private final List<Friends> friends;

    public PlayerCreateDto() {
        this(0, "");
    }

    public PlayerCreateDto(@Nonnull Integer userId, @Nonnull String nick) {
        this.userId = userId;
        this.nick = nick;
//        this.friends = friends;
    }

    @Nonnull
    public Integer getUserId() {
        return userId;
    }

    @Nonnull
    public String getNick() {
        return nick;
    }
/*
    public List<Friends> getFriends() {
        return friends;
    }
*/
    @Override
    public String toString() {
        return "PlayerCreateDto{" +
                "userId=" + userId +
                ", nick='" + nick + '\'' +
//                ", friends='" + friends + '\'' +
                '}';
    }
}
