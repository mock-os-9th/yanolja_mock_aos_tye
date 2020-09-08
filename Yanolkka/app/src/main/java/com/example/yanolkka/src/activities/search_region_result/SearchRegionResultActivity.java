package com.example.yanolkka.src.activities.search_region_result;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.viewpager.widget.ViewPager;

import com.example.yanolkka.R;
import com.example.yanolkka.src.activities.main.MainActivity;
import com.example.yanolkka.src.activities.maps.MapsActivity;
import com.example.yanolkka.src.activities.num_people.NumPeopleActivity;
import com.example.yanolkka.src.activities.search.SearchActivity;
import com.example.yanolkka.src.common.adapters.ContentsPagerAdapter;
import com.example.yanolkka.src.common.base.BaseActivity;
import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class SearchRegionResultActivity extends BaseActivity implements View.OnClickListener {

    static final int REQUEST_NUM_PEOPLE = 1, REQUEST_LENGTH = 0;

    private LinearLayout llLocation, llSchedule,
            llEditOptionsLength, llEditOptionsPerson;
    public LinearLayout llEditOptions;
    private RelativeLayout rlBtnEditOptions;
    private TextView tvLocation, tvSchedule, tvEditOptionsLength, tvEditOptionsPerson;
    private TabLayout tlNearby;
    private ViewPager vpNearby;
    private AppBarLayout abl;
    private ImageView ivBtnSearchOrMap, ivBtnEditSearchOrMap, ivBtnMap, ivBtnHome, ivBtnBack;

    private View vEntire;

    private ContentsPagerAdapter mPagerAdapter;

    private boolean collapsed = false;

    private List<String> titles = new ArrayList<>();

    public double latitude, longitude;
    public int numAdult, numKid, tempNumAdult, tempNumKid;
    public Calendar checkIn, checkOut;

    public int groupIdx;
    public String groupName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_region_result);

        if (titles.isEmpty()){
            titles.add(getString(R.string.motel));
            titles.add(getString(R.string.hotelResort));
            titles.add(getString(R.string.pensionVilla));
            titles.add(getString(R.string.guesthouse));
            titles.add(getString(R.string.infiniteCouponRoom));
            titles.add(getString(R.string.franchise));
            titles.add(getString(R.string.newRemodeled));
            titles.add(getString(R.string.superDealHotel));
            titles.add(getString(R.string.partyRoom));
        }

        llLocation = findViewById(R.id.ll_region_search_location);
        tvLocation = findViewById(R.id.tv_region_search_location);
        llSchedule = findViewById(R.id.ll_region_search_schedule);
        tvSchedule = findViewById(R.id.tv_region_search_schedule);

        tlNearby = findViewById(R.id.tab_region_search);
        vpNearby = findViewById(R.id.vp_region_search);

        ivBtnSearchOrMap = findViewById(R.id.iv_btn_region_result_search);
        ivBtnHome = findViewById(R.id.iv_btn_region_result_home);

        abl = findViewById(R.id.appbar_region_search);

        llEditOptions = findViewById(R.id.ll_region_search_edit_options);
        llEditOptionsLength = findViewById(R.id.ll_region_search_length);
        llEditOptionsPerson = findViewById(R.id.ll_region_search_person);
        tvEditOptionsLength = findViewById(R.id.tv_region_search_length);
        tvEditOptionsPerson = findViewById(R.id.tv_region_search_person);
        ivBtnEditSearchOrMap = findViewById(R.id.iv_btn_region_search_dark);
        rlBtnEditOptions = findViewById(R.id.rl_btn_region_search_edit_options_apply);
        ivBtnMap = findViewById(R.id.iv_region_search_map);
        ivBtnBack = findViewById(R.id.iv_region_result_back);

        vEntire = findViewById(R.id.v_region_search_edit_options_entire);

        setListeners();
    }

    private void setListeners(){
        checkIn = Calendar.getInstance();
        checkOut = Calendar.getInstance();
        checkOut.add(Calendar.DATE, 1);
        numAdult = 2;
        numKid = 0;
        tempNumAdult = numAdult;
        tempNumKid = numKid;

        tvSchedule.setText(String.format("%02d.%02d~%02d.%02d, %d명",
                checkIn.get(Calendar.MONTH)+1, checkIn.get(Calendar.DATE),
                checkOut.get(Calendar.MONTH)+1, checkOut.get(Calendar.DATE),
                numAdult+numKid));

        Intent intent = getIntent();
        groupName = intent.getStringExtra("groupName");
        tvLocation.setText(groupName);
        groupIdx = intent.getIntExtra("groupIdx", 1);

        mPagerAdapter = new ContentsPagerAdapter(
                getSupportFragmentManager(), titles);
        vpNearby.setAdapter(mPagerAdapter);

        tlNearby.setupWithViewPager(vpNearby);

        abl.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener() {
            @Override
            public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {

                collapsed = Math.abs(verticalOffset)-appBarLayout.getTotalScrollRange() == 0;

                int resource = collapsed ? R.drawable.icon_map_accent : R.drawable.ic_baseline_search;
                int visibility = collapsed ? View.GONE : View.VISIBLE;
                ivBtnHome.setVisibility(visibility);

                ivBtnSearchOrMap.setImageResource(resource);
                ivBtnEditSearchOrMap.setImageResource(resource);
            }
        });

        ivBtnSearchOrMap.setOnClickListener(this);
        ivBtnEditSearchOrMap.setOnClickListener(this);
        llLocation.setOnClickListener(this);
        llSchedule.setOnClickListener(this);
        llEditOptionsPerson.setOnClickListener(this);
        llEditOptionsLength.setOnClickListener(this);
        rlBtnEditOptions.setOnClickListener(this);
        vEntire.setOnClickListener(this);
        ivBtnMap.setOnClickListener(this);
        ivBtnHome.setOnClickListener(this);
        ivBtnBack.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.iv_region_result_back:
                finish();
                break;
            case R.id.iv_btn_region_result_search:
            case R.id.iv_btn_region_search_dark:
                if (!collapsed){
                    Intent intent = new Intent(this, SearchActivity.class);
                    intent.putExtra("numAdult", numAdult);
                    intent.putExtra("numKid", numKid);
                    intent.putExtra("checkInMonth", checkIn.get(Calendar.MONTH)+1);
                    intent.putExtra("checkInDate", checkIn.get(Calendar.DATE));
                    intent.putExtra("checkOutMonth", checkOut.get(Calendar.MONTH)+1);
                    intent.putExtra("checkOutDate", checkOut.get(Calendar.DATE));

                    startActivity(intent);
                }else{
                    goMapsActivity();
                }
                break;

            case R.id.iv_region_search_map:
                goMapsActivity();
                break;

            case R.id.ll_region_search_schedule:
                showEdit();
                break;

            case R.id.v_region_search_edit_options_entire:
                hideEdit();
                break;

            case R.id.ll_region_search_person:
                Intent intent = new Intent(this, NumPeopleActivity.class);
                intent.putExtra("numAdult", numAdult);
                intent.putExtra("numKid", numKid);
                startActivityForResult(intent, REQUEST_NUM_PEOPLE);
                break;

            case R.id.rl_btn_region_search_edit_options_apply:
                numAdult = tempNumAdult;
                numKid = tempNumKid;
                tvSchedule.setText(String.format("%02d.%02d~%02d.%02d, %d명",
                        checkIn.get(Calendar.MONTH)+1, checkIn.get(Calendar.DATE),
                        checkOut.get(Calendar.MONTH)+1, checkOut.get(Calendar.DATE),
                        numAdult+numKid));
                hideEdit();
                break;

            case R.id.iv_btn_region_result_home:
                Intent homeIntent = new Intent(this, MainActivity.class);
                homeIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(homeIntent);
                break;
        }
    }

    private void showEdit(){
        Animation fadeIn = AnimationUtils.loadAnimation(this, R.anim.fade_in);
        llEditOptions.setAnimation(fadeIn);
        llEditOptions.setVisibility(View.VISIBLE);

        long diff = checkOut.getTimeInMillis() - checkIn.getTimeInMillis();
        long nights = diff / (1000*60*60*24);
        String length = (checkIn.get(Calendar.MONTH)+1)+"."+checkIn.get(Calendar.DATE)+" ~ "
                +(checkOut.get(Calendar.MONTH)+1)+"."+checkOut.get(Calendar.DATE)+", "+nights+"박";
        tvEditOptionsLength.setText(length);
        tvEditOptionsPerson.setText(getString(R.string.adult)+" "+numAdult+", "+getString(R.string.kid)+" "+numKid);
    }

    public void hideEdit(){
        Animation fadeOut = AnimationUtils.loadAnimation(this, R.anim.fade_out);
        llEditOptions.setAnimation(fadeOut);
        llEditOptions.setVisibility(View.GONE);
    }

    private void goMapsActivity(){
        Intent intent = new Intent(this, MapsActivity.class);
        intent.putExtra("latitude", latitude);
        intent.putExtra("longitude", longitude);
        intent.putExtra("numAdult", numAdult);
        intent.putExtra("numKid", numKid);
        intent.putExtra("checkInMonth", checkIn.get(Calendar.MONTH));
        intent.putExtra("checkOutMonth", checkOut.get(Calendar.MONTH));
        intent.putExtra("checkInDate", checkIn.get(Calendar.DATE));
        intent.putExtra("checkOutDate", checkOut.get(Calendar.DATE));
        startActivity(intent);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK && data != null){
            if (requestCode == REQUEST_NUM_PEOPLE){
                tempNumAdult = data.getIntExtra("numAdult", 2);
                tempNumKid = data.getIntExtra("numKid", 0);

                tvEditOptionsPerson.setText(getString(R.string.adult)+" "+tempNumAdult+", "+getString(R.string.kid)+" "+tempNumKid);
            }
        }
    }

    @Override
    public void onBackPressed() {
        if (llEditOptions.getVisibility() == View.VISIBLE){
            hideEdit();
            return;
        }

        super.onBackPressed();
    }
}