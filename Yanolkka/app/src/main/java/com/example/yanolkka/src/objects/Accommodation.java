package com.example.yanolkka.src.objects;

public class Accommodation {
    private String name;
    private String imageUri;
    private float rating;
    private int reviews;
    private int originalPrice;
    private float discount;

    public Accommodation(String name, String imageUri, int originalPrice, float discount) {
        this.name = name;
        this.imageUri = imageUri;
        this.originalPrice = originalPrice;
        this.discount = discount;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImageUri() {
        return imageUri;
    }

    public void setImageUri(String imageUri) {
        this.imageUri = imageUri;
    }

    public float getRating() {
        return rating;
    }

    public void setRating(float rating) {
        this.rating = rating;
    }

    public int getReviews() {
        return reviews;
    }

    public void setReviews(int reviews) {
        this.reviews = reviews;
    }

    public int getOriginalPrice() {
        return originalPrice;
    }

    public void setOriginalPrice(int originalPrice) {
        this.originalPrice = originalPrice;
    }

    public float getDiscount() {
        return discount;
    }

    public void setDiscount(float discount) {
        this.discount = discount;
    }
}
