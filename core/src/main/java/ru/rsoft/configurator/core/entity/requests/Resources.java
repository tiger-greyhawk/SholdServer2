package ru.rsoft.configurator.core.entity.requests;


import java.sql.Timestamp;
import java.util.Date;
import java.util.Objects;

import ru.rsoft.configurator.core.entity.Player;
import ru.rsoft.configurator.core.entity.User;
import ru.rsoft.configurator.core.entity.Village;

import javax.annotation.Nonnull;
import javax.persistence.*;

/**
 * Created by Admin on 24.04.2016.
 */
@Entity
@Table(name = "RESOURCES")
public class Resources {
    @Id
    @Column(name = "ID")
    @GeneratedValue
    private int id;

    @Column (name = "TYPE", nullable = false)
    @Nonnull
    private String type;

    @Column (name = "NAME", nullable = false)
    @Nonnull
    private String name;

    @ManyToOne
    @JoinColumn(name = "VILLAGE_ID", nullable = false)
    @Nonnull
    private Village village;

    @Column(name = "AMOUNT", nullable = false)
    @Nonnull
    private int amount;

    @Column(name = "MAX_QUANTUM", nullable = false)
    @Nonnull
    private int max_quantum;

    @ManyToOne
    @JoinColumn(name = "PLAYER_ID", nullable = false)
    @Nonnull
    private Player player;

    @Column(name = "TIMESTAMP", nullable = false)
    @Nonnull
    //private Timestamp timestamp;
    private Date timestamp;



    private Resources() {
        java.util.Date today = new java.util.Date();
        this.type = "resources";
        this.name = "nothing";
        this.amount = 0;
        this.max_quantum = 300;
        this.player = new Player(new User("",""), "");
        this.timestamp = new Timestamp(today.getTime());
    }

    public Resources(@Nonnull String type, @Nonnull String name, @Nonnull Village village, @Nonnull int amount, @Nonnull int max_quantum, @Nonnull Player player, @Nonnull Date timestamp) {
//        this.id = id;
        this.type = type;
        this.name = name;
        this.village = Objects.requireNonNull(village, "village for a request must not be null");
        this.amount = amount;
        this.max_quantum = max_quantum;
        this.player = Objects.requireNonNull(player, "player for a request must not be null");
        this.timestamp = timestamp;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Nonnull
    public String getType() {
        return type;
    }

    public void setType(@Nonnull String type) {
        this.type = type;
    }

    @Nonnull
    public String getName() {
        return name;
    }

    public void setName(@Nonnull String name) {
        this.name = name;
    }

    @Nonnull
    public Village getVillage() {
        return village;
    }

    public void setVillage(@Nonnull Village village) {
        this.village = village;
    }

    @Nonnull
    public int getAmount() {
        return amount;
    }

    public void setAmount(@Nonnull int amount) {
        this.amount = amount;
    }

    @Nonnull
    public int getMax_quantum() {
        return max_quantum;
    }

    public void setMax_quantum(@Nonnull int max_quantum) {
        this.max_quantum = max_quantum;
    }

    @Nonnull
    public Player getPlayer() {
        return player;
    }

    public void setPlayer(@Nonnull Player player) {
        this.player = player;
    }

    @Nonnull
    public Date getTimestamp() {
        return timestamp;
    }

    public void setTimestamp() {
        java.util.Date today = new java.util.Date();
        this.timestamp = new Date();
    }

    @Override
    public String toString() {
        return "Resources{" +
                "id=" + id +
                ", type='" + type + '\'' +
                ", name='" + name + '\'' +
                ", village=" + village +
                ", amount=" + amount +
                ", max_quantum=" + max_quantum +
                ", player=" + player +
                ", timestamp=" + timestamp +
                '}';
    }
}
