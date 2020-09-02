package com.example.yanolkka.src.activities.sign_up.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.example.yanolkka.R;
import com.example.yanolkka.src.activities.sign_up.SignUpActivity;

import java.util.Objects;

public class SignUp2Fragment extends Fragment implements View.OnClickListener {
    
    private RelativeLayout rlBtnNext;
    private EditText etEmail, etPw, etPwCheck;

    public SignUp2Fragment() {
    }

    public static SignUp2Fragment newInstance() {
        return new SignUp2Fragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_sign_up_2, container, false);

        TextView tvTitle = view.findViewById(R.id.tv_sign_up_2);
        tvTitle.setText(getString(R.string.signUp)+" (2/3)");

        etEmail = view.findViewById(R.id.et_sign_up_email);
        etPw = view.findViewById(R.id.et_sign_up_pw);
        etPwCheck = view.findViewById(R.id.et_sign_up_pw_check);

        view.findViewById(R.id.iv_sign_up_2_back).setOnClickListener(this);
        rlBtnNext = view.findViewById(R.id.rl_btn_sign_up_2_next);
        rlBtnNext.setOnClickListener(this);

        return view;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.iv_sign_up_2_back:
                assert getFragmentManager() != null;
                getFragmentManager().beginTransaction().remove(this).commit();
                break;

            case R.id.rl_btn_sign_up_2_next:
                SignUpActivity signUpActivity = (SignUpActivity)getActivity();

                signUpActivity.email = etEmail.getText().toString();
                signUpActivity.pw = etPw.getText().toString();

                signUpActivity.addFragment(getFragmentManager(), SignUp3Fragment.newInstance(), R.id.fl_sign_up_fragments);
                break;
        }
    }
}