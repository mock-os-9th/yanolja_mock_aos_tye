package com.example.yanolkka.src.activities.reservation.interfaces;

import com.example.yanolkka.src.activities.profile.models.EditInfoResult;
import com.example.yanolkka.src.activities.profile.models.NicknameToChange;
import com.example.yanolkka.src.activities.profile.models.PasswordToChange;
import com.example.yanolkka.src.activities.profile.models.PasswordToValidate;
import com.example.yanolkka.src.activities.profile.models.UserInfoResult;
import com.example.yanolkka.src.activities.profile.models.ValidResult;
import com.example.yanolkka.src.activities.reservation.models.ReservationInfo;
import com.example.yanolkka.src.activities.reservation.models.ReservationResult;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.PATCH;
import retrofit2.http.POST;

public interface ReservationRetrofitInterface {

    @POST("/reserve-p/order")
    Call<ReservationResult> getValidResult(@Body ReservationInfo reservationInfo);
}
