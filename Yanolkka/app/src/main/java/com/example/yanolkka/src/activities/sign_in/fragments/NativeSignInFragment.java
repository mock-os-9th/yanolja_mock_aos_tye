package com.example.yanolkka.src.activities.sign_in.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.yanolkka.R;
import com.example.yanolkka.src.common.fragments.BaseFragment;
import com.example.yanolkka.src.activities.sign_in.SignInActivity;
import com.example.yanolkka.src.activities.sign_in.models.SignIn;
import com.example.yanolkka.src.activities.sign_up.SignUpActivity;

public class NativeSignInFragment extends BaseFragment implements View.OnClickListener {

    private EditText etEmail, etPassword;
    private RelativeLayout rlBtnSignIn, rlBtnSignUp;
    private TextView tvFindPw;

    public NativeSignInFragment() {
    }

    public static NativeSignInFragment newInstance() {
        return new NativeSignInFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_native_sign_in, container, false);

        etEmail = view.findViewById(R.id.et_sign_in_native_id_email);
        etPassword = view.findViewById(R.id.et_sign_in_native_password);
        rlBtnSignIn = view.findViewById(R.id.rl_btn_native_sign_in);
        rlBtnSignUp = view.findViewById(R.id.rl_btn_native_go_sign_up);
        tvFindPw = view.findViewById(R.id.tv_sign_in_native_find_pw);

        etPassword.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (!etEmail.getText().toString().isEmpty() && !etPassword.getText().toString().isEmpty()){
                    rlBtnSignIn.setBackground(getContext().getResources().getDrawable(R.drawable.button_rect_accent));
                    rlBtnSignIn.setEnabled(true);
                }else{
                    rlBtnSignIn.setBackground(getContext().getResources().getDrawable(R.drawable.button_rect_gray));
                    rlBtnSignIn.setEnabled(false);
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        rlBtnSignIn.setEnabled(false);
        rlBtnSignIn.setOnClickListener(this);
        rlBtnSignUp.setOnClickListener(this);
        tvFindPw.setOnClickListener(this);

        return view;
    }

    @Override
    public void onClick(View view) {
        switch(view.getId()){
            case R.id.rl_btn_native_sign_in:
                requestSignIn();
                break;
            case R.id.rl_btn_native_go_sign_up:
                startActivity(new Intent(getContext(), SignUpActivity.class));
                break;
            case R.id.tv_sign_in_native_find_pw:
                goYetActivity();
                break;
        }
    }

    private void requestSignIn(){
        String userEmail = etEmail.getText().toString();
        String pw = etPassword.getText().toString();

        SignIn signIn = new SignIn(userEmail, pw);
        Log.d("TAGTAG", "\nsignIn : \nid : "+signIn.getId()
                +"\npw : "+signIn.getPw()
                +"\nadult : " +signIn.getAdult()
                +"\nchild : "+signIn.getChild()
                +"\nstartDate : "+signIn.getStartDate()
                +"\nendDate : "+signIn.getEndDate());

        ((SignInActivity)getActivity()).signIn(signIn);
    }
}