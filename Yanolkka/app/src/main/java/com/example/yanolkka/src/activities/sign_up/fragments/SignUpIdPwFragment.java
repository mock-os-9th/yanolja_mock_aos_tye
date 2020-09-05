package com.example.yanolkka.src.activities.sign_up.fragments;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.example.yanolkka.R;
import com.example.yanolkka.src.activities.sign_up.SignUpActivity;
import com.example.yanolkka.src.common.views.ValidatingEditText;

public class SignUpIdPwFragment extends Fragment implements View.OnClickListener, ValidatingEditText.EventListener {
    
    private RelativeLayout rlBtnNext;
    private LinearLayout llIdPw;
    private ValidatingEditText vetEmail, vetPw, vetPwCheck;

    public SignUpIdPwFragment() {
    }

    public static SignUpIdPwFragment newInstance() {
        return new SignUpIdPwFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_sign_up_id_pw, container, false);

        TextView tvTitle = view.findViewById(R.id.tv_sign_up_2);
        tvTitle.setText(getString(R.string.signUp)+" (2/3)");

        llIdPw = view.findViewById(R.id.ll_sign_up_id_pw);
        vetEmail = new ValidatingEditText(getContext(),
                ValidatingEditText.STYLE_EMAIL, getString(R.string.email));
        vetPw = new ValidatingEditText(getContext(),
                ValidatingEditText.STYLE_PASSWORD, getString(R.string.password));
        vetPwCheck = new ValidatingEditText(getContext(),
                ValidatingEditText.STYLE_PASSWORD, getString(R.string.pwCheck));
        vetEmail.setEventListener(this);
        vetPw.setEventListener(this);
        vetPwCheck.setEventListener(this);
        llIdPw.addView(vetEmail);
        llIdPw.addView(vetPw);
        llIdPw.addView(vetPwCheck);

        view.findViewById(R.id.iv_sign_up_2_back).setOnClickListener(this);
        rlBtnNext = view.findViewById(R.id.rl_btn_sign_up_2_next);
        rlBtnNext.setOnClickListener(this);

        rlBtnNext.setEnabled(false);

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

                String email = vetEmail.getText();
                String pw = vetPw.getText();
                String pwCheck = vetPwCheck.getText();

                if (pw.equals(pwCheck)){
                    signUpActivity.email = email;
                    signUpActivity.pw = pw;

                    signUpActivity.addFragment(getFragmentManager(), SignUpPhoneFragment.newInstance(), R.id.fl_sign_up_fragments);
                }else{
                    Toast.makeText(signUpActivity, "입력하신 비밀번호가 일치하지 않습니다.", Toast.LENGTH_SHORT).show();
                }
                break;
        }
    }

    @Override
    public void onTextChanged() {
        if (!vetEmail.getText().isEmpty() && !vetPw.getText().isEmpty()
                && !vetPwCheck.getText().isEmpty()){
            rlBtnNext.setBackground(getContext().getResources().getDrawable(R.drawable.button_accent));
            rlBtnNext.setEnabled(true);
        }else{
            rlBtnNext.setBackground(getContext().getResources().getDrawable(R.drawable.button_gray));
            rlBtnNext.setEnabled(false);
        }
    }
}