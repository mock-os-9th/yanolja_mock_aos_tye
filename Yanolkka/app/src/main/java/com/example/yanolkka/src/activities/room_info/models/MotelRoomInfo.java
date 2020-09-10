package com.example.yanolkka.src.activities.room_info.models;

public class MotelRoomInfo {
    private int AccomIdx;
    private int RoomIdx;
    private boolean IsPartTimeAvailable;
    private String ReservedCheckIn;
    private String ReservedCheckOut;
    private String AvailablePartTimeCheckIn;
    private String AvailablePartTimeDeadline;
    private int PartTimePrice;
    private int PartTimeHour;
    private boolean IsAllDayAvailable;
    private String AvailableAllDayCheckIn;
    private int AllDayPrice;

    public int getAccomIdx() {
        return AccomIdx;
    }

    public int getRoomIdx() {
        return RoomIdx;
    }

    public boolean isPartTimeAvailable() {
        return IsPartTimeAvailable;
    }

    public String getReservedCheckIn() {
        return ReservedCheckIn;
    }

    public String getReservedCheckOut() {
        return ReservedCheckOut;
    }

    public String getAvailablePartTimeCheckIn() {
        return AvailablePartTimeCheckIn;
    }

    public String getAvailablePartTimeDeadline() {
        return AvailablePartTimeDeadline;
    }

    public int getPartTimePrice() {
        return PartTimePrice;
    }

    public int getPartTimeHour() {
        return PartTimeHour;
    }

    public boolean isAllDayAvailable() {
        return IsAllDayAvailable;
    }

    public String getAvailableAllDayCheckIn() {
        return AvailableAllDayCheckIn;
    }

    public int getAllDayPrice() {
        return AllDayPrice;
    }
}
