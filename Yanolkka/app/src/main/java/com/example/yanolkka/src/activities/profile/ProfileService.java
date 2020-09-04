package com.example.yanolkka.src.activities.profile;

import com.example.yanolkka.src.activities.profile.interfaces.ProfileActivityView;
import com.example.yanolkka.src.activities.profile.interfaces.ProfileRetrofitInterface;
import com.example.yanolkka.src.activities.profile.models.PasswordToValidate;
import com.example.yanolkka.src.activities.profile.models.ValidResult;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.example.yanolkka.src.ApplicationClass.getRetrofit;

public class ProfileService {
    private final ProfileActivityView profileActivityView;

    public ProfileService(final ProfileActivityView profileActivityView){
        this.profileActivityView = profileActivityView;
    }

    void checkValid(String pw){
        PasswordToValidate pwToValidate = new PasswordToValidate(pw);

        final ProfileRetrofitInterface profileRetrofitInterface =
                getRetrofit().create(ProfileRetrofitInterface.class);

        profileRetrofitInterface.getValidResult(pwToValidate).enqueue(new Callback<ValidResult>() {
            @Override
            public void onResponse(Call<ValidResult> call, Response<ValidResult> response) {
                if (response.isSuccessful()){
                    ValidResult validResult = response.body();
                    if (validResult != null){
                        if (validResult.isSuccess()){

                            if (validResult.getCode() == 200)
                                profileActivityView.validateSuccess(validResult.getMessage());
                            else
                                profileActivityView.validateFailure(validResult.getMessage());

                        }else{
                            profileActivityView.validateFailure(null);
                        }
                    }
                }else{
                    profileActivityView.validateFailure(null);
                }
            }

            @Override
            public void onFailure(Call<ValidResult> call, Throwable t) {
                profileActivityView.validateFailure(t.getMessage());
            }
        });
    }
}
