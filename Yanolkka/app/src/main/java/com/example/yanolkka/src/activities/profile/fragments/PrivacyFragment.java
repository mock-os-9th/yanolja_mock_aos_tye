package com.example.yanolkka.src.activities.profile.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.yanolkka.R;
import com.example.yanolkka.src.activities.profile.interfaces.ProfileRetrofitInterface;
import com.example.yanolkka.src.activities.profile.models.EditInfoResult;
import com.example.yanolkka.src.activities.profile.models.NicknameToChange;
import com.example.yanolkka.src.activities.profile.models.PasswordToChange;
import com.example.yanolkka.src.activities.profile.models.User;
import com.example.yanolkka.src.activities.profile.models.UserInfoResult;
import com.example.yanolkka.src.common.base.BaseFragment;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.example.yanolkka.src.ApplicationClass.getRetrofit;

public class PrivacyFragment extends BaseFragment implements View.OnClickListener {

    private LinearLayout llBtnWithdrawal, llNickname, llEditNickname, llPw, llEditPw;
    private ImageView ivProfile;
    private TextView tvNickname, tvEmail, tvId, tvCurrentEmail, tvPw, tvPhoneNum;
    private EditText etNickname, etCurrentPw, etNewPw, etNewPwCheck;

    private ProfileRetrofitInterface profileRetrofitInterface =
            getRetrofit().create(ProfileRetrofitInterface.class);

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
        llNickname = view.findViewById(R.id.ll_privacy_nickname_email);
        llEditNickname = view.findViewById(R.id.ll_privacy_nickname_edit);
        llPw = view.findViewById(R.id.ll_privacy_password);
        llEditPw = view.findViewById(R.id.ll_privacy_password_edit);
        ivProfile = view.findViewById(R.id.iv_privacy_profile);
        tvNickname = view.findViewById(R.id.tv_privacy_nickname);
        tvEmail = view.findViewById(R.id.tv_privacy_email);
        tvId = view.findViewById(R.id.tv_privacy_id);
        tvCurrentEmail = view.findViewById(R.id.tv_privacy_current_email);
        tvPw = view.findViewById(R.id.tv_privacy_pw);
        tvPhoneNum = view.findViewById(R.id.tv_privacy_phone_number);
        etNickname = view.findViewById(R.id.et_edit_nickname);
        etCurrentPw = view.findViewById(R.id.et_privacy_current_pw);
        etNewPw = view.findViewById(R.id.et_privacy_new_pw);
        etNewPwCheck = view.findViewById(R.id.et_privacy_new_pw_check);

        view.findViewById(R.id.iv_btn_privacy_edit_nickname).setOnClickListener(this);
        view.findViewById(R.id.tv_btn_privacy_edit_pw).setOnClickListener(this);
        view.findViewById(R.id.tv_btn_privacy_edit_phone_number).setOnClickListener(this);

        view.findViewById(R.id.iv_btn_privacy_et_nickname_clear).setOnClickListener(this);

        view.findViewById(R.id.tv_btn_privacy_nickname_confirm).setOnClickListener(this);
        view.findViewById(R.id.tv_btn_privacy_nickname_cancel).setOnClickListener(this);

        view.findViewById(R.id.tv_btn_pw_confirm).setOnClickListener(this);
        view.findViewById(R.id.tv_btn_pw_cancel).setOnClickListener(this);

        Glide.with(this).load(R.drawable.profile_large).circleCrop().into(ivProfile);

        return view;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.iv_btn_privacy_edit_nickname:
                llNickname.setVisibility(View.GONE);
                llEditNickname.setVisibility(View.VISIBLE);
                etNickname.setText(tvNickname.getText().toString());
                break;

            case R.id.iv_btn_privacy_et_nickname_clear:
                etNickname.setText("");
                break;

            case R.id.tv_btn_privacy_nickname_cancel:
                llNickname.setVisibility(View.VISIBLE);
                llEditNickname.setVisibility(View.GONE);
                break;

            case R.id.tv_btn_privacy_nickname_confirm:
                //넥네임 수정
                editNickname();
                break;

