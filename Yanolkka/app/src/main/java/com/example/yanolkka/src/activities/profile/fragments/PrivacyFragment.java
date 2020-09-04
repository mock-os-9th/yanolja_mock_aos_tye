package com.example.yanolkka.src.activities.profile.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.yanolkka.R;
import com.example.yanolkka.src.common.base.BaseFragment;

public class PrivacyFragment extends BaseFragment {

    private LinearLayout llBtnWithdrawal;
    private ImageView ivProfile, ivBtnEditNickname;
    private TextView tvNickname, tvEmail, tvId, tvCurrentEmail,
            tvPw, tvPhoneNum, tvBtnEditPw, tvBtnEditPhoneNum;

    public PrivacyFragment() {
    }

    public static PrivacyFragment newInstance() {
        return new PrivacyFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_privacy, container, false);

        llBtnWithdrawal = view.findViewById(R.id.ll_btn_privacy_withdrawal);
        ivProfile = view.findViewById(R.id.iv_privacy_profile);
        ivBtnEditNickname = view.findViewById(R.id.iv_btn_privacy_edit_nickname);
        tvNickname = view.findViewById(R.id.tv_privacy_nickname);
        tvEmail = view.findViewById(R.id.tv_privacy_email);
        tvId = view.findViewById(R.id.tv_privacy_id);
        tvCurrentEmail = view.findViewById(R.id.tv_privacy_current_email);
        tvPw = view.findViewById(R.id.tv_privacy_pw);
        tvPhoneNum = view.findViewById(R.id.tv_privacy_phone_number);
        tvBtnEditPw = view.findViewById(R.id.tv_btn_privacy_edit_pw);
        tvBtnEditPhoneNum = view.findViewById(R.id.tv_btn_privacy_edit_phone_number);

        Glide.with(this).load(R.drawable.profile_large).circleCrop().into(ivProfile);

        return view;
    }
}