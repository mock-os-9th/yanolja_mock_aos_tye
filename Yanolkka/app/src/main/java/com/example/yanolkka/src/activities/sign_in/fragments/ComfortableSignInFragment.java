package com.example.yanolkka.src.activities.sign_in.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.yanolkka.R;
import com.example.yanolkka.src.common.fragments.BaseFragment;

public class ComfortableSignInFragment extends BaseFragment implements View.OnClickListener {

    public ComfortableSignInFragment() {
    }

    public static ComfortableSignInFragment newInstance() {
        return new ComfortableSignInFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_comfortable_sign_in, container, false);

        view.findViewById(R.id.rl_btn_comfortable_sign_in_phone_num).setOnClickListener(this);
        view.findViewById(R.id.rl_btn_comfortable_sign_in_naver).setOnClickListener(this);
        view.findViewById(R.id.rl_btn_comfortable_sign_in_payco).setOnClickListener(this);

        return view;
    }

    @Override
    public void onClick(View view) {
        goYetActivity();
    }
}