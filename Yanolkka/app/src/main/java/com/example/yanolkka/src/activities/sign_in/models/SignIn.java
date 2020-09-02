package com.example.yanolkka.src.activities.sign_in.models;

import java.text.SimpleDateFormat;
import java.util.Date;

public class SignIn {
    private long startDate, endDate;
    private int adult, child;
    private String id, pw;

    public SignIn(String id, String pw){
        this.id = id;
        this.pw = pw;

        SimpleDateFormat format = new SimpleDateFormat("YYYYMMdd");
        Date date = new Date();
        this.startDate = Long.parseLong(format.format(date));
        this.endDate = startDate+1;

        this.adult = 3;
        this.child = 0;
    }

    public long getStartDate() {
        return startDate;
    }

    public long getEndDate() {
        return endDate;
    }

    public int getAdult() {
        return adult;
    }

    public int getChild() {
        return child;
    }

    public String getId() {
        return id;
    }

    public String getPw() {
        return pw;
    }
}
