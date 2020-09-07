package com.example.yanolkka.src.common.objects;

public class Room {
    private String name;
    private String imageUri;
    private int numStandard;
    private int numMaximum;
    private char type;
    private int rentalTime;
    private String checkIn;
    private int rentalPrice;
    private int stayingPrice;
    private float discount;

    public Room(char type, String name, int numStandard, int numMaximum){
        this.type = type;
        this.name = name;
        this.numStandard = numStandard;
        this.numMaximum = numMaximum;
    }

    public String getImageUri() {
        return imageUri;
    }

    public void setImageUri(String imageUri) {
        this.imageUri = imageUri;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getNumStandard() {
        return numStandard;
    }

    public void setNumStandard(int numStandard) {
        this.numStandard = numStandard;
    }

    public int getNumMaximum() {
        return numMaximum;
    }

    public void setNumMaximum(int numMaximum) {
        this.numMaximum = numMaximum;
    }

    public char getType() {
        return type;
    }

    public void setType(char type) {
        this.type = type;
    }

    public int getRentalTime() {
        return rentalTime;
    }

    public void setRentalTime(int rentalTime) {
        this.rentalTime = rentalTime;
    }

    public String getCheckIn() {
        return checkIn;
    }

    public void setCheckIn(String checkIn) {
        this.checkIn = checkIn;
    }

    public int getRentalPrice() {
        return rentalPrice;
    }

    public void setRentalPrice(int rentalPrice) {
        this.rentalPrice = rentalPrice;
    }

    public int getStayingPrice() {
        return stayingPrice;
    }

    public void setStayingPrice(int stayingPrice) {
        this.stayingPrice = stayingPrice;
    }

    public float getDiscount() {
        return discount;
    }

    public void setDiscount(float discount) {
        this.discount = discount;
    }
}
