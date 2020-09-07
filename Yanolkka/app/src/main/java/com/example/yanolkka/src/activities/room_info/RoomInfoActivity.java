package com.example.yanolkka.src.activities.room_info;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import androidx.annotation.RequiresApi;
import androidx.viewpager.widget.ViewPager;

import com.example.yanolkka.R;
import com.example.yanolkka.src.activities.room_info.interfaces.RoomInfoActivityView;
import com.example.yanolkka.src.common.adapters.ImagePagerAdapter;
import com.example.yanolkka.src.common.base.BaseActivity;
import com.example.yanolkka.src.common.objects.Room;
import com.example.yanolkka.src.common.views.RoomView;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class RoomInfoActivity extends BaseActivity implements RoomInfoActivityView, View.OnClickListener {

    private RelativeLayout rlActionBarTransparent, rlActionBar, rlBtnGoReservation;
    private LinearLayout llBtnGoCalendar, llRooms, llFacilitiesHor, llFacilitiesVer, llNotices;
    private ImageView ivBtnBackWhite, ivBtnBack, ivBtnLikeWhite, ivBtnLike
            , ivBtnShareWhite, ivBtnShare;
    private TextView tvTitle, tvRating, tvReviews, tvLocation, tvCheckIn, tvCheckOut
            , tvAddress, tvBtnCopyAddress, tvActionTitle;
    private ScrollView scrollView;
    private ViewPager vpImages;

    private ArrayList<Integer> imageList;

    private ArrayList<Room> rooms;

    private int screenHeight;

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
                WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);

        setContentView(R.layout.activity_room_info);

        getData();

        setViews();

        DisplayMetrics metrics = getResources().getDisplayMetrics();
        screenHeight = metrics.heightPixels;

        setListeners();
    }

    private void getData(){
        imageList = new ArrayList<>();

        imageList.add(R.drawable.motel_sample);
        imageList.add(R.drawable.sample_accommodation);

        rooms = new ArrayList<>();
        for (int i = 1; i <= 4; i++) {
            Room room = new Room('m', "객실"+i, 2, 4);
            room.setCheckIn("15:00");
            room.setRentalTime(4);
            room.setRentalPrice(35000);
            room.setStayingPrice(50000);

            rooms.add(room);
        }
    }

    private void setViews(){
        rlActionBarTransparent = findViewById(R.id.rl_room_info_action_bar_first);
        rlActionBar = findViewById(R.id.rl_room_info_action_bar);
        rlBtnGoReservation = findViewById(R.id.rl_btn_room_info_go_reservation);
        llBtnGoCalendar = findViewById(R.id.ll_btn_room_info_length);
        llRooms = findViewById(R.id.ll_room_info_rooms);
        llFacilitiesHor = findViewById(R.id.ll_room_info_facilities_hor);
        llFacilitiesVer = findViewById(R.id.ll_room_info_facilities_ver);
        llNotices = findViewById(R.id.ll_room_info_notice);
        ivBtnBackWhite = findViewById(R.id.iv_room_info_back_white);
        ivBtnBack = findViewById(R.id.iv_room_info_back);
        ivBtnLikeWhite = findViewById(R.id.iv_btn_room_info_like_white);
        ivBtnLike = findViewById(R.id.iv_btn_room_info_like);
        ivBtnShareWhite = findViewById(R.id.iv_btn_room_info_share_white);
        ivBtnShare = findViewById(R.id.iv_btn_room_info_share);
        tvTitle = findViewById(R.id.tv_room_info_title);
        tvRating = findViewById(R.id.tv_room_info_rating);
        tvReviews = findViewById(R.id.tv_room_info_reviews);
        tvLocation = findViewById(R.id.tv_room_info_location);
        tvCheckIn = findViewById(R.id.tv_room_info_check_in);
        tvCheckOut = findViewById(R.id.tv_room_info_check_out);
        tvAddress = findViewById(R.id.tv_room_info_address);
        tvBtnCopyAddress = findViewById(R.id.tv_btn_room_info_copy_address);
        tvActionTitle = findViewById(R.id.tv_room_info_ab_title);
        vpImages = findViewById(R.id.vp_room_info_photo);
        scrollView = findViewById(R.id.sv_room_info);

        Intent intent = getIntent();
        tvTitle.setText(intent.getStringExtra("accName"));
        tvActionTitle.setText(intent.getStringExtra("accName"));
        tvRating.setText(intent.getStringExtra("rating"));
        tvReviews.setText(intent.getStringExtra("reviews"));

        ImagePagerAdapter adapter = new ImagePagerAdapter(this, imageList);
        vpImages.setAdapter(adapter);

        for (Room room : rooms)
            llRooms.addView(new RoomView(this, room));

        rlBtnGoReservation.setOnClickListener(this);
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    private void setListeners(){
        //스크롤 리스너
        scrollView.setOnScrollChangeListener(new View.OnScrollChangeListener() {
            @Override
            public void onScrollChange(View view, int i, int i1, int i2, int i3) {
                float alpha = getAlphaForActionBar(view.getScrollY());
                rlActionBar.setAlpha(alpha);
                if (view.getScrollY() > 0){
                    rlActionBarTransparent.setVisibility(View.GONE);
                    rlActionBar.setVisibility(View.VISIBLE);
                }else{
                    rlActionBarTransparent.setVisibility(View.VISIBLE);
                    rlActionBar.setVisibility(View.GONE);
                }
            }

            private float getAlphaForActionBar(int scrollY) {
                float minAlpha = 0.4f,maxAlpha = 1.f;
                float alpha = minAlpha;
                if(scrollY <= 0){
                    alpha = minAlpha;
                }
                else if(scrollY > vpImages.getHeight()){
                    alpha = maxAlpha;
                }
                else {
                    alpha += scrollY * ((maxAlpha - minAlpha) / vpImages.getHeight());
                }
                return alpha;
            }
        });

        ivBtnBack.setOnClickListener(this);
        ivBtnBackWhite.setOnClickListener(this);
        ivBtnLike.setOnClickListener(this);
        ivBtnLikeWhite.setOnClickListener(this);
        llBtnGoCalendar.setOnClickListener(this);
        rlBtnGoReservation.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.iv_room_info_back:
            case R.id.iv_room_info_back_white:
                finish();
                break;

            case R.id.iv_btn_room_info_like:
            case R.id.iv_btn_room_info_like_white:
                //찜
                break;

            case R.id.ll_btn_room_info_length:
                //일정 수정
                break;

            case R.id.rl_btn_room_info_go_reservation:
                //예약하기
                scrollToView(findViewById(R.id.rl_btn_room_info_coupon), scrollView, 0);
                break;
        }
    }

    @Override
    public void validateSuccess(String text) {

    }

    @Override
    public void validateFailure(String message) {

    }
}