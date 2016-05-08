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
    private int id;

    @Column (name = "NAME", nullable = false)
    @Nonnull
    private String name;

    @Column (name = "FILENAME", nullable = false)
    @Nonnull
    private String fileName;

    @Lob
    @Column (name = "FILE", nullable = false)
    @Nonnull
    private String file;

    @Column (name = "PHOTONAME", nullable = false)
    @Nonnull
    private String photoName;

    @Lob
    @Column (name = "PHOTO", nullable = false)
    @Nonnull
    private String photo;

    @Column (name = "PLAYER_ID", nullable = false)
    @Nonnull
    private Integer playerId;  //должно быть связано с игроком или пользователем - пока не решил.

    @Column (name = "TYPE_CASTLE", nullable = false)
    @Nonnull
    private String typeCastle;

    @Column (name = "ACCESSFROM", nullable = false)
    @Nonnull
    private int accessFrom; //доступ побитово 1-овнер, 2-группа, 3-друзья, 4-остальные

    private Pattern() {this("", "", "", "", "", null, "", 1000);
    }

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

    public Pattern(String name, String fileName, String file, String photoName, String photo, Integer playerId, String typeCastle, int accessFrom) {
        this.name = name;
        this.fileName = fileName;
        this.file = file;
        this.photoName = photoName;
        this.photo = photo;
        this.playerId = playerId;
        this.typeCastle = typeCastle;
        this.accessFrom = accessFrom;
    }

    public int getId() {
        return id;
    }

    @Nonnull
    public String getName() {
        return name;
    }

    public void setName(@Nonnull String name) {
        this.name = name;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getFile() {
        return file;
    }

    public void setFile(String file) {
        this.file = file;
    }

    public String getPhotoName() {
        return photoName;
    }

    public void setPhotoName(String photoName) {
        this.photoName = photoName;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    @Nonnull
    public Integer getPlayerId() {
        return playerId;
    }

    @Nonnull
    public String getTypeCastle() {
        return typeCastle;
    }

    public void setTypeCastle(@Nonnull String typeCastle) {
        this.typeCastle = typeCastle;
    }

    @Nonnull
    public int getAccessFrom() {
        return accessFrom;
    }

    public void setAccessFrom(@Nonnull int accessFrom) {
        this.accessFrom = accessFrom;
    }
}
