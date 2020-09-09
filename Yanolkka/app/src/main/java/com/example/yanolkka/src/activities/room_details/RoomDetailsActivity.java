package com.example.yanolkka.src.activities.room_details;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.viewpager.widget.ViewPager;

import com.example.yanolkka.R;
import com.example.yanolkka.src.common.base.BaseActivity;
import com.example.yanolkka.src.common.views.NoticeView;

public class RoomDetailsActivity extends BaseActivity implements View.OnClickListener {

    private LinearLayout llBtnLength, llBtnRental, llBtnNight,
            llNoticeReservation, llRulesCancel;
    private RelativeLayout rlBtnReserveNight, rlBtnReserveRental;
    private TextView tvRoomName, tvCapacity, tvAccomName, tvImagePager,
            tvLengthCheckIn, tvLengthCheckOut, tvRentalTime, tvRentalOpenTime,
            tvRentalOriginalPrice, tvRentalDiscount, tvRentalPrice, tvNightCheckIn,
            tvNightCheckOut, tvNightOriginalPrice, tvNightDiscount, tvNightPrice,
            tvReserveRental, tvReserveNight;
    private ImageView ivBtnBack;
    private ViewPager vpImages;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_room_details);

        setViews();

        setListeners();
    }

    private void setViews(){
        llBtnLength = findViewById(R.id.ll_btn_room_details_length);
        llBtnRental = findViewById(R.id.ll_btn_room_details_rental);
        llBtnNight = findViewById(R.id.ll_btn_room_details_night);
        llRulesCancel = findViewById(R.id.ll_room_details_rules_cancel);

        rlBtnReserveNight = findViewById(R.id.rl_btn_room_details_reserve_night);
        rlBtnReserveRental = findViewById(R.id.rl_btn_room_details_reserve_rental);

        tvRoomName = findViewById(R.id.tv_room_details_room_name);
        tvCapacity = findViewById(R.id.tv_room_details_capacity);
        tvAccomName = findViewById(R.id.tv_room_details_accommodation);
        tvImagePager = findViewById(R.id.tv_room_details_vp_pager);
        tvLengthCheckIn = findViewById(R.id.tv_room_details_check_in);
        tvLengthCheckOut = findViewById(R.id.tv_room_details_check_out);
        tvRentalTime = findViewById(R.id.tv_room_details_rental_time);
        tvRentalOpenTime = findViewById(R.id.tv_room_details_rental_open_time);
        tvRentalOriginalPrice = findViewById(R.id.tv_room_details_rental_original_price);
        tvRentalDiscount = findViewById(R.id.tv_room_details_rental_discount);
        tvRentalPrice = findViewById(R.id.tv_room_details_rental_price);
        tvNightCheckIn = findViewById(R.id.tv_room_details_night_check_in);
        tvNightCheckOut = findViewById(R.id.tv_room_details_night_check_out);
        tvNightOriginalPrice = findViewById(R.id.tv_room_details_night_original_price);
        tvNightDiscount = findViewById(R.id.tv_room_details_night_discount);
        tvNightPrice = findViewById(R.id.tv_room_details_night_price);
        tvReserveRental = findViewById(R.id.tv_room_details_reserve_rental);
        tvReserveNight = findViewById(R.id.tv_room_details_reserve_night);

        ivBtnBack = findViewById(R.id.iv_room_details_back);

        vpImages = findViewById(R.id.vp_room_details);
    }

    private void setListeners(){
        ivBtnBack.setOnClickListener(this);
        llBtnLength.setOnClickListener(this);
        llBtnNight.setOnClickListener(this);
        llBtnRental.setOnClickListener(this);
        rlBtnReserveRental.setOnClickListener(this);
        rlBtnReserveNight.setOnClickListener(this);

        String[] cancelRules = getResources().getStringArray(R.array.cancelRules);
        for (String rule : cancelRules){
            NoticeView noticeView = new NoticeView(this, rule);
            llRulesCancel.addView(noticeView);
        }
    }

    @Override
    public void onClick(View view) {
        switch(view.getId()){
            case R.id.iv_room_details_back:
                finish();
                break;

            case R.id.ll_btn_room_details_length:
                //일정 변경
                break;

            case R.id.ll_btn_room_details_night:
            case R.id.rl_btn_room_details_reserve_night:
                //숙박예약
                break;

            case R.id.ll_btn_room_details_rental:
            case R.id.rl_btn_room_details_reserve_rental:
                //대실예약
                break;
        }
    }
}