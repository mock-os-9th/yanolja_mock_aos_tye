package com.example.yanolkka.src.activities.profile;

import android.os.Bundle;
import android.view.View;

import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.yanolkka.R;
import com.example.yanolkka.src.activities.profile.fragments.PasswordFragment;
import com.example.yanolkka.src.activities.profile.fragments.PrivacyFragment;
import com.example.yanolkka.src.activities.profile.interfaces.ProfileActivityView;
import com.example.yanolkka.src.common.base.BaseActivity;

public class ProfileActivity extends BaseActivity implements ProfileActivityView {

    private FragmentManager fragmentManager = getSupportFragmentManager();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        findViewById(R.id.iv_profile_back).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.add(R.id.fl_profile_fragments, PasswordFragment.newInstance()).commit();
    }

    public void checkValid(String pw){
        showProgressDialog();
        ProfileService profileService = new ProfileService(this);
        profileService.checkValid(pw);
    }

    @Override
    public void validateSuccess(String text) {
        replaceFragment(fragmentManager,
                PrivacyFragment.newInstance(), R.id.fl_profile_fragments);
        hideProgressDialog();
    }

    @Override
    public void validateFailure(String message) {
        hideProgressDialog();
        showCustomToast(message == null || message.isEmpty() ? getString(R.string.networkError) : message);
    }
}