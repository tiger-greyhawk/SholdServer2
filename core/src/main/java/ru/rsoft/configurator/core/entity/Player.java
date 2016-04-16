package ru.rsoft.configurator.core.entity;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
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

    @ManyToOne
    @JoinColumn(name = "USER_ID", nullable = false)
    @Nonnull
    private User user;

    @Column(name = "NICK", unique = true, nullable = false)
    @Nonnull
    private String nick;

    public Player() {
        this(new User("", ""), "");
    }
    public Player(@Nonnull User user,@Nullable String nick) {
        this.user = user;
        this.nick = nick;
    }

    public Integer getId() {
        return id;
    }

    @Nonnull
    public User getUser() {
        return user;
    }

    public void setUser(@Nonnull User user) {
        this.user = user;
    }

    @Nonnull
    public String getNick() {
        return nick;
    }

    public void setNick(@Nonnull String nick) {
        this.nick = nick;
    }

    @Override
    public String toString() {
        return "Player{" +
                "id=" + id +
                ", user=" + user +
                ", nick='" + nick + '\'' +
                '}';
    }
}
