package com.example.yanolkka.src.activities.maps;

import com.example.yanolkka.src.activities.maps.interfaces.MapsActivityView;
import com.example.yanolkka.src.activities.maps.interfaces.MapsRetrofitInterface;
import com.example.yanolkka.src.activities.maps.models.AccomResult;
import com.example.yanolkka.src.activities.maps.models.MapResult;
import com.example.yanolkka.src.common.objects.MarkerData;
import com.google.android.gms.maps.model.LatLng;

import java.util.ArrayList;
import java.util.Date;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.example.yanolkka.src.ApplicationClass.getRetrofit;

public class MapsService {

    private final MapsActivityView mapsActivityView;

    public MapsService(final MapsActivityView mapsActivityView){
        this.mapsActivityView = mapsActivityView;
    }

    public void getMapInfo(double latitude, double longitude, int scope,
                           String checkIn, String checkOut, int numAdult, int numKid){

        final MapsRetrofitInterface mapsRetrofitInterface = getRetrofit().create(MapsRetrofitInterface.class);

        mapsRetrofitInterface.getMapResult(latitude, longitude, scope, checkIn, checkOut, numAdult, numKid)
                .enqueue(new Callback<MapResult>() {
                    @Override
                    public void onResponse(Call<MapResult> call, Response<MapResult> response) {
                        if (response.isSuccessful()){
                            MapResult result = response.body();
                            if (result != null){
                                if (result.isSuccess() && (result.getCode() == 200 || result.getCode() == 201)){
                                    ArrayList<AccomResult> accomResults = result.getResult();
                                    ArrayList<MarkerData> markers = new ArrayList<>();

                                    for (AccomResult accomResult : accomResults){
                                        LatLng latLng = new LatLng(accomResult.getAccomLatitude(), accomResult.getAccomLongitude());
                                        int price = accomResult.getPrice();
                                        int accomIdx = accomResult.getAccomIdx();
                                        MarkerData markerData = new MarkerData(latLng, accomIdx, price);
                                        markers.add(markerData);
                                    }

                                    mapsActivityView.getMarkerData(markers);
                                }else{
                                    mapsActivityView.validateFailure(result.getMessage());
                                }
                            }else{
                                mapsActivityView.validateFailure(response.message());
                            }
                        }else{
                            mapsActivityView.validateFailure(response.message());
                        }
                    }

                    @Override
                    public void onFailure(Call<MapResult> call, Throwable t) {
                        mapsActivityView.validateFailure(null);
                    }
                });
    }
}
