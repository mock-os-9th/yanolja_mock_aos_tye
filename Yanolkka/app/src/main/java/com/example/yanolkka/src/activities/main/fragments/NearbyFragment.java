package com.example.yanolkka.src.activities.main.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.viewpager.widget.ViewPager;

import com.example.yanolkka.R;
import com.example.yanolkka.src.activities.num_people.NumPeopleActivity;
import com.example.yanolkka.src.activities.search.SearchActivity;
import com.example.yanolkka.src.common.adapters.ContentsPagerAdapter;
import com.example.yanolkka.src.common.base.BaseFragment;
import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import static android.app.Activity.RESULT_OK;
import static com.example.yanolkka.src.ApplicationClass.BASE_LATITUDE;
import static com.example.yanolkka.src.ApplicationClass.BASE_LONGITUDE;

public class NearbyFragment extends BaseFragment implements View.OnClickListener {
    static final int REQUEST_NUM_PEOPLE = 1, REQUEST_LENGTH = 0;

    private LinearLayout llLocation, llSchedule,
            llEditOptionsLength, llEditOptionsPerson;
    public LinearLayout llEditOptions;
    private RelativeLayout rlBtnEditOptions;
    private TextView tvLocation, tvSchedule, tvEditOptionsLength, tvEditOptionsPerson;
    private TabLayout tlNearby;
    private ViewPager vpNearby;
    private AppBarLayout abl;
    private ImageView ivBtnSearchOrMap, ivBtnEditSearchOrMap;

    private View vEntire;

    private ContentsPagerAdapter mPagerAdapter;

    private boolean collapsed = false;

    private List<String> titles = new ArrayList<>();

    public double latitude, longitude;
    public int numAdult, numKid;
    public Calendar checkIn, checkOut;

    public NearbyFragment() {
    }

    public static NearbyFragment newInstance() {
        return new NearbyFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

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
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_nearby, container, false);

        llLocation = view.findViewById(R.id.ll_nearby_location);
        tvLocation = view.findViewById(R.id.tv_nearby_location);
        llSchedule = view.findViewById(R.id.ll_nearby_schedule);
        tvSchedule = view.findViewById(R.id.tv_nearby_schedule);

        tlNearby = view.findViewById(R.id.tab_nearby);
        vpNearby = view.findViewById(R.id.vp_nearby);

        ivBtnSearchOrMap = view.findViewById(R.id.iv_btn_nearby_search);
        abl = view.findViewById(R.id.appbar_nearby);

        llEditOptions = view.findViewById(R.id.ll_nearby_edit_options);
        llEditOptionsLength = view.findViewById(R.id.ll_nearby_length);
        llEditOptionsPerson = view.findViewById(R.id.ll_nearby_person);
        tvEditOptionsLength = view.findViewById(R.id.tv_nearby_length);
        tvEditOptionsPerson = view.findViewById(R.id.tv_nearby_person);
        ivBtnEditSearchOrMap = view.findViewById(R.id.iv_btn_nearby_search_dark);
        rlBtnEditOptions = view.findViewById(R.id.rl_btn_nearby_edit_options_apply);

        vEntire = view.findViewById(R.id.v_nearby_edit_options_entire);

        setListeners();

        return view;
    }

    private void setListeners(){
        latitude = BASE_LATITUDE;
        longitude = BASE_LONGITUDE;

        checkIn = Calendar.getInstance();
        checkOut = Calendar.getInstance();
        checkOut.add(Calendar.DATE, 1);
        numAdult = 2;
        numKid = 0;

        tvSchedule.setText(String.format("%02d.%02d~%02d.%02d, %d명",
                checkIn.get(Calendar.MONTH)+1, checkIn.get(Calendar.DATE),
                checkOut.get(Calendar.MONTH)+1, checkOut.get(Calendar.DATE),
                numAdult+numKid));

        mPagerAdapter = new ContentsPagerAdapter(
                getChildFragmentManager(), titles);
        vpNearby.setAdapter(mPagerAdapter);

        tlNearby.setupWithViewPager(vpNearby);

        abl.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener() {
            @Override
            public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {

                collapsed = Math.abs(verticalOffset)-appBarLayout.getTotalScrollRange() == 0;

                int resource = collapsed ? R.drawable.icon_map_accent : R.drawable.ic_baseline_search;

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
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.iv_btn_nearby_search:
            case R.id.iv_btn_nearby_search_dark:
                if (!collapsed){
                    Intent intent = new Intent(getContext(), SearchActivity.class);
                    intent.putExtra("numAdult", numAdult);
                    intent.putExtra("numKid", numKid);
                    intent.putExtra("checkInMonth", checkIn.get(Calendar.MONTH)+1);
                    intent.putExtra("checkInDate", checkIn.get(Calendar.DATE));
                    intent.putExtra("checkOutMonth", checkOut.get(Calendar.MONTH)+1);
                    intent.putExtra("checkOutDate", checkOut.get(Calendar.DATE));

                    startActivity(intent);
                }
                break;

            case R.id.ll_nearby_schedule:
                showEdit();
                break;

            case R.id.v_nearby_edit_options_entire:
                hideEdit();
                break;

            case R.id.ll_nearby_person:
                Intent intent = new Intent(getContext(), NumPeopleActivity.class);
                intent.putExtra("numAdult", numAdult);
                intent.putExtra("numKid", numKid);
                startActivityForResult(intent, REQUEST_NUM_PEOPLE);
                break;

            case R.id.rl_btn_nearby_edit_options_apply:
                tvSchedule.setText(String.format("%02d.%02d~%02d.%02d, %d명",
                        checkIn.get(Calendar.MONTH)+1, checkIn.get(Calendar.DATE),
                        checkOut.get(Calendar.MONTH)+1, checkOut.get(Calendar.DATE),
                        numAdult+numKid));
                hideEdit();
                break;
        }
    }

    private void showEdit(){
        Animation fadeIn = AnimationUtils.loadAnimation(getContext(), R.anim.fade_in);
        llEditOptions.setAnimation(fadeIn);
        llEditOptions.setVisibility(View.VISIBLE);

        long diff = checkOut.getTimeInMillis() - checkIn.getTimeInMillis();
        long nights = diff / (1000*60*60*24);
        String length = checkIn.get(Calendar.MONTH)+1+"."+checkIn.get(Calendar.DATE)+" ~ "
                +checkOut.get(Calendar.MONTH)+1+"."+checkOut.get(Calendar.DATE)+", "+nights+"박";
        tvEditOptionsLength.setText(length);
        tvEditOptionsPerson.setText(getString(R.string.adult)+" "+numAdult+", "+getString(R.string.kid)+" "+numKid);
    }

    public void hideEdit(){
        Animation fadeOut = AnimationUtils.loadAnimation(getContext(), R.anim.fade_out);
        llEditOptions.setAnimation(fadeOut);
        llEditOptions.setVisibility(View.GONE);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK && data != null){
            if (requestCode == REQUEST_NUM_PEOPLE){
                numAdult = data.getIntExtra("numAdult", 2);
                numKid = data.getIntExtra("numKid", 0);

                tvEditOptionsPerson.setText(getString(R.string.adult)+" "+numAdult+", "+getString(R.string.kid)+" "+numKid);
            }
        }
    }
}