package com.example.yanolkka.src.activities.room_info;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.Build;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
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
import com.example.yanolkka.src.activities.reservation.ReservationActivity;
import com.example.yanolkka.src.activities.room_details.RoomDetailsActivity;
import com.example.yanolkka.src.activities.room_info.interfaces.RoomInfoActivityView;
import com.example.yanolkka.src.activities.room_info.models.MotelInfo;
import com.example.yanolkka.src.activities.room_info.models.MotelInfoResult;
import com.example.yanolkka.src.activities.room_info.models.MotelRoomInfo;
import com.example.yanolkka.src.activities.room_info.models.OverallRatingResult;
import com.example.yanolkka.src.activities.room_info.models.Review;
import com.example.yanolkka.src.activities.room_info.models.ReviewsInfo;
import com.example.yanolkka.src.activities.room_info.models.ReviewsResult;
import com.example.yanolkka.src.common.adapters.ImagePagerAdapter;
import com.example.yanolkka.src.common.base.BaseActivity;
import com.example.yanolkka.src.common.objects.Room;
import com.example.yanolkka.src.common.views.FacilityView;
import com.example.yanolkka.src.common.views.NoticeView;
import com.example.yanolkka.src.common.views.OverallReviewView;
import com.example.yanolkka.src.common.views.ReviewView;
import com.example.yanolkka.src.common.views.RoomView;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.lang.reflect.Array;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

import static com.example.yanolkka.src.ApplicationClass.BASE_LATITUDE;
import static com.example.yanolkka.src.ApplicationClass.BASE_LONGITUDE;

