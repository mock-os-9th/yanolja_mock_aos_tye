package com.example.yanolkka.src.activities.search_result.interfaces;

import com.example.yanolkka.src.activities.search_result.models.MotelResult;
import com.example.yanolkka.src.activities.search_result.models.NearByHotel;
import com.example.yanolkka.src.activities.search_result.models.NearByMotel;

import java.util.ArrayList;
import java.util.List;

public interface SearchResultActivityView {

    void validateSuccess(String text);

    void validateFailure(String message);

    void getMotels(List<NearByMotel> motels);

    void getHotels(List<NearByHotel> hotels);

    void getRegionMotels(ArrayList<MotelResult> motels);
}
