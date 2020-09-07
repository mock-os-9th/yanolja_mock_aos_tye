package com.example.yanolkka.src.activities.search_result.models;

public class LocationInfo {
    public double Latitude, Longitude;
    public String CheckInDate, CheckOutDate;
    public int AdultNum, ChildNum;

    public LocationInfo(double latitude, double longitude, String checkInDate, String checkoutDate, int adultNum, int childNum) {
        Latitude = latitude;
        Longitude = longitude;
        CheckInDate = checkInDate;
        CheckOutDate = checkoutDate;
        AdultNum = adultNum;
        ChildNum = childNum;
    }
}
