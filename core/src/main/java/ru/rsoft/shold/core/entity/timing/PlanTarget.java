package ru.rsoft.shold.core.entity.timing;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.persistence.*;
import java.util.Date;

/**
 * Created by Admin on 14.08.2016.
 */

@Entity
@Table(name = "PLAN_TARGET")
public class PlanTarget {

    @Id
    @Column(name = "ID")
    @GeneratedValue
    private int id;

    @Column(name = "PLAN_ID", nullable = true)
    @Nullable
    private int planId;

    @Column(name = "TARGET_VILLAGE_ID", nullable = true)
    @Nullable
    private int targetVillageId;

    @Column(name = "TARGET_PLAYER_NAME", nullable = true)
    @Nullable
    private String targetPlayerName;

    @Column(name = "TARGET_PLAYER_ONLINE", nullable = true)
    @Nullable
    private String targetPlayerOnline;

    @Column(name = "TARGET_ROUTE", nullable = false)
    @Nonnull
    private String targetRoute;

    @Column(name = "TARGET_INFO", nullable = true)
    @Nullable
    private String targetInfo;

    @Column(name = "TARGET_SCREEN", nullable = true)
    @Nullable
    private String targetScreen;

    public PlanTarget() {this(0, 0, 0, "player", "", "", "", "");
    }

    public PlanTarget(int id, int planId, int targetVillageId, String targetPlayerName,
                      String targetPlayerOnline, @Nonnull String targetRoute,
                      String targetInfo, String targetScreen) {
        this.id = id;
        this.planId = planId;
        this.targetVillageId = targetVillageId;
        this.targetPlayerName = targetPlayerName;
        this.targetPlayerOnline = targetPlayerOnline;
        this.targetRoute = targetRoute;
        this.targetInfo = targetInfo;
        this.targetScreen = targetScreen;
    }

    public int getId() {
        return id;
    }

    @Nullable
    public int getPlanId() {
        return planId;
    }

    @Nullable
    public int getTargetVillageId() {
        return targetVillageId;
    }

    @Nullable
    public String getTargetPlayerName() {
        return targetPlayerName;
    }

    @Nullable
    public String getTargetPlayerOnline() {
        return targetPlayerOnline;
    }

    @Nonnull
    public String getTargetRoute() {
        return targetRoute;
    }

    @Nullable
    public String getTargetInfo() {
        return targetInfo;
    }

    @Nullable
    public String getTargetScreen() {
        return targetScreen;
    }

    @Override
    public String toString() {
        return "PlanTarget{" +
                "id=" + id +
                ", planId=" + planId +
                ", targetVillageId=" + targetVillageId +
                ", targetPlayerName='" + targetPlayerName + '\'' +
                ", targetPlayerOnline='" + targetPlayerOnline + '\'' +
                ", targetRoute='" + targetRoute + '\'' +
                ", targetInfo='" + targetInfo + '\'' +
                ", targetScreen='" + targetScreen + '\'' +
                '}';
    }
}
