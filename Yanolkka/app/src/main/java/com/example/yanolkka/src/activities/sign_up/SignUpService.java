package com.example.yanolkka.src.activities.sign_up;

import android.util.Log;

import com.example.yanolkka.src.activities.sign_up.interfaces.SignUpActivityView;
import com.example.yanolkka.src.activities.sign_up.interfaces.SignUpRetrofitInterface;
import com.example.yanolkka.src.activities.sign_up.models.SignUp;
import com.example.yanolkka.src.activities.sign_up.models.SignUpResult;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.example.yanolkka.src.ApplicationClass.getRetrofit;

class SignUpService {
    private final SignUpActivityView mSignUpActivityView;

    SignUpService(final SignUpActivityView signUpActivityView){
        this.mSignUpActivityView = signUpActivityView;
    }

    void signUp(SignUp signUp){
        final SignUpRetrofitInterface signUpRetrofitInterface = getRetrofit().create(SignUpRetrofitInterface.class);
        signUpRetrofitInterface.signUp(signUp).enqueue(new Callback<SignUpResult>() {
            @Override
            public void onResponse(Call<SignUpResult> call, Response<SignUpResult> response) {
                if (response.isSuccessful()){
                    SignUpResult signUpResult = response.body();
                    if (signUpResult == null){
                        mSignUpActivityView.validateFailure(null);
                        return;
                    }

                    Log.d("TAGTAG", "response : "
                            +"\nisSuccess : "+ signUpResult.isSuccess()
                            +"\ncode : "+ signUpResult.getCode()
                            +"\nmessage : "+ signUpResult.getMessage());

                    mSignUpActivityView.validateSuccess(signUpResult.getMessage());

                }else{
                    mSignUpActivityView.validateFailure(null);
                }
            }

            @Override
            public void onFailure(Call<SignUpResult> call, Throwable t) {
                mSignUpActivityView.validateFailure(null);
            }
        });
    }
}
