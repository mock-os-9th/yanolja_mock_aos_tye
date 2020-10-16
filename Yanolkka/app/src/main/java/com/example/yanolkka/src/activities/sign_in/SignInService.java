package com.example.yanolkka.src.activities.sign_in;

import android.content.SharedPreferences;
import android.util.Log;

import com.example.yanolkka.src.activities.sign_in.interfaces.SignInActivityView;
import com.example.yanolkka.src.activities.sign_in.interfaces.SignInRetrofitInterface;
import com.example.yanolkka.src.activities.sign_in.models.SignIn;
import com.example.yanolkka.src.activities.sign_in.models.SignInResult;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.example.yanolkka.src.ApplicationClass.X_ACCESS_TOKEN;
import static com.example.yanolkka.src.ApplicationClass.getRetrofit;
import static com.example.yanolkka.src.ApplicationClass.sSharedPreferences;

public class SignInService {
    private final SignInActivityView mSignInActivityView;

    public SignInService(final SignInActivityView signInActivityView){
        this.mSignInActivityView = signInActivityView;
    }

    public void signIn(SignIn signIn){
        final SignInRetrofitInterface signInRetrofitInterface = getRetrofit().create(SignInRetrofitInterface.class);
        signInRetrofitInterface.signIn(signIn).enqueue(new Callback<SignInResult>() {
            @Override
            public void onResponse(Call<SignInResult> call, Response<SignInResult> response) {
                if (response.isSuccessful()){
                    SignInResult signInResult = response.body();
                    if (signInResult == null){
                        mSignInActivityView.validateFailure(null);
                        return;
                    }

                    Log.d("TAGTAG", "response : " +
                            "\nuser jwt : "+ signInResult.getJwt()
                            +"\nisSuccess : "+ signInResult.isSuccess()
                            +"\ncode : "+ signInResult.getCode()
                            +"\nmessage : "+ signInResult.getMessage());

                    if (signInResult.getCode() == 100){
                        SharedPreferences.Editor editor = sSharedPreferences.edit();
                        editor.putString(X_ACCESS_TOKEN, signInResult.getJwt());
                        editor.apply();

                        mSignInActivityView.validateSuccess(signInResult.getMessage());
                    }else{
                        mSignInActivityView.validateFailure(signInResult.getMessage());
                    }
                }else{
                    mSignInActivityView.validateFailure(null);
                }
            }

            @Override
            public void onFailure(Call<SignInResult> call, Throwable t) {
                mSignInActivityView.validateFailure(null);
            }
        });
    }
}
