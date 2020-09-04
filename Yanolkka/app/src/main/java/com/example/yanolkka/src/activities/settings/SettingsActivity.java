package com.example.yanolkka.src.activities.settings;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

import com.example.yanolkka.R;
import com.example.yanolkka.src.activities.profile.ProfileActivity;
import com.example.yanolkka.src.common.base.BaseActivity;

import static com.example.yanolkka.src.ApplicationClass.X_ACCESS_TOKEN;
import static com.example.yanolkka.src.ApplicationClass.sSharedPreferences;

public class SettingsActivity extends BaseActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        LinearLayout llSocialSignIn = findViewById(R.id.ll_settings_social_sign_in);
        LinearLayout llSignOut = findViewById(R.id.ll_settings_sign_out);

        if(sSharedPreferences.getString(X_ACCESS_TOKEN, null) != null){
            llSocialSignIn.setVisibility(View.VISIBLE);
            llSocialSignIn.setOnClickListener(this);
            llSignOut.setVisibility(View.VISIBLE);
            llSignOut.setOnClickListener(this);
        }else{
            llSocialSignIn.setVisibility(View.GONE);
            llSignOut.setVisibility(View.GONE);
        }

        findViewById(R.id.iv_settings_back).setOnClickListener(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.iv_settings_back:
                finish();
                break;

            case R.id.ll_settings_sign_out:
                SharedPreferences.Editor editor = sSharedPreferences.edit();
                editor.remove(X_ACCESS_TOKEN);
                editor.apply();
                showCustomToast(getString(R.string.signOut));
                finish();
                break;

            case R.id.ll_my_page_my_info:
                startActivity(new Intent(this, ProfileActivity.class));
        }
    }
}