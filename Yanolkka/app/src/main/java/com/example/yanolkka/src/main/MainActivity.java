package com.example.yanolkka.src.main;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.yanolkka.R;
import com.example.yanolkka.src.BaseActivity;
import com.example.yanolkka.src.main.fragments.HomeFragment;
import com.example.yanolkka.src.main.fragments.like.LikeFragment;
import com.example.yanolkka.src.main.fragments.LocationFragment;
import com.example.yanolkka.src.main.fragments.MyPageFragment;
import com.example.yanolkka.src.main.fragments.NearbyFragment;
import com.example.yanolkka.src.views.CustomBottomNavView;

public class MainActivity extends BaseActivity implements CustomBottomNavView.EventListener {

    private CustomBottomNavView customBottomNavView;

    private FragmentManager fragmentManager;
    private HomeFragment homeFragment;
    private LocationFragment locationFragment;
    private NearbyFragment nearbyFragment;
    private LikeFragment likeFragment;
    private MyPageFragment myPageFragment;

    private int currentPage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        customBottomNavView = findViewById(R.id.cbnv_main);
        customBottomNavView.setEventListener(this);

        fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.add(R.id.fl_fragments, HomeFragment.newInstance()).commit();
    }

    @Override
    public void turnPage(int i) {
        switch (i){
            case 0:
                if (homeFragment == null)
                    homeFragment = HomeFragment.newInstance();
                replaceFragment(homeFragment);
                break;
            case 1:
                if (locationFragment == null)
                    locationFragment = LocationFragment.newInstance();
                replaceFragment(locationFragment);
                break;
            case 2:
                if (nearbyFragment == null)
                    nearbyFragment = NearbyFragment.newInstance();
                replaceFragment(nearbyFragment);
                break;
            case 3:
                if (likeFragment == null)
                    likeFragment = LikeFragment.newInstance();
                replaceFragment(likeFragment);
                break;
            case 4:
                if (myPageFragment == null)
                    myPageFragment = MyPageFragment.newInstance();
                replaceFragment(myPageFragment);
                break;
        }

        currentPage = i;
    }

//    Fragment 바꾸기
    public void replaceFragment(Fragment fragment) {
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.fl_fragments, fragment).commit();
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