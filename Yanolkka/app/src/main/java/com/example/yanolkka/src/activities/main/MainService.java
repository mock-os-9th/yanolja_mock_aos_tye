package com.example.yanolkka.src.activities.main;


import com.example.yanolkka.src.activities.main.interfaces.MainActivityView;
import com.example.yanolkka.src.activities.main.interfaces.MainRetrofitInterface;
import com.example.yanolkka.src.activities.main.models.Group;
import com.example.yanolkka.src.activities.main.models.GroupResult;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.example.yanolkka.src.ApplicationClass.getRetrofit;

public class MainService {
    private final MainActivityView mainActivityView;

    public MainService(final MainActivityView mainActivityView){
        this.mainActivityView = mainActivityView;
    }

    public void getGroups(){
        MainRetrofitInterface mainRetrofitInterface = getRetrofit().create(MainRetrofitInterface.class);
        mainRetrofitInterface.getGroups().enqueue(new Callback<GroupResult>() {
            @Override
            public void onResponse(Call<GroupResult> call, Response<GroupResult> response) {
                if (response.isSuccessful()){
                    GroupResult gResult = response.body();
                    if (gResult != null){
                        if (gResult.isSuccess() &&(gResult.getCode() == 200 || gResult.getCode() == 201)){
                            ArrayList<Group> gList = new ArrayList<>(gResult.getResult());
                            mainActivityView.getGroups(gList);
                        }else{
                            mainActivityView.validateFailure(gResult.getMessage());
                        }
                    }else{
                        mainActivityView.validateFailure(response.message());
                    }
                }
            }

            @Override
            public void onFailure(Call<GroupResult> call, Throwable t) {
                mainActivityView.validateFailure(null);
            }
        });
    }
}
