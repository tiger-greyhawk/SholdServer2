package ru.rsoft.shold.core.entity;


import javax.annotation.Nonnull;
import javax.persistence.*;

/**
 * Created by Admin on 16.04.2016.
 */
@Entity
@Table(name = "PATTERNS")
public class Pattern {
    @Id
    @Column(name = "ID")
    @GeneratedValue
    private Integer id;

    @Column (name = "PLAYER_ID", nullable = false)
    @Nonnull
    private Integer playerId;  //должно быть связано с игроком или пользователем - пока не решил.

    @Column (name = "NAME", nullable = false)
    @Nonnull
    private String name;

    @Column (name = "TYPE_CASTLE", nullable = false)
    @Nonnull
    private String typeCastle;

    @Column (name = "ACCESSFROM", nullable = false)
    @Nonnull
    private int accessFrom; //доступ побитово 1-овнер, 2-группа, 3-друзья, 4-остальные

    private Pattern() {this(0, 0, "", "", 0000);
    }
/*
    public Pattern(String name, String typeCastle, Integer playerId) {
        this.name = name;
//        this.fileName = "nil";
//        this.file = "";
//        this.photoName = "nil";
//        this.photo = "";
        this.playerId = playerId;
//        this.player = player;
        this.typeCastle = typeCastle;
//        this.accessFrom = 0000;
    }
*/
    public Pattern(Integer id, Integer playerId, String name, String typeCastle, int accessFrom) {
        this.id = id;
        this.playerId = playerId;
        this.name = name;
        this.typeCastle = typeCastle;
        this.accessFrom = accessFrom;
    }

    public Integer getId() {
        return id;
    }

    @Nonnull
    public Integer getPlayerId() {
        return playerId;
    }

    @Nonnull
    public String getName() {
        return name;
    }

    @Nonnull
    public String getTypeCastle() {
        return typeCastle;
    }

    @Nonnull
    public int getAccessFrom() {
        return accessFrom;
    }

    @Override
    public String toString() {
        return "Pattern{" +
                "id=" + id +
                ", playerId=" + playerId +
                ", name='" + name + '\'' +
                ", typeCastle='" + typeCastle + '\'' +
                ", accessFrom=" + accessFrom +
                '}';
    }
}
