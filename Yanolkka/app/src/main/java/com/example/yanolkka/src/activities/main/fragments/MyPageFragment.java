package com.example.yanolkka.src.activities.main.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.yanolkka.R;
import com.example.yanolkka.src.activities.main.interfaces.MyPageRetrofitInterface;
import com.example.yanolkka.src.activities.main.models.MyPageResult;
import com.example.yanolkka.src.activities.main.models.Result;
import com.example.yanolkka.src.activities.main.models.User;
import com.example.yanolkka.src.activities.main.models.UserReseration;
import com.example.yanolkka.src.activities.profile.ProfileActivity;
import com.example.yanolkka.src.activities.settings.SettingsActivity;
import com.example.yanolkka.src.activities.sign_in.SignInActivity;
import com.example.yanolkka.src.common.base.BaseFragment;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.example.yanolkka.src.ApplicationClass.X_ACCESS_TOKEN;
import static com.example.yanolkka.src.ApplicationClass.getRetrofit;
import static com.example.yanolkka.src.ApplicationClass.sSharedPreferences;

public class MyPageFragment extends BaseFragment implements View.OnClickListener {

    private boolean signedIn;

    private LinearLayout llUser, llAnonymous, llBtnGoSettings, llBtnMyInfo;
    private TextView tvUserNickname, tvBtnGoSignIn;
    private ImageView ivProfile;

    private List<TextView> tvPoints = new ArrayList<>();

    public MyPageFragment() {
    }

    public static MyPageFragment newInstance() {
        return new MyPageFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_my_page, container, false);

        llUser = view.findViewById(R.id.ll_my_page_user);
        llAnonymous = view.findViewById(R.id.ll_my_page_anonymous);
        llBtnGoSettings = view.findViewById(R.id.ll_my_page_settings);
        llBtnMyInfo = view.findViewById(R.id.ll_my_page_my_info);
        tvUserNickname = view.findViewById(R.id.tv_my_page_nickname);
        tvBtnGoSignIn = view.findViewById(R.id.tv_btn_my_page_go_sign_in);
        ivProfile = view.findViewById(R.id.iv_my_page_profile);

        tvPoints.add((TextView)view.findViewById(R.id.tv_my_page_user_point));
        tvPoints.add((TextView)view.findViewById(R.id.tv_my_page_coupon_point));
        tvPoints.add((TextView)view.findViewById(R.id.tv_my_page_domestic_point));
        tvPoints.add((TextView)view.findViewById(R.id.tv_my_page_leisure_point));
        tvPoints.add((TextView)view.findViewById(R.id.tv_my_page_ktx_point));
        tvPoints.add((TextView)view.findViewById(R.id.tv_my_page_overseas_point));
        tvPoints.add((TextView)view.findViewById(R.id.tv_my_page_user_point));

        llBtnGoSettings.setOnClickListener(this);
        tvBtnGoSignIn.setOnClickListener(this);
        llBtnMyInfo.setOnClickListener(this);

        return view;
    }

    @Override
    public void onResume() {
        super.onResume();

        signedIn = sSharedPreferences.getString(X_ACCESS_TOKEN, null) != null;

        if (signedIn){
            llUser.setVisibility(View.VISIBLE);
            llAnonymous.setVisibility(View.GONE);
            //사용자 정보 세팅
            Glide.with(this).load(R.drawable.profile).circleCrop().into(ivProfile);

            getUserInfo();

        }else{
            llUser.setVisibility(View.GONE);
            llAnonymous.setVisibility(View.VISIBLE);

            for (TextView tv : tvPoints)
                tv.setVisibility(View.GONE);
        }
    }

    private void getUserInfo() {

        MyPageRetrofitInterface myPageRetrofitInterface = getRetrofit().create(MyPageRetrofitInterface.class);
        myPageRetrofitInterface.getMyPageInfo().enqueue(new Callback<MyPageResult>() {
            @Override
            public void onResponse(Call<MyPageResult> call, Response<MyPageResult> response) {
                if (response.isSuccessful()){
                    MyPageResult myPageResult = response.body();

                    if (myPageResult != null){
                        if (myPageResult.isSuccess()){
                            Result result = myPageResult.getResult();

                            if (result != null){
                                if (!result.getUser().isEmpty() && !result.getUserReseration().isEmpty()){
                                    User user = result.getUser().get(0);
                                    UserReseration reseration = result.getUserReseration().get(0);
                                    tvUserNickname.setText(user.getUserName()+user.getUserIdx());

                                    for (TextView tv : tvPoints){
                                        tv.setVisibility(View.VISIBLE);
                                        switch (tv.getId()){
                                            case R.id.tv_my_page_user_point:
                                                tv.setText(user.getUserPoint()+"");
                                                break;

                                            case R.id.tv_my_page_coupon_point:
                                                tv.setText(user.getCouponCount()+"");
                                                break;

                                            case R.id.tv_my_page_domestic_point:
                                                tv.setText(reseration.getDomesticAccommodation()+"");
                                                break;

                                            default:
                                                tv.setText("0");
                                        }
                                    }
                                }
                            }
                        }
                    }
                }else{
                    showCustomToast(getString(R.string.networkError) + response.message());
                }
            }

            @Override
            public void onFailure(Call<MyPageResult> call, Throwable t) {
                showCustomToast(t.getMessage());
            }
        });
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.tv_btn_my_page_go_sign_in:
                startActivity(new Intent(getContext(), SignInActivity.class));
                break;

            case R.id.ll_my_page_settings:
                startActivity(new Intent(getContext(), SettingsActivity.class));
                break;

            case R.id.ll_my_page_my_info:
                startActivity(new Intent(getContext(), ProfileActivity.class));
                break;
        }
    }
}