package com.example.yanolkka.src.activities.reservation.models;

public class ReservationInfo {
    private int AccomIdx;
    private int RoomIdx;
    private String CheckInDate;
    private String CheckOutDate;
    private String ReserveName;
    private String ReserveContact;
    private String VisitExists;
    private String VisitName;
    private String VisitContact;
    private String Transportation;
    private int UserPointUsed;
    private int CouponIdx;
    private int FinalCost;

    public ReservationInfo(int accomIdx, int roomIdx, String checkInDate, String checkOutDate, String reserveName, String reserveContact, String visitExists, String transportation, int finalCost) {
        AccomIdx = accomIdx;
        RoomIdx = roomIdx;
        CheckInDate = checkInDate;
        CheckOutDate = checkOutDate;
        ReserveName = reserveName;
        ReserveContact = reserveContact;
        VisitExists = visitExists;
        Transportation = transportation;
        FinalCost = finalCost;
    }

    public void setVisitExists(String visitExists) {
        VisitExists = visitExists;
    }

    public void setVisitName(String visitName) {
        VisitName = visitName;
    }

    public void setVisitContact(String visitContact) {
        VisitContact = visitContact;
    }

    public void setUserPointUsed(int userPointUsed) {
        UserPointUsed = userPointUsed;
    }

    public void setCouponIdx(int couponIdx) {
        CouponIdx = couponIdx;
    }
}
