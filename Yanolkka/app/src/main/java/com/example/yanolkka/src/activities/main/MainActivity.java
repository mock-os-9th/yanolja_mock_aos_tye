package com.example.yanolkka.src.activities.main;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.yanolkka.R;
import com.example.yanolkka.src.BaseActivity;
import com.example.yanolkka.src.YetFragment;
import com.example.yanolkka.src.activities.main.fragments.HomeFragment;
import com.example.yanolkka.src.activities.main.fragments.like.LikeFragment;
import com.example.yanolkka.src.activities.main.fragments.location.LocationFragment;
import com.example.yanolkka.src.activities.main.fragments.MyPageFragment;
import com.example.yanolkka.src.activities.main.fragments.NearbyFragment;
import com.example.yanolkka.src.activities.sign_in.interfaces.SignInRetrofitInterface;
import com.example.yanolkka.src.activities.sign_in.models.SignIn;
import com.example.yanolkka.src.activities.sign_in.models.SignInResult;
import com.example.yanolkka.src.views.CustomBottomNavView;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

import static com.example.yanolkka.src.ApplicationClass.getRetrofit;
import static com.example.yanolkka.src.ApplicationClass.sSharedPreferences;

public class MainActivity extends BaseActivity implements CustomBottomNavView.EventListener {

    private CustomBottomNavView customBottomNavView;

    private FragmentManager fragmentManager;
    private HomeFragment homeFragment;
    private LocationFragment locationFragment;
    private NearbyFragment nearbyFragment;
    private LikeFragment likeFragment;
    private MyPageFragment myPageFragment;

    private int currentPage;

    private Retrofit retrofit = getRetrofit();
    private SignInRetrofitInterface userClient = retrofit.create(SignInRetrofitInterface.class);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        customBottomNavView = findViewById(R.id.cbnv_main);
        customBottomNavView.setEventListener(this);

        fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.add(R.id.fl_fragments, HomeFragment.newInstance()).commit();

        //비회원 토큰 발급
        if(sSharedPreferences.getString("userJwt", null) == null){
            getAnonymousUser();
        }
    }

    private void getAnonymousUser() {
        SignIn signIn = new SignIn(null, null);
        Log.d("TAGTAG", "\nsignIn : \nid : "+signIn.getId()
                +"\npw : "+signIn.getPw()
                +"\nadult : " +signIn.getAdult()
                +"\nchild : "+signIn.getChild()
                +"\nstartDate : "+signIn.getStartDate()
                +"\nendDate : "+signIn.getEndDate());

        Call<SignInResult> call = userClient.signIn(signIn);

        call.enqueue(new Callback<SignInResult>() {
            @Override
            public void onResponse(Call<SignInResult> call, Response<SignInResult> response) {
                if (response.isSuccessful()){
                    SignInResult signInResult = response.body();
                    Log.d("TAGTAG", "response : " +
                            "\nuser jwt : "+ signInResult.getJwt()
                            +"\nisSuccess : "+ signInResult.isSuccess()
                            +"\ncode : "+ signInResult.getCode()
                            +"\nmessage : "+ signInResult.getMessage());

                    SharedPreferences.Editor editor = sSharedPreferences.edit();
                    editor.putString("userJwt", signInResult.getJwt());
                    editor.putBoolean("isAnonymous", true);
                    editor.apply();

                }else{
                    Toast.makeText(MainActivity.this, "sign in failure", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<SignInResult> call, Throwable t) {
                Toast.makeText(MainActivity.this, "sign in error : "+t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void turnPage(int i) {
        int layoutId = R.id.fl_fragments;
        switch (i){
            case 0:
                if (homeFragment == null)
                    homeFragment = HomeFragment.newInstance();
                replaceFragment(fragmentManager, homeFragment, layoutId);
                break;
            case 1:
                if (locationFragment == null)
                    locationFragment = LocationFragment.newInstance();
                replaceFragment(fragmentManager, locationFragment, layoutId);
                break;
            case 2:
//                if (nearbyFragment == null)
//                    nearbyFragment = NearbyFragment.newInstance();
//                replaceFragment(fragmentManager, nearbyFragment, layoutId);
                replaceFragment(fragmentManager, YetFragment.newInstance(), layoutId);
                break;
            case 3:
                if (likeFragment == null)
                    likeFragment = LikeFragment.newInstance();
                replaceFragment(fragmentManager, likeFragment, layoutId);
                break;
            case 4:
//                if (myPageFragment == null)
//                    myPageFragment = MyPageFragment.newInstance();
//                replaceFragment(fragmentManager, myPageFragment, layoutId);
                replaceFragment(fragmentManager, YetFragment.newInstance(), layoutId);
                break;
        }

        currentPage = i;
    }

//    HomeFragment가 안보일 때 누르면 HomeFragment로 이동
    @Override
    public void onBackPressed() {
        if (currentPage != 0){
            customBottomNavView.onBackKey();
        }else{
            finish();
        }
    }
}