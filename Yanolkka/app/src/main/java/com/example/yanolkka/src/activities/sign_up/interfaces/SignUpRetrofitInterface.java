package com.example.yanolkka.src.activities.sign_up.interfaces;

import com.example.yanolkka.src.activities.sign_up.models.SignUp;
import com.example.yanolkka.src.activities.sign_up.models.SignUpResult;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface SignUpRetrofitInterface {

    @POST("/users")
    Call<SignUpResult> signUp(@Body SignUp signUp);
}
