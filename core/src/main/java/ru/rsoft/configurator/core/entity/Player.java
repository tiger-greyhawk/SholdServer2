package ru.rsoft.configurator.core.entity;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.persistence.*;
import java.util.List;
import java.util.Set;

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

    @OneToMany
    @JoinTable( name="PLAYER_FRIENDS",
            joinColumns = @JoinColumn(name="player_id", referencedColumnName="id"),
            inverseJoinColumns = @JoinColumn(name="friends_id", referencedColumnName="id")
    )
    private List<Friends> friends;

    public Player() {
        this(new User("", ""), "");
    }
    public Player(@Nonnull User user,@Nullable String nick) {
        this.user = user;
        this.nick = nick;
//        this.friends = friends;
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
/*
    public List<Friends> getFriends(){
        return friends;
    }
    public void setFriends(List<Friends> friends) {
        this.friends = friends;
    }
*/
    @Override
    public String toString() {
        return "Player{" +
                "id=" + id +
                ", user=" + user +
                ", nick='" + nick +
//                ", friends='" + friends + '\'' +
                '}';
    }
}
