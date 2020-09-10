package com.example.yanolkka.src.activities.room_info;

import com.example.yanolkka.src.activities.room_info.interfaces.RoomInfoActivityView;
import com.example.yanolkka.src.activities.room_info.interfaces.RoomInfoRetrofitInterface;
import com.example.yanolkka.src.activities.room_info.models.MotelInfo;
import com.example.yanolkka.src.activities.room_info.models.MotelInfoResult;
import com.example.yanolkka.src.activities.room_info.models.ReviewsInfo;
import com.example.yanolkka.src.activities.room_info.models.ReviewsResult;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.example.yanolkka.src.ApplicationClass.getRetrofit;

public class RoomInfoService {
    private final RoomInfoActivityView roomInfoActivityView;
    final RoomInfoRetrofitInterface roomInfoRetrofitInterface =
            getRetrofit().create(RoomInfoRetrofitInterface.class);

    public RoomInfoService(final RoomInfoActivityView roomInfoActivityView){
        this.roomInfoActivityView = roomInfoActivityView;
    }

    public void getMotelInfo(final int motelIdx, String checkIn, String checkOut, int numAdult, int numKid){
        roomInfoRetrofitInterface.getMotelInfo(motelIdx, checkIn, checkOut, numAdult, numKid)
                .enqueue(new Callback<MotelInfoResult>() {
                    @Override
                    public void onResponse(Call<MotelInfoResult> call, Response<MotelInfoResult> response) {
                        if (response.isSuccessful()){
                            MotelInfoResult result = response.body();
                            if (result != null){
                                if (result.isSuccess() && (result.getCode() == 200 || result.getCode() == 201)){
                                    roomInfoActivityView.getMotelInfo(result);
                                }else{
                                    roomInfoActivityView.validateFailure(result.getMessage());
                                }
                            }else{
                                roomInfoActivityView.validateFailure(response.message());
                            }
                        }else{
                            roomInfoActivityView.validateFailure(response.message());
                        }
                    }

                    @Override
                    public void onFailure(Call<MotelInfoResult> call, Throwable t) {
                        roomInfoActivityView.validateFailure(null);
                    }
                });
    }

    public void getReviewsInfo(int accomIdx, int order){
        roomInfoRetrofitInterface.getReviewsInfo(accomIdx, "'"+order+"'")
                .enqueue(new Callback<ReviewsResult>() {
                    @Override
                    public void onResponse(Call<ReviewsResult> call, Response<ReviewsResult> response) {
                        if (response.isSuccessful()){
                            ReviewsResult reviewsResult = response.body();
                            if (reviewsResult != null){
                                if (reviewsResult.isSuccess()){
                                    if (reviewsResult.getCode() / 100 == 4){
                                        roomInfoActivityView.validateFailure(reviewsResult.getMessage());
                                    }else{
                                        ReviewsInfo reviewsInfo = reviewsResult.getResult();
                                        roomInfoActivityView.getReviews(reviewsInfo);
                                    }
                                }else{
                                    roomInfoActivityView.validateFailure(reviewsResult.getMessage());
                                }
                            }else{
                                roomInfoActivityView.validateFailure(response.message());
                            }
                        }else{
                            roomInfoActivityView.validateFailure(response.message());
                        }
                    }

                    @Override
                    public void onFailure(Call<ReviewsResult> call, Throwable t) {
                        roomInfoActivityView.validateFailure(null);
                    }
                });
    }
}
