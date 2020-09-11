package com.example.yanolkka.src.activities.reservation.models;

public class ReservationResult {
    private boolean IsSuccess;
    private int code;
    private String Message;

    public boolean isSuccess() {
        return IsSuccess;
    }

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return Message;
    }
}
