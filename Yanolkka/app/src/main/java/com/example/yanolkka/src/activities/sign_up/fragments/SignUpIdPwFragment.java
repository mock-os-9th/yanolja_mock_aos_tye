package com.example.yanolkka.src.activities.sign_up.fragments;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.example.yanolkka.R;
import com.example.yanolkka.src.activities.sign_up.SignUpActivity;

public class SignUpIdPwFragment extends Fragment implements View.OnClickListener {
    
    private RelativeLayout rlBtnNext;
    private EditText etEmail, etPw, etPwCheck;

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

        etEmail = view.findViewById(R.id.et_sign_up_email);
        etPw = view.findViewById(R.id.et_sign_up_pw);
        etPwCheck = view.findViewById(R.id.et_sign_up_pw_check);

        setListener(etEmail);
        setListener(etPwCheck);
        setListener(etPw);

        view.findViewById(R.id.iv_sign_up_2_back).setOnClickListener(this);
        rlBtnNext = view.findViewById(R.id.rl_btn_sign_up_2_next);
        rlBtnNext.setOnClickListener(this);

        rlBtnNext.setEnabled(false);

        return view;
    }

    private void setListener(EditText et) {
        et.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (!etEmail.getText().toString().isEmpty() && !etPw.getText().toString().isEmpty()
                        && !etPwCheck.getText().toString().isEmpty()){
                    rlBtnNext.setBackground(getContext().getResources().getDrawable(R.drawable.button_accent));
                    rlBtnNext.setEnabled(true);
                }else{
                    rlBtnNext.setBackground(getContext().getResources().getDrawable(R.drawable.button_gray));
                    rlBtnNext.setEnabled(false);
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
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

                String email = etEmail.getText().toString();
                String pw = etPw.getText().toString();
                String pwCheck = etPwCheck.getText().toString();

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
}