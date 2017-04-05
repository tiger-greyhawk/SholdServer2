package ru.rsoft.shold.core.dto.timing;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.Date;

/**
 * Created by Admin on 14.08.2016.
 */
public class PlanTargetDto {

    private int id;

    @Nullable
    private int planId;

    @Nullable
    private int targetVillageId;

    @Nullable
    private String targetPlayerName;

    @Nullable
    private String targetPlayerOnline;

    @Nonnull
    private String targetRoute;

    @Nullable
    private String targetInfo;

    @Nullable
    private String targetScreen;

    public PlanTargetDto() {
    }

    public PlanTargetDto(int id, int planId, int targetVillageId, String targetPlayerName, String targetPlayerOnline, @Nonnull String targetRoute, String targetInfo, String targetScreen) {
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
        return "PlanTargetDto{" +
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
