package ru.rsoft.shold.core.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.springframework.data.annotation.Id;
import ru.rsoft.shold.core.entity.Player;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

/**
 * Created by Admin on 07.10.2016.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class FactionDto {

    @Id
    private int id;

    private int houseId;

    private String name;

    @Nonnull
    private String owner;

    @Nullable
    private String officer1;
    @Nullable
    private String officer2;
    @Nullable
    private String officer3;
    @Nullable
    private String officer4;
    @Nullable
    private String officer5;

    private String officerChat;

    private String basicChat;
    /*
    public FactionDto() {
        this(0, 0, "");
    }
*/

    public FactionDto(int id, int houseId, String name, String owner, String officer1, String officer2, String officer3, String officer4, String officer5, String officerChat, String basicChat) {
        this.id = id;
        this.houseId = houseId;
        this.name = name;
        this.owner = owner;
        this.officer1 = officer1;
        this.officer2 = officer2;
        this.officer3 = officer3;
        this.officer4 = officer4;
        this.officer5 = officer5;
        this.officerChat = officerChat;
        this.basicChat = basicChat;
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
        return "FactionDto{" +
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
