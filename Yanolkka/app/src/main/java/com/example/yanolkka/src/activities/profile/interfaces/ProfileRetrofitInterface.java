package com.example.yanolkka.src.activities.profile.interfaces;

import com.example.yanolkka.src.activities.profile.models.EditInfoResult;
import com.example.yanolkka.src.activities.profile.models.NicknameToChange;
import com.example.yanolkka.src.activities.profile.models.PasswordToChange;
import com.example.yanolkka.src.activities.profile.models.PasswordToValidate;
import com.example.yanolkka.src.activities.profile.models.UserInfoResult;
import com.example.yanolkka.src.activities.profile.models.ValidResult;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.PATCH;
import retrofit2.http.POST;

public interface ProfileRetrofitInterface {

    @POST("/isValidPwd")
    Call<ValidResult> getValidResult(@Body PasswordToValidate pwToValidate);

    @GET("/users")
    Call<UserInfoResult> getUserInfo();

    @PATCH("/users/change-name")
    Call<EditInfoResult> editUserNickname(@Body NicknameToChange nicknameToChange);

    @PATCH("/users/change-pwd")
    Call<EditInfoResult> editUserPassword(@Body PasswordToChange pwToChange);
}
