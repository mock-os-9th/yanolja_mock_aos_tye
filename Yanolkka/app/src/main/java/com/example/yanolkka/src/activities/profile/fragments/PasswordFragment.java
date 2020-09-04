package com.example.yanolkka.src.activities.profile.fragments;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.RelativeLayout;

import com.example.yanolkka.R;
import com.example.yanolkka.src.activities.profile.ProfileActivity;
import com.example.yanolkka.src.common.base.BaseFragment;

public class PasswordFragment extends BaseFragment {

    private EditText etPw;
    private RelativeLayout rlBtnConfirm;

    public PasswordFragment() {
    }

    public static PasswordFragment newInstance() {
        return new PasswordFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_password, container, false);

        etPw = view.findViewById(R.id.et_password_password);
        rlBtnConfirm = view.findViewById(R.id.rl_btn_password_confirm);

        rlBtnConfirm.setEnabled(false);

        etPw.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (etPw.getText().toString().isEmpty()){
                    rlBtnConfirm.setEnabled(false);
                    rlBtnConfirm.setBackground(getContext().getDrawable(R.drawable.button_gray));
                }else{
                    rlBtnConfirm.setEnabled(true);
                    rlBtnConfirm.setBackground(getContext().getDrawable(R.drawable.button_accent));
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        rlBtnConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //비밀번호 확인

                ((ProfileActivity)getActivity()).replaceFragment(getFragmentManager(),
                        PrivacyFragment.newInstance(), R.id.fl_profile_fragments);
            }
        });

        return view;
    }
}