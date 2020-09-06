package com.example.yanolkka.src.activities.main;

import android.os.Bundle;
import android.util.Log;

import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.yanolkka.R;
import com.example.yanolkka.src.activities.main.interfaces.MainActivityView;
import com.example.yanolkka.src.common.base.BaseActivity;
import com.example.yanolkka.src.common.yet.YetFragment;
import com.example.yanolkka.src.activities.main.fragments.HomeFragment;
import com.example.yanolkka.src.activities.main.fragments.LikeFragment;
import com.example.yanolkka.src.activities.main.fragments.LocationFragment;
import com.example.yanolkka.src.activities.main.fragments.MyPageFragment;
import com.example.yanolkka.src.activities.main.fragments.NearbyFragment;
import com.example.yanolkka.src.activities.sign_in.models.SignIn;
import com.example.yanolkka.src.common.views.CustomBottomNavView;

import static com.example.yanolkka.src.ApplicationClass.sSharedPreferences;

public class MainActivity extends BaseActivity implements CustomBottomNavView.EventListener, MainActivityView {

    private CustomBottomNavView customBottomNavView;

    private FragmentManager fragmentManager;
    private HomeFragment homeFragment;
    private LocationFragment locationFragment;
    private NearbyFragment nearbyFragment;
    private LikeFragment likeFragment;
    private MyPageFragment myPageFragment;

    public MainService mainService = new MainService(this);

    private int currentPage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        customBottomNavView = findViewById(R.id.cbnv_main);
        customBottomNavView.setEventListener(this);

        fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        homeFragment = HomeFragment.newInstance();
        fragmentTransaction.add(R.id.fl_fragments, homeFragment).commit();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    public void turnPage(int i) {
        int layoutId = R.id.fl_fragments;
        switch (i){
            case 0:
                if (homeFragment == null){
                    homeFragment = HomeFragment.newInstance();
                }
                if(currentPage == 0){
                    homeFragment.sv.smoothScrollTo(0,0);
                }else{
                    replaceFragment(fragmentManager, homeFragment, layoutId);
                }
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
                if (myPageFragment == null)
                    myPageFragment = MyPageFragment.newInstance();
                replaceFragment(fragmentManager, myPageFragment, layoutId);
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

    //    로그인 성공
    @Override
    public void validateSuccess(String text) {
        hideProgressDialog();
        showCustomToast(text);
    }

    //    로그인 실패
    @Override
    public void validateFailure(String message) {
        hideProgressDialog();
        showCustomToast(message == null || message.isEmpty() ? getString(R.string.networkError) : message);
    }
}