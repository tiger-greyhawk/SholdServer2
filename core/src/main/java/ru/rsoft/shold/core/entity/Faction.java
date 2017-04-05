package ru.rsoft.shold.core.entity;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.persistence.*;
import java.util.Date;

/**
 * Created by Admin on 07.10.2016.
 */

@Entity
@Table(name = "FACTIONS")
public class Faction {

    @Id
    @GeneratedValue(strategy =  GenerationType.IDENTITY)
    @Column(name = "ID")
    private int id;

    @Column(name = "HOUSE_ID")
    private int houseId;


    @Column(name = "NAME", unique = true)
    private String name;

    @Nonnull
    @Column(name = "OWNER")
    private String owner;

    @Nullable
    @Column(name = "OFFICER1")
    private String officer1;

    @Nullable
    @Column(name = "OFFICER2")
    private String officer2;

    @Nullable
    @Column(name = "OFFICER3")
    private String officer3;

    @Nullable
    @Column(name = "OFFICER4")
    private String officer4;

    @Nullable
    @Column(name = "OFFICER5")
    private String officer5;

    @Column(name = "OFFICER_CHAT")
    private String officerChat;

    @Column(name = "BASIC_CHAT")
    private String basicChat;

    public Faction() {
        this(0, 0, "", "", "", "", "", "", "");
    }

    public Faction(int id, int houseId, String name, String owner, String officer1, String officer2, String officer3, String officer4, String officer5) {
        this.id = id;
        this.houseId = houseId;
        this.name = name;
        this.owner = owner;
        this.officer1 = officer1;
        this.officer2 = officer2;
        this.officer3 = officer3;
        this.officer4 = officer4;
        this.officer5 = officer5;
        this.officerChat = name.replaceAll(" ", "_")+"_"+"_officer";
        this.basicChat = name.replaceAll(" ", "_")+"_"+"_basic";
        //this.officerChat = name.replaceAll(" ", "_")+"_"+owner+"_officer";
        //this.basicChat = name.replaceAll(" ", "_")+"_"+owner+"_basic";
    }



    public int getId() {
        return id;
    }

    public int getHouseId() {
        return houseId;
    }

    public String getName() {
        return name;
    }

    @Nonnull
    public String getOwner() {
        return owner;
    }

    @Nullable
    public String getOfficer1() {
        return officer1;
    }

    @Nullable
    public String getOfficer2() {
        return officer2;
    }

    @Nullable
    public String getOfficer3() {
        return officer3;
    }

    @Nullable
    public String getOfficer4() {
        return officer4;
    }

    @Nullable
    public String getOfficer5() {
        return officer5;
    }

    public String getOfficerChat() {
        return officerChat;
    }

    public String getBasicChat() {
        return basicChat;
    }

    @Override
    public String toString() {
        return "Faction{" +
                "id=" + id +
                ", houseId=" + houseId +
                ", name='" + name + '\'' +
                ", owner='" + owner + '\'' +
                ", officer1='" + officer1 + '\'' +
                ", officer2='" + officer2 + '\'' +
                ", officer3='" + officer3 + '\'' +
                ", officer4='" + officer4 + '\'' +
                ", officer5='" + officer5 + '\'' +
                ", officerChat='" + officerChat + '\'' +
                ", basicChat='" + basicChat + '\'' +
                '}';
    }
}
