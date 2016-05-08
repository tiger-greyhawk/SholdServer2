package ru.rsoft.shold.core.entity;

import javax.annotation.Nonnull;
import javax.persistence.*;

/**
 * Created by Admin on 26.11.2015.
 */
@Entity
@Table(name = "PLAYERS")
public class Player {
    @Id
    @GeneratedValue(strategy =  GenerationType.IDENTITY)
    @Column(name = "ID")
    private Integer id;

    @Column(name = "USER_ID")
    private Integer userId;

    @Column(name = "NICK", unique = true, nullable = false)
    @Nonnull
    private String nick;

    public Player() {
        this(0, "");
    }
    public Player(@Nonnull Integer userId, @Nonnull String nick) {
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
        return "Player{" +
                "id=" + id +
                ", userId=" + userId +
                ", nick='" + nick + '\'' +
                '}';
    }
}
