package ru.rsoft.shold.core.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.annotation.Nonnull;

/**
 * Created by Admin on 26.11.2015.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class PlayerCreateDto {
    @Nonnull
    private final Integer userId;
    @Nonnull
    private final String nick;

//    private final List<Friend> friends;

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
    public List<Friend> getFriends() {
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
