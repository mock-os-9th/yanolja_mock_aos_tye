package com.example.yanolkka.src.activities.sign_up.fragments;

import android.os.Bundle;
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

public class SignUp3Fragment extends Fragment implements View.OnClickListener {

    private RelativeLayout rlBtnSendAuthCode, rlBtnFinishSignUp;
    private EditText etPhoneNum, etAuthCode;

    public SignUp3Fragment() {
    }

    public static SignUp3Fragment newInstance() {
        return new SignUp3Fragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_sign_up_3, container, false);

        TextView tvTitle = view.findViewById(R.id.tv_sign_up_3);
        tvTitle.setText(getString(R.string.signUp)+" (3/3)");

        rlBtnSendAuthCode = view.findViewById(R.id.rl_btn_send_auth_code);
        rlBtnFinishSignUp = view.findViewById(R.id.rl_btn_sign_up_3_finish);

        etPhoneNum = view.findViewById(R.id.et_sign_up_contact);
        etAuthCode = view.findViewById(R.id.et_sign_up_input_auth_code);

        rlBtnSendAuthCode.setOnClickListener(this);
        rlBtnFinishSignUp.setOnClickListener(this);

        view.findViewById(R.id.iv_sign_up_3_back).setOnClickListener(this);

        return view;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.iv_sign_up_3_back:
                assert getFragmentManager() != null;
                getFragmentManager().beginTransaction().remove(this).commit();
                break;

            case R.id.rl_btn_send_auth_code:
                Toast.makeText(getContext(), getString(R.string.yet), Toast.LENGTH_SHORT).show();
                break;

            case R.id.rl_btn_sign_up_3_finish:
                SignUpActivity signUpActivity = (SignUpActivity)getActivity();
                signUpActivity.phoneNum = etPhoneNum.getText().toString();
                signUpActivity.requestSignUp();
                break;
        }
    }
}