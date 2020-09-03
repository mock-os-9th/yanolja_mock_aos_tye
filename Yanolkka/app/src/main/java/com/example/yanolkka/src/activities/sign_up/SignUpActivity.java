package com.example.yanolkka.src.activities.sign_up;

import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.util.Log;

import com.example.yanolkka.R;
import com.example.yanolkka.src.common.activities.BaseActivity;
import com.example.yanolkka.src.activities.sign_up.fragments.SignUpTermsFragment;
import com.example.yanolkka.src.activities.sign_up.interfaces.SignUpActivityView;
import com.example.yanolkka.src.activities.sign_up.models.SignUp;

public class SignUpActivity extends BaseActivity implements SignUpActivityView {

    public String email, pw, phoneNum;

    private FragmentManager fragmentManager = getSupportFragmentManager();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.add(R.id.fl_sign_up_fragments, SignUpTermsFragment.newInstance()).commit();
    }

    public void requestSignUp(){
        showProgressDialog();

        SignUp signUp = new SignUp(email, pw, phoneNum);

        Log.d("TAGTAG", "\nsignIn : \nid : "+signUp.getUserId()
                +"\npw : "+signUp.getUserPwd()
                +"\nname : " +signUp.getUserName()
                +"\nbirth : "+signUp.getUserBirth()
                +"\ncontact : "+signUp.getUserContact()
                +"\ngender : "+signUp.getUserGender());

        final SignUpService signUpService = new SignUpService(this);
        signUpService.signUp(signUp);
    }

    @Override
    public void validateSuccess(String text) {
        hideProgressDialog();
        showCustomToast(text);
        finish();
    }

    @Override
    public void validateFailure(String message) {
        hideProgressDialog();
        showCustomToast(message == null || message.isEmpty() ? getString(R.string.networkError) : message);
    }
}