public class RoomInfoActivity extends BaseActivity implements RoomInfoActivityView,
        View.OnClickListener, OnMapReadyCallback {

    private RelativeLayout rlActionBarTransparent, rlActionBar, rlBtnGoReservation;
    private LinearLayout llBtnGoCalendar, llRooms, llFacilitiesHor, llFacilitiesVer,
            llNotices, llIntroduction, llReviews;
    private ImageView ivBtnBackWhite, ivBtnBack, ivBtnLikeWhite, ivBtnLike
            , ivBtnShareWhite, ivBtnShare;
    private TextView tvTitle, tvRating, tvReviews, tvLocation, tvCheckIn, tvCheckOut
            , tvAddress, tvBtnCopyAddress, tvActionTitle, tvPager, tvIntroduction, tvWay;
    private ScrollView scrollView;
    private ViewPager vpImages;

    private MapView mapView;

    private ArrayList<Integer> imageList;

    private ArrayList<Room> rooms;
    private ArrayList<Review> reviews = new ArrayList<>();

    private SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
    private String[] daysOfWeek;
    private String[] notices;

    private String startAt, endAt;
    private int accommodationIdx, groupIdx, numAdult, numKid;
    private char accomType;

    private double latitude = BASE_LATITUDE, longitude = BASE_LONGITUDE;
    private GoogleMap mMap;

    private int screenHeight;

    private RoomInfoService roomInfoService;

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
                WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);

        setContentView(R.layout.activity_room_info);

        getData();

        try {
            setViews();
        } catch (ParseException e) {
            e.printStackTrace();
        }

        DisplayMetrics metrics = getResources().getDisplayMetrics();
        screenHeight = metrics.heightPixels;

        mapView.onCreate(savedInstanceState);
        mapView.getMapAsync(this);

        setListeners();
    }

    private void getData(){
        imageList = new ArrayList<>();

        imageList.add(R.drawable.motel_sample);
        imageList.add(R.drawable.sample_accommodation);

        Intent fromIntent = getIntent();
        accommodationIdx = fromIntent.getIntExtra("idx", 1);
        accomType = fromIntent.getCharExtra("type", 'm');

        rooms = new ArrayList<>();

        Intent intent = getIntent();
        startAt = intent.getStringExtra("startAt");
        endAt = intent.getStringExtra("endAt");
        accommodationIdx = intent.getIntExtra("accomIdx", 1);
        groupIdx = intent.getIntExtra("groupIdx", 1);
        numAdult = intent.getIntExtra("numAdult", 2);
        numKid = intent.getIntExtra("numKid", 0);

        if (accomType == 'm'){
            roomInfoService = new RoomInfoService(this);
            roomInfoService.getMotelInfo(accommodationIdx, startAt, endAt, groupIdx, numAdult, numKid);
        }else{
            for (int i = 1; i <= 4; i++) {
                Room room = new Room('m', "객실"+i, 2, 4);
                room.setCheckIn("15:00");
                room.setRentalTime(4);
                room.setRentalPrice(35000);
                room.setStayingPrice(50000);

                rooms.add(room);
            }
        }
    }

    private void setViews() throws ParseException {
        rlActionBarTransparent = findViewById(R.id.rl_room_info_action_bar_first);
        rlActionBar = findViewById(R.id.rl_room_info_action_bar);
        rlBtnGoReservation = findViewById(R.id.rl_btn_room_info_go_reservation);
        llBtnGoCalendar = findViewById(R.id.ll_btn_room_info_length);
        llRooms = findViewById(R.id.ll_room_info_rooms);
        llFacilitiesHor = findViewById(R.id.ll_room_info_facilities_hor);
        llFacilitiesVer = findViewById(R.id.ll_room_info_facilities_ver);
        llNotices = findViewById(R.id.ll_room_info_notice);
        llIntroduction = findViewById(R.id.ll_room_info_introduction);
        llReviews = findViewById(R.id.ll_room_info_reviews);
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
        tvIntroduction = findViewById(R.id.tv_room_info_introduction);
        tvAddress = findViewById(R.id.tv_room_info_address);
        tvWay = findViewById(R.id.tv_room_info_way);
        tvBtnCopyAddress = findViewById(R.id.tv_btn_room_info_copy_address);
        tvActionTitle = findViewById(R.id.tv_room_info_ab_title);
        vpImages = findViewById(R.id.vp_room_info_photo);
        scrollView = findViewById(R.id.sv_room_info);
        tvPager = findViewById(R.id.tv_room_info_photo_pager);
        mapView = findViewById(R.id.mv_room_info_map);

        daysOfWeek = getResources().getStringArray(R.array.daysOfWeek);

        Calendar start = Calendar.getInstance();
        if (startAt != null){
            start.setTime(format.parse(startAt));
        }
        tvCheckIn.setText(start.get(Calendar.MONTH)+1+"월 "+
                start.get(Calendar.DATE)+"일 ("+daysOfWeek[start.get(Calendar.DAY_OF_WEEK)-1]+")");

        Calendar end = Calendar.getInstance();
        if (endAt != null){
            end.setTime(format.parse(endAt));
        }else{
            end.add(Calendar.DATE, 1);
        }
        tvCheckOut.setText(end.get(Calendar.MONTH)+1+"월 "+
                end.get(Calendar.DATE)+"일 ("+daysOfWeek[end.get(Calendar.DAY_OF_WEEK)-1]+")");

        notices = getResources().getStringArray(R.array.noticesSample);
        for (String notice : notices){
            NoticeView noticeView = new NoticeView(this, notice);
            llNotices.addView(noticeView);
        }

        final ImagePagerAdapter adapter = new ImagePagerAdapter(this, imageList);
        vpImages.setAdapter(adapter);

        vpImages.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                tvPager.setText((position+1)+"/"+adapter.getCount());
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

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
                scrollToView(findViewById(R.id.rl_btn_room_info_coupon), scrollView, 0);
                break;
        }
    }

    @Override
    public void validateSuccess(String text) {

    }

    @Override
    public void validateFailure(String message) {
        Log.e("TAGTAG", message == null ? "null" : message);
    }

    @Override
    public void getMotelInfo(final MotelInfoResult motelInfo) {
        final MotelInfo info = motelInfo.getInfo();
        tvTitle.setText(info.getAccomName());
        tvActionTitle.setText(info.getAccomName());
        tvRating.setText(String.format("%1.1f",info.getAvgRating()));
        tvLocation.setText(info.getGuideFromStation() == null ? info.getAccomAddress() : info.getGuideFromStation());
        if (info.getGuideFromStation() != null)
            tvWay.setText(info.getGuideFromStation());
        tvReviews.setText(info.getNumOfReview()+"");
        tvAddress.setText(info.getAccomAddress());

        latitude = info.getAccomLatitude();
        longitude = info.getAccomLongitude();

        if(motelInfo.getResult().isEmpty()){
            for (int i = 1; i <= 4; i++) {
                Room room = new Room('m', "객실"+i, 2, 4);
                room.setCheckIn("15:00");
                room.setRentalTime(4);
                room.setRentalPrice(35000);
                room.setStayingPrice(50000);

                rooms.add(room);
            }
        }else{
            ArrayList<MotelRoomInfo> motelRoomInfos = motelInfo.getResult();
            for (MotelRoomInfo motelRoomInfo : motelRoomInfos){
                Room room = new Room('m', motelRoomInfo.getRoomName(), 2 ,4);
                room.setRentalPrice(motelRoomInfo.getPartTimePrice());
                room.setStayingPrice(motelRoomInfo.getAllDayPrice());
                room.setCheckIn(motelRoomInfo.getAvailableAllDayCheckIn());
                if (motelRoomInfo.getPartTimeHour() != null){
                    String[] timeArr = motelRoomInfo.getPartTimeHour().split(":");
                    room.setRentalTime(Integer.parseInt(timeArr[0]));
                }
                room.setIdx(motelRoomInfo.getRoomIdx());

                rooms.add(room);
            }
        }

        llRooms.removeAllViews();
        for (final Room room : rooms){
            RoomView roomView = new RoomView(this, room);
            roomView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(RoomInfoActivity.this, RoomDetailsActivity.class);
                    intent.putExtra("type", accomType);
                    intent.putExtra("accomName", info.getAccomName());
                    intent.putExtra("accomIdx", accommodationIdx);
                    intent.putExtra("roomIdx", room.getIdx());
                    intent.putExtra("roomName", room.getName());
                    intent.putExtra("rentalPrice", room.getRentalPrice());
                    intent.putExtra("stayingPrice", room.getStayingPrice());
                    intent.putExtra("rentalTime", room.getRentalTime());
                    intent.putExtra("checkIn",room.getCheckIn());
                    intent.putExtra("startAt", startAt);
                    intent.putExtra("endAt", endAt);
                    startActivity(intent);
                }
            });
            llRooms.addView(roomView);
        }
        llRooms.invalidate();

        mapView.getMapAsync(this);

        if (motelInfo.getFacility() != null){
            for(String facility : motelInfo.getFacility()){
                FacilityView facilityView = new FacilityView(this, facility);
                llFacilitiesHor.addView(facilityView);
            }
        }

        if (info.getAccomIntroduction() != null){
            llIntroduction.setVisibility(View.VISIBLE);
            tvIntroduction.setText(info.getAccomIntroduction());
        }else{
            llIntroduction.setVisibility(View.GONE);
        }

        reviews.addAll(motelInfo.getReviewPreview());

        roomInfoService.getReviewsInfo(accommodationIdx, 2);
    }

    @Override
    public void getReviews(ReviewsInfo reviewsInfo) {
        OverallRatingResult overallRating = reviewsInfo.getOverallRating().get(0);

        OverallReviewView overallReviewView = new OverallReviewView(this,
                overallRating.getReviewCount(), (float)overallRating.getOverallRating(),
                (float)overallRating.getKindnessRating(), (float)overallRating.getCleanlinessRating(),
                (float)overallRating.getConvenienceRating(), (float)overallRating.getLocationRating());

        llReviews.addView(overallReviewView);

        for (Review review : reviews) {
            String userName = review.getUserName();
            String roomName = review.getRoomName();
            String reserveOption = review.getReserveType();
            String content = review.getReviewContent();
            String reply = review.getReviewReply();
            String created = review.getWrittenTime();
            String replyCreated = review.getReplyWrittenTime();
            float rating = review.getOverallRating();

            ReviewView reviewView = null;
            try {
                reviewView = new ReviewView(this, userName, roomName, reserveOption,
                        content, reply, created, replyCreated, rating);
            } catch (ParseException e) {
                e.printStackTrace();
            }

            llReviews.addView(reviewView);
        }
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        LatLng latLng = new LatLng(latitude, longitude);
        Bitmap locationBitmap = ((BitmapDrawable)getResources().getDrawable(R.drawable.accom_indicator)).getBitmap();
        googleMap.moveCamera(CameraUpdateFactory.newLatLng(latLng));
        googleMap.moveCamera(CameraUpdateFactory.zoomTo(15f));
        googleMap.addMarker(new MarkerOptions()
                .position(latLng)
                .icon(BitmapDescriptorFactory.fromBitmap(locationBitmap)));
    }

    @Override
    protected void onResume() {
        mapView.onResume();
        super.onResume();
    }

    @Override
    protected void onStart() {
        mapView.onStart();
        super.onStart();
    }

    @Override
    public void onStop() {
        mapView.onStop();
        super.onStop();
    }

    @Override
    protected void onPause() {
        mapView.onPause();
        super.onPause();
    }

    @Override
    protected void onDestroy() {
        mapView.onDestroy();
        super.onDestroy();
    }
}