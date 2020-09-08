package com.example.yanolkka.src.activities.maps.interfaces;

import com.example.yanolkka.src.common.objects.MarkerData;

import java.util.ArrayList;

public interface MapsActivityView {

    void validateSuccess(String text);

    void validateFailure(String message);

    void getMarkerData(ArrayList<MarkerData> markers);
}
