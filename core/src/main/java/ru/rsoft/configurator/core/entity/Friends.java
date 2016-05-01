package ru.rsoft.configurator.core.entity;

import javax.annotation.Nonnull;
import javax.persistence.*;
import java.util.Set;

/**
 * Created by Admin on 27.04.2016.
 */
@Entity
@Table(name = "FRIENDS")
public class Friends {
    @Id
    @Column(name = "ID")
    @GeneratedValue
    private int id;
/*
    @ManyToOne
    @JoinColumn (name = "PLAYER_ID", nullable = false)
    @Nonnull
    private Player player;
*/
/*
    @ManyToMany(fetch = FetchType.EAGER, mappedBy = "players")
    private Set<Player> players;

    public Set<Player> getPlayers() {
        return players;
    }

    public void setPlayers(Set<Player> players) {
        this.players = players;
    }
*/
    @ManyToOne
    @JoinColumn (name = "FRIEND_ID", nullable = false)
    @Nonnull
    private Player friend;

    @Column (name = "COMMENT", nullable = false)
    @Nonnull
    private String comment;

    private Friends() { this(null,null,"");
    }

    public Friends(@Nonnull Set<Player> player, @Nonnull Player friend, @Nonnull String comment) {
//        this.players = (Set<Player>) player;
        this.friend = friend;
        this.comment = comment;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
/*
    @Nonnull
    public Player getPlayer() {
        return player;
    }

    public void setPlayer(@Nonnull Player player) {
        this.player = player;
    }
*/
    @Nonnull
    public Player getFriend() {
        return friend;
    }

    public void setFriend(@Nonnull Player friend) {
        this.friend = friend;
    }

    @Nonnull
    public String getComment() {
        return comment;
    }

    public void setComment(@Nonnull String comment) {
        this.comment = comment;
    }

    @Override
    public String toString() {
        return "Friends{" +
                "id=" + id +
//                ", players=" + players +
                ", friend=" + friend +
                ", comment='" + comment + '\'' +
                '}';
    }
}
