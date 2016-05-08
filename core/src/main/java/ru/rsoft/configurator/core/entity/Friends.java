package ru.rsoft.configurator.core.entity;

//import org.hibernate.annotations.LazyToOne;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
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

//    @ManyToOne
//    @Join
    @Column (name = "PLAYER_ID", nullable = false)
    @Nonnull
    private int playerId;

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
/*
    @ManyToOne
    @JoinTable ( name="PLAYER_FRIENDS",
            joinColumns = @JoinColumn(name="friends_id", referencedColumnName="id"),
            inverseJoinColumns = @JoinColumn(name="player_id", referencedColumnName="id")
    )
*/
    @Column (name = "FRIEND_ID", nullable = true)
    @Nullable
    private int friendId;

    @Column (name = "COMMENT", nullable = true)
    @Nullable
    private String comment;

    private Friends() { this(0, 0,"");
    }

    public Friends(@Nonnull int playerId, @Nonnull int friendId, @Nonnull String comment) {
//    public Friends(@Nonnull int playerId, @Nonnull String comment) {
        this.playerId = playerId;
//        this.friendId = friendId;
        this.comment = comment;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Nonnull
    public int getPlayerId() {
        return playerId;
    }

    public void setPlayerId(@Nonnull int playerId) {
        this.playerId = playerId;
    }

    @Nonnull
    public int getFriendId() {
        return friendId;
    }

    public void setFriendId(@Nonnull int friendId) {
        this.friendId = friendId;
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
                ", playerId=" + playerId +
                ", friendId=" + friendId +
                ", comment='" + comment + '\'' +
                '}';
    }
}
