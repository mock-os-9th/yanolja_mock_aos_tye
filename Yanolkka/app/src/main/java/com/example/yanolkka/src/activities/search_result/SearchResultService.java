package com.example.yanolkka.src.activities.search_result;


import com.example.yanolkka.src.activities.search_result.interfaces.SearchResultActivityView;
import com.example.yanolkka.src.activities.search_result.interfaces.SearchResultRetrofitInterface;
import com.example.yanolkka.src.activities.search_result.models.GetMotelResult;
import com.example.yanolkka.src.activities.search_result.models.LocationInfo;
import com.example.yanolkka.src.activities.search_result.models.MotelResult;
import com.example.yanolkka.src.activities.search_result.models.NearByHotelsResult;
import com.example.yanolkka.src.activities.search_result.models.NearByMotelsResult;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.example.yanolkka.src.ApplicationClass.getRetrofit;

public class SearchResultService {
    private final SearchResultActivityView searchResultActivityView;
    final SearchResultRetrofitInterface searchResultRetrofitInterface =
            getRetrofit().create(SearchResultRetrofitInterface.class);

    public SearchResultService(final SearchResultActivityView searchResultActivityView){
        this.searchResultActivityView = searchResultActivityView;
    }

    public void searchNearByMotels(LocationInfo locationInfo){

        searchResultRetrofitInterface.getNearbyMotels(locationInfo.Latitude, locationInfo.Longitude,
                locationInfo.CheckInDate, locationInfo.CheckOutDate, locationInfo.AdultNum, locationInfo.ChildNum)
                .enqueue(new Callback<NearByMotelsResult>() {
            @Override
            public void onResponse(Call<NearByMotelsResult> call, Response<NearByMotelsResult> response) {
                if (response.isSuccessful()){
                    NearByMotelsResult result = response.body();
                    if (result != null){
                        if (result.isSuccess() && (result.getCode() == 200 || result.getCode() == 201)){
                            searchResultActivityView.getMotels(result.getResult());
                        }else{
                            searchResultActivityView.validateFailure(result.getMessage());
                        }
                    }else{
                        searchResultActivityView.validateFailure(null);
                    }
                }else
                    searchResultActivityView.validateFailure(null);
            }

            @Override
            public void onFailure(Call<NearByMotelsResult> call, Throwable t) {
                searchResultActivityView.validateFailure(null);
            }
        });
    }

    public void searchNearByHotels(LocationInfo locationInfo){

        searchResultRetrofitInterface.getNearbyHotels(locationInfo.Latitude, locationInfo.Longitude,
                locationInfo.CheckInDate, locationInfo.CheckOutDate, locationInfo.AdultNum, locationInfo.ChildNum)
                .enqueue(new Callback<NearByHotelsResult>() {
                    @Override
                    public void onResponse(Call<NearByHotelsResult> call, Response<NearByHotelsResult> response) {
                        if (response.isSuccessful()){
                            NearByHotelsResult result = response.body();
                            if (result != null){
                                if (result.isSuccess() && (result.getCode() == 200 || result.getCode() == 201)){
                                    searchResultActivityView.getHotels(result.getResult());
                                }else{
                                    searchResultActivityView.validateFailure(result.getMessage());
                                }
                            }else{
                                searchResultActivityView.validateFailure(null);
                            }
                        }else
                            searchResultActivityView.validateFailure(null);
                    }

                    @Override
                    public void onFailure(Call<NearByHotelsResult> call, Throwable t) {
                        searchResultActivityView.validateFailure(null);
                    }
                });
    }

    public void getMotels(String startAt, String endAt, int groupIdx, int numAdult, int numKid){
        startAt = "'"+startAt+"'";
        endAt = "'"+endAt+"'";
        searchResultRetrofitInterface.getMotelResult(startAt, endAt, groupIdx, numAdult, numKid)
                .enqueue(new Callback<GetMotelResult>() {
                    @Override
                    public void onResponse(Call<GetMotelResult> call, Response<GetMotelResult> response) {
                        if (response.isSuccessful()){
                            GetMotelResult motelResult = response.body();
                            if (motelResult != null){
                                if (motelResult.isSuccess() && (motelResult.getCode() == 200 || motelResult.getCode() == 201)){
                                    searchResultActivityView.getRegionMotels(motelResult.getResult());
                                }else{
                                    searchResultActivityView.validateFailure(motelResult.getMessage());
                                }
                            }else{
                                searchResultActivityView.validateFailure(response.message());
                            }
                        }else{
                            searchResultActivityView.validateFailure(response.message());
                        }
                    }

                    @Override
                    public void onFailure(Call<GetMotelResult> call, Throwable t) {
                        searchResultActivityView.validateFailure(null);
                    }
                });
    }
}