            case R.id.tv_btn_privacy_edit_pw:
                llPw.setVisibility(View.GONE);
                llEditPw.setVisibility(View.VISIBLE);
                break;

            case R.id.tv_btn_pw_cancel:
                llPw.setVisibility(View.VISIBLE);
                llEditPw.setVisibility(View.GONE);
                etCurrentPw.setText("");
                etNewPw.setText("");
                etNewPwCheck.setText("");
                break;

            case R.id.tv_btn_pw_confirm:
                //비밀번호 수정
                editPassword();
                break;
        }
    }

    @Override
    public void onResume() {
        super.onResume();

        getUserInfo();
    }

    private void getUserInfo(){

        showProgressDialog();

        profileRetrofitInterface.getUserInfo().enqueue(new Callback<UserInfoResult>() {
            @Override
            public void onResponse(Call<UserInfoResult> call, Response<UserInfoResult> response) {
                if (response.isSuccessful()){
                    UserInfoResult userInfoResult = response.body();
                    if (userInfoResult != null && userInfoResult.isSuccess()){
                        User user = userInfoResult.getResult().getUser().get(0);

                        tvNickname.setText(user.getUserName());
                        tvEmail.setText(user.getUserId());
                        tvId.setText(user.getUserId());
                        tvCurrentEmail.setText(user.getUserId());
                        tvPhoneNum.setText(user.getUserContact());
                        tvPw.setText(user.getUserPwd());
                    }
                }
                hideProgressDialog();
            }

            @Override
            public void onFailure(Call<UserInfoResult> call, Throwable t) {
                showCustomToast(t.getMessage());
                hideProgressDialog();
            }
        });
    }

    private void editNickname(){
        showProgressDialog();

        final String newNickname = etNickname.getText().toString();
        NicknameToChange nicknameToChange = new NicknameToChange(newNickname);

        profileRetrofitInterface.editUserNickname(nicknameToChange).enqueue(new Callback<EditInfoResult>() {
            @Override
            public void onResponse(Call<EditInfoResult> call, Response<EditInfoResult> response) {
                if (response.isSuccessful()){
                    EditInfoResult result = response.body();
                    if (result != null) {
                        if (result.getCode() == 200){
                            showCustomToast(result.getMessage());
                            llEditNickname.setVisibility(View.GONE);
                            llNickname.setVisibility(View.VISIBLE);
                            tvNickname.setText(newNickname);
                        }else{
                            showCustomToast(result.getMessage());
                        }
                    }
                }
                hideProgressDialog();
            }

            @Override
            public void onFailure(Call<EditInfoResult> call, Throwable t) {
                showCustomToast(t.getMessage());
                hideProgressDialog();
            }
        });
    }

    private void editPassword() {
        showProgressDialog();

        String currentPw = etCurrentPw.getText().toString();
        final String newPw = etNewPw.getText().toString();
        String newPwCheck = etNewPwCheck.getText().toString();

        PasswordToChange passwordToValidate = new PasswordToChange(currentPw, newPw, newPwCheck);

        profileRetrofitInterface.editUserPassword(passwordToValidate).enqueue(new Callback<EditInfoResult>() {
            @Override
            public void onResponse(Call<EditInfoResult> call, Response<EditInfoResult> response) {
                if (response.isSuccessful()){
                    EditInfoResult result = response.body();

                    if (result != null) {
                        if (result.getCode() == 200){
                            showCustomToast(result.getMessage());
                            llPw.setVisibility(View.VISIBLE);
                            llEditPw.setVisibility(View.GONE);
                            etCurrentPw.setText("");
                            etNewPw.setText("");
                            etNewPwCheck.setText("");
                            tvPw.setText(newPw);
                        }else{
                            showCustomToast(result.getMessage());
                        }
                    }
                }
                hideProgressDialog();
            }

            @Override
            public void onFailure(Call<EditInfoResult> call, Throwable t) {
                showCustomToast(t.getMessage());
                hideProgressDialog();
            }
        });
    }
}