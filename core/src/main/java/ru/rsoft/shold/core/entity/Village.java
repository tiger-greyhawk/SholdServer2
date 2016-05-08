package ru.rsoft.shold.core.entity;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.persistence.*;


/**
 * Created by Admin on 16.11.2015.
 */
@Entity
@Table(name="VILLAGES")
public class Village {




    @Id
    @GeneratedValue(strategy =  GenerationType.IDENTITY)
    @Column(name = "ID")
    private Integer id;

    @Column(name = "PLAYER_ID")
    @Nonnull
    private Integer playerId;

    @Column (name = "NAME", nullable = true)
    @Nullable
    private String name;

    @Column (name = "ID_IN_WORLD", nullable = false)
    @Nonnull
    private Integer idInWorld;

    public Village() {
        this(0, "", 0);

    }

    public Village(@Nonnull Integer playerId, @Nullable String name, @Nonnull Integer idInWorld) {
        this.playerId = playerId;
        this.name = name;
        this.idInWorld = idInWorld;
    }

    public Integer getId() {
        return id;
    }

    @Nullable
    public Integer getPlayerId() {
        return playerId;
    }

    @Nullable
    public String getName() {
        return name;
    }

    @Nonnull
    public Integer getIdInWorld() {
        return idInWorld;
    }

    @Override
    public String toString() {
        return "Village{" +
                "id=" + id +
                ", playerId=" + playerId +
                ", name='" + name + '\'' +
                ", idInWorld=" + idInWorld +
                '}';
    }
}