package com.example.yanolkka.src.activities.room_details;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.viewpager.widget.ViewPager;

import com.example.yanolkka.R;
import com.example.yanolkka.src.activities.reservation.ReservationActivity;
import com.example.yanolkka.src.common.adapters.ContentsPagerAdapter;
import com.example.yanolkka.src.common.adapters.ImagePagerAdapter;
import com.example.yanolkka.src.common.base.BaseActivity;
import com.example.yanolkka.src.common.views.NoticeView;

import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

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

    private String accomName, roomName, checkIn, startAt, endAt;
    private int accomIdx, roomIdx, rentalPrice, stayingPrice, rentalTime;
    private char type;

    private ArrayList<Integer> images = new ArrayList<>();

    private SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_room_details);

        setViews();

        images.add(R.drawable.room_sample);
        images.add(R.drawable.motel_room_sample);

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

        Intent intent = getIntent();
        type = intent.getCharExtra("type", 'm');
        accomName = intent.getStringExtra("accomName");
        roomName = intent.getStringExtra("roomName");
        accomIdx = intent.getIntExtra("accomIdx", 1);
        if (type == 'm'){
            rentalPrice = intent.getIntExtra("rentalPrice", 30000);
            rentalTime = intent.getIntExtra("rentalTime", 4);
            llBtnRental.setVisibility(View.VISIBLE);
            tvRentalPrice.setText(new DecimalFormat("###,###").format(rentalPrice));
            tvRentalTime.setText("최대 "+rentalTime+"시간");
            rlBtnReserveRental.setVisibility(View.VISIBLE);
            tvReserveNight.setText(getString(R.string.stayNight)+" "+getString(R.string.reserve));
        }
        stayingPrice = intent.getIntExtra("stayingPrice", 50000);
        checkIn = intent.getStringExtra("checkIn");

        startAt = intent.getStringExtra("startAt");
        Calendar start = Calendar.getInstance();
        endAt = intent.getStringExtra("endAt");
        Calendar end = Calendar.getInstance();
        try {
            start.setTime(format.parse(startAt));
            end.setTime(format.parse(endAt));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        String[] daysOfWeek = getResources().getStringArray(R.array.daysOfWeek);

        tvLengthCheckIn.setText(start.get(Calendar.MONTH)+1+"월 "+
                start.get(Calendar.DATE)+"일 ("+daysOfWeek[start.get(Calendar.DAY_OF_WEEK)-1]+")");

        tvLengthCheckOut.setText(end.get(Calendar.MONTH)+1+"월 "+
                end.get(Calendar.DATE)+"일 ("+daysOfWeek[end.get(Calendar.DAY_OF_WEEK)-1]+")");

        tvAccomName.setText(accomName);
        tvRoomName.setText(roomName);
        tvNightCheckIn.setText(checkIn+"부터");
        tvNightPrice.setText(new DecimalFormat("###,###").format(stayingPrice));

        final ImagePagerAdapter mAdapter = new ImagePagerAdapter(this, images);
        vpImages.setAdapter(mAdapter);

        vpImages.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                tvImagePager.setText((position+1)+"/"+mAdapter.getCount());
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
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
                Intent nightIntent = new Intent(this, ReservationActivity.class);
                nightIntent.putExtra("isRental", false);
                nightIntent.putExtra("accomIdx", accomIdx);
                nightIntent.putExtra("roomIdx", roomIdx);
                nightIntent.putExtra("accomName", accomName);
                nightIntent.putExtra("roomName", roomName);
                nightIntent.putExtra("startAt", startAt);
                nightIntent.putExtra("endAt", endAt);
                nightIntent.putExtra("price", stayingPrice);
                startActivity(nightIntent);
                break;

            case R.id.ll_btn_room_details_rental:
            case R.id.rl_btn_room_details_reserve_rental:
                //대실예약
                Intent rentalIntent = new Intent(this, ReservationActivity.class);
                rentalIntent.putExtra("isRental", true);
                rentalIntent.putExtra("accomIdx", accomIdx);
                rentalIntent.putExtra("roomIdx", roomIdx);
                rentalIntent.putExtra("accomName", accomName);
                rentalIntent.putExtra("roomName", roomName);
                rentalIntent.putExtra("startAt", startAt);
                rentalIntent.putExtra("endAt", startAt);
                rentalIntent.putExtra("price", rentalPrice);
                rentalIntent.putExtra("rentalTime", rentalTime);
                startActivity(rentalIntent);
                break;
        }
    }
}