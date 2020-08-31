package com.example.yanolkka.src.activities.sign_in;

import android.os.Bundle;
import android.view.View;

import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.yanolkka.R;
import com.example.yanolkka.src.BaseActivity;
import com.example.yanolkka.src.activities.sign_in.fragments.ComfortableSignInFragment;
import com.example.yanolkka.src.activities.sign_in.fragments.NativeSignInFragment;
import com.google.android.material.tabs.TabLayout;

public class SignInActivity extends BaseActivity implements TabLayout.OnTabSelectedListener {

    private FragmentManager fragmentManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);

        findViewById(R.id.iv_sign_in_back).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        TabLayout signInTabLayout = findViewById(R.id.tab_sign_in);
        signInTabLayout.addTab(signInTabLayout.newTab().setText(getString(R.string.nativeSignIn)));
        signInTabLayout.addTab(signInTabLayout.newTab().setText(getString(R.string.comfortableSignIn)));

        fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.add(R.id.fl_sign_in_fragments, NativeSignInFragment.newInstance()).commit();

        signInTabLayout.addOnTabSelectedListener(this);
    }

    @Override
    public void onTabSelected(TabLayout.Tab tab) {
        int layoutId = R.id.fl_sign_in_fragments;
        switch (tab.getPosition()){
            case 0:
                replaceFragment(fragmentManager, NativeSignInFragment.newInstance(), layoutId);
                break;
            case 1:
                replaceFragment(fragmentManager, ComfortableSignInFragment.newInstance(), layoutId);
                break;
        }
    }

    @Override
    public void onTabUnselected(TabLayout.Tab tab) {

    }

    @Override
    public void onTabReselected(TabLayout.Tab tab) {

    }
}