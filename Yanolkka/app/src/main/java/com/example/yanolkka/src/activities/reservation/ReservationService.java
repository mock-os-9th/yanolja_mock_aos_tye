package com.example.yanolkka.src.activities.reservation;

import com.example.yanolkka.src.activities.reservation.interfaces.ReservationActivityView;
import com.example.yanolkka.src.activities.reservation.interfaces.ReservationRetrofitInterface;
import com.example.yanolkka.src.activities.reservation.models.ReservationInfo;
import com.example.yanolkka.src.activities.reservation.models.ReservationResult;
import com.example.yanolkka.src.activities.room_info.interfaces.RoomInfoActivityView;
import com.example.yanolkka.src.activities.room_info.interfaces.RoomInfoRetrofitInterface;
import com.example.yanolkka.src.activities.room_info.models.MotelInfoResult;
import com.example.yanolkka.src.activities.room_info.models.ReviewsInfo;
import com.example.yanolkka.src.activities.room_info.models.ReviewsResult;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.example.yanolkka.src.ApplicationClass.getRetrofit;

public class ReservationService {
    private final ReservationActivityView reservationActivityView;
    final ReservationRetrofitInterface reservationRetrofitInterface =
            getRetrofit().create(ReservationRetrofitInterface.class);

    public ReservationService(final ReservationActivityView reservationActivityView){
        this.reservationActivityView = reservationActivityView;
    }

    public void getReservationResult(final ReservationInfo reservationInfo){
        reservationRetrofitInterface.getValidResult(reservationInfo).enqueue(new Callback<ReservationResult>() {
            @Override
            public void onResponse(Call<ReservationResult> call, Response<ReservationResult> response) {
                if (response.isSuccessful()){
                    ReservationResult reservationResult = response.body();
                    if (reservationResult != null){
                        if (reservationResult.isSuccess() && reservationResult.getCode() == 200){
                            reservationActivityView.validateSuccess(reservationResult.getMessage());
                        }else{
                            reservationActivityView.validateFailure(reservationResult.getMessage());
                        }
                    }else{
                        reservationActivityView.validateFailure(null);
                    }
                }else{
                    reservationActivityView.validateFailure(null);
                }
            }

            @Override
            public void onFailure(Call<ReservationResult> call, Throwable t) {
                reservationActivityView.validateFailure(null);
            }
        });
    }
}
