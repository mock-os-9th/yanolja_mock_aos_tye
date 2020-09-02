package com.example.yanolkka.src.activities.sign_in.interfaces;

import com.example.yanolkka.src.activities.sign_in.models.SignIn;
import com.example.yanolkka.src.activities.sign_in.models.SignInResult;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface SignInRetrofitInterface {

    @POST("/jwt")
    Call<SignInResult> signIn(@Body SignIn signIn);
}
