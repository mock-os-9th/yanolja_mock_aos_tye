package com.example.yanolkka.src.common.objects;

public class Motel extends Accommodation {
    private int originalRentalPrice;
    private float discountRental;
    private int hourRentalStartAt;
    private int minuteRentalStartAt;
    private int rentalLength;

    public Motel(String name, String imageUri, int originalPrice, float discount) {
        super(name, imageUri, originalPrice, discount);
        type = 'm';
    }

    public int getRentalLength() {
        return rentalLength;
    }

    public void setRentalLength(int rentalLength) {
        this.rentalLength = rentalLength;
    }

    public int getOriginalRentalPrice() {
        return originalRentalPrice;
    }

    public void setOriginalRentalPrice(int originalRentalPrice) {
        this.originalRentalPrice = originalRentalPrice;
    }

    public float getDiscountRental() {
        return discountRental;
    }

    public void setDiscountRental(float discountRental) {
        this.discountRental = discountRental;
    }

    public int getHourRentalStartAt() {
        return hourRentalStartAt;
    }

    public void setHourRentalStartAt(int hourRentalStartAt) {
        this.hourRentalStartAt = hourRentalStartAt;
    }

    public int getMinuteRentalStartAt() {
        return minuteRentalStartAt;
    }

    public void setMinuteRentalStartAt(int minuteRentalStartAt) {
        this.minuteRentalStartAt = minuteRentalStartAt;
    }
}
