package com.example.yanolkka.src.activities.sign_up.fragments;

import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.RequiresApi;

import com.example.yanolkka.R;
import com.example.yanolkka.src.common.fragments.BaseFragment;
import com.example.yanolkka.src.activities.sign_up.SignUpActivity;
import com.example.yanolkka.src.common.views.TermView;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class SignUpTermsFragment extends BaseFragment implements View.OnClickListener, TermView.EventListener {

    private LinearLayout llTerms;

    private RelativeLayout rlBtnNext;
    private CheckBox cbAgreeAll;

    private List<TermView> terms = new ArrayList<>();

    public SignUpTermsFragment() {
    }

    public static SignUpTermsFragment newInstance() {
        return new SignUpTermsFragment();
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        terms.add(new TermView(getContext(), getString(R.string.agreeAge), null, true, null));
        terms.add(new TermView(getContext(), getString(R.string.agreeService), getString(R.string.agreeServiceDetails), true, null));
        terms.add(new TermView(getContext(), getString(R.string.agreeProfile), getString(R.string.agreeProfileDetails1), true, null));
        terms.add(new TermView(getContext(), getString(R.string.agreeLocation), getString(R.string.agreeLocationDetails), false, null));
        terms.add(new TermView(getContext(), getString(R.string.agreeProfile), getString(R.string.agreeProfileDetails2), false, null));
        terms.add(new TermView(getContext(), getString(R.string.agreeRetain), getString(R.string.agreeRetainDetails), false, null));
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_sign_up_terms, container, false);

        TextView tvTitle = view.findViewById(R.id.tv_sign_up_1);
        tvTitle.setText(getString(R.string.signUp)+" (1/3)");

        view.findViewById(R.id.iv_sign_up_1_back).setOnClickListener(this);

        rlBtnNext = view.findViewById(R.id.rl_btn_sign_up_1_next);
        llTerms = view.findViewById(R.id.ll_sign_up_terms);
        cbAgreeAll = view.findViewById(R.id.cb_sign_up_terms_all);

        setMoreViews();

        return view;
    }

    private void setMoreViews(){
        rlBtnNext.setEnabled(false);
        rlBtnNext.setOnClickListener(this);

        for (TermView termView : terms){
            termView.setEventListener(this);
            llTerms.addView(termView);
        }

        cbAgreeAll.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.iv_sign_up_1_back:
                Objects.requireNonNull(getActivity()).finish();
                break;

            case R.id.rl_btn_sign_up_1_next:
                ((SignUpActivity)getActivity())
                        .addFragment(getFragmentManager(), SignUpIdPwFragment.newInstance(), R.id.fl_sign_up_fragments);
                break;

            case R.id.cb_sign_up_terms_all:
                boolean b = ((CheckBox)view).isChecked();
                for (TermView termView : terms)
                    termView.setChecked(b);

                if (b){
                    rlBtnNext.setBackground(getContext().getResources().getDrawable(R.drawable.button_accent));
                    rlBtnNext.setEnabled(true);
                }else{
                    rlBtnNext.setBackground(getContext().getResources().getDrawable(R.drawable.button_gray));
                    rlBtnNext.setEnabled(false);
                }
                break;
        }
    }

    @Override
    public void onCheckChanged() {
        boolean allChecked = true;
        for (TermView termView : terms){
            if(!termView.getChecked()){
                allChecked = false;
                break;
            }
        }
        cbAgreeAll.setChecked(allChecked);

        boolean mandatoryAllChecked = true;
        for (TermView termView : terms){
            if (termView.getMandatory() && !termView.getChecked()){
                mandatoryAllChecked = false;
                break;
            }
        }

        if (allChecked || mandatoryAllChecked){
            rlBtnNext.setBackground(getContext().getResources().getDrawable(R.drawable.button_accent));
            rlBtnNext.setEnabled(true);
        }else{
            rlBtnNext.setBackground(getContext().getResources().getDrawable(R.drawable.button_gray));
            rlBtnNext.setEnabled(false);
        }
    }
}