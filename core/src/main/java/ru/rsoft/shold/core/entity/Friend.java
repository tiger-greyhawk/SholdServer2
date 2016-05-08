package ru.rsoft.shold.core.entity;

import javax.annotation.Nullable;
import javax.persistence.*;

@Entity
@Table(name = "FRIENDS")
public class Friend {

    @Id
    @Column(name = "ID")
    @GeneratedValue
    private final int id;

    @Column (name = "PLAYER_ID", nullable = false)
    private final int playerId;

    @Column (name = "FRIEND_ID")
    private final int friendId;

    @Column (name = "COMMENT")
    @Nullable
    private final String comment;

    private Friend() {
        this(0, 0, 0,"");
    }

    public Friend(int playerId, int friendId, @Nullable String comment) {
        this(0, playerId, friendId, comment);
    }

    public Friend(int id, int playerId, int friendId, String comment) {
        this.id = id;
        this.playerId = playerId;
        this.friendId = friendId;
        this.comment = comment;
    }

    public int getId() {
        return id;
    }

    public int getPlayerId() {
        return playerId;
    }

    public int getFriendId() {
        return friendId;
    }

    @Nullable
    public String getComment() {
        return comment;
    }

    @Override
    public String toString() {
        return "Friend{" +
                "id=" + id +
                ", playerId=" + playerId +
                ", friendId=" + friendId +
                ", comment='" + comment + '\'' +
                '}';
    }
}
