package com.example.yanolkka.src.activities.sign_up.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.yanolkka.R;
import com.example.yanolkka.src.BaseFragment;
import com.example.yanolkka.src.activities.sign_up.SignUpActivity;

import java.util.Objects;

public class SignUp1Fragment extends BaseFragment implements View.OnClickListener {

    private RelativeLayout rlBtnNext;

    public SignUp1Fragment() {
    }

    public static SignUp1Fragment newInstance() {
        return new SignUp1Fragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_sign_up_1, container, false);

        TextView tvTitle = view.findViewById(R.id.tv_sign_up_1);
        tvTitle.setText(getString(R.string.signUp)+" (1/3)");

        view.findViewById(R.id.iv_sign_up_1_back).setOnClickListener(this);

        rlBtnNext = view.findViewById(R.id.rl_btn_sign_up_1_next);
        rlBtnNext.setOnClickListener(this);

        return view;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.iv_sign_up_1_back:
                Objects.requireNonNull(getActivity()).finish();
                break;

            case R.id.rl_btn_sign_up_1_next:
                ((SignUpActivity)getActivity())
                        .addFragment(getFragmentManager(), SignUp2Fragment.newInstance(), R.id.fl_sign_up_fragments);
                break;
        }
    }
}