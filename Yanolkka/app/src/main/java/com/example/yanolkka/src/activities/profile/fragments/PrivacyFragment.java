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
import com.example.yanolkka.src.common.views.ValidatingEditText;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.example.yanolkka.src.ApplicationClass.getRetrofit;

public class PrivacyFragment extends BaseFragment implements View.OnClickListener, ValidatingEditText.EventListener {

    private LinearLayout llBtnWithdrawal, llNickname, llEditNickname, llEditNicknameText, llPw, llEditPw;
    private ImageView ivProfile;
    private TextView tvNickname, tvEmail, tvId, tvCurrentEmail, tvPw, tvPhoneNum;
    private ValidatingEditText vetNickname, vetCurrentPw, vetNewPw, vetNewPwCheck;

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
        llEditNicknameText = view.findViewById(R.id.ll_privacy_nickname_edit_text);
        llPw = view.findViewById(R.id.ll_privacy_password);
        llEditPw = view.findViewById(R.id.ll_privacy_password_edit);
        ivProfile = view.findViewById(R.id.iv_privacy_profile);
        tvNickname = view.findViewById(R.id.tv_privacy_nickname);
        tvEmail = view.findViewById(R.id.tv_privacy_email);
        tvId = view.findViewById(R.id.tv_privacy_id);
        tvCurrentEmail = view.findViewById(R.id.tv_privacy_current_email);
        tvPw = view.findViewById(R.id.tv_privacy_pw);
        tvPhoneNum = view.findViewById(R.id.tv_privacy_phone_number);

        vetNickname = new ValidatingEditText(getContext(), ValidatingEditText.STYLE_NORMAL,getString(R.string.nickname));
        vetNickname.setEventListener(this);
        llEditNicknameText.addView(vetNickname);

        vetCurrentPw = new ValidatingEditText(getContext(), ValidatingEditText.STYLE_PASSWORD,getString(R.string.inputCurrentPw));
        vetCurrentPw.setEventListener(this);
        vetNewPw = new ValidatingEditText(getContext(), ValidatingEditText.STYLE_NORMAL,getString(R.string.inputNewPw));
        vetNewPw.setEventListener(this);
        vetNewPwCheck = new ValidatingEditText(getContext(), ValidatingEditText.STYLE_NORMAL,getString(R.string.checkNewPw));
        vetNewPwCheck.setEventListener(this);
        llEditPw.addView(vetCurrentPw, 0);
        llEditPw.addView(vetNewPw, 1);
        llEditPw.addView(vetNewPwCheck, 2);

        view.findViewById(R.id.iv_btn_privacy_edit_nickname).setOnClickListener(this);
        view.findViewById(R.id.tv_btn_privacy_edit_pw).setOnClickListener(this);
        view.findViewById(R.id.tv_btn_privacy_edit_phone_number).setOnClickListener(this);

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
                vetNickname.setText(tvNickname.getText().toString());
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
                vetCurrentPw.clearText();
                vetNewPw.clearText();
                vetNewPwCheck.clearText();
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

        final String newNickname = vetNickname.getText();
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

        String currentPw = vetCurrentPw.getText();
        final String newPw = vetNewPw.getText();
        String newPwCheck = vetNewPwCheck.getText();

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
                            vetCurrentPw.clearText();
                            vetNewPw.clearText();
                            vetNewPwCheck.clearText();
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

    @Override
    public void onTextChanged() {

    }
}