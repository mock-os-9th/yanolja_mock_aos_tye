package com.example.yanolkka.src.common.objects;

import com.google.android.gms.maps.model.LatLng;

public class MarkerData {
    private LatLng latLng;
    private int accomIdx;
    private int price;

    public MarkerData(LatLng latLng, int accomIdx, int price) {
        this.latLng = latLng;
        this.accomIdx = accomIdx;
        this.price = price;
    }

    public LatLng getLatLng() {
        return latLng;
    }

    public int getAccomIdx() {
        return accomIdx;
    }

    public int getPrice() {
        return price;
    }
}
