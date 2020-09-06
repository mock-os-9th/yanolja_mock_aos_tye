package com.example.yanolkka.src.activities.main.fragments;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.example.yanolkka.R;
import com.example.yanolkka.src.common.adapters.AdMdAdapter;
import com.example.yanolkka.src.common.adapters.AutoScrollViewPagerAdapter;
import com.example.yanolkka.src.common.base.BaseFragment;
import com.example.yanolkka.src.common.objects.Accommodation;
import com.example.yanolkka.src.activities.search.SearchActivity;
import com.example.yanolkka.src.common.adapters.SimpleAccommodationAdapter;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

import pl.pzienowicz.autoscrollviewpager.AutoScrollViewPager;

public class HomeFragment extends BaseFragment {

    private RelativeLayout rlBtnGoSearch;
    private LinearLayout llCollapsing;
    private RecyclerView rvHomeSimpleAccommodations, rvMd;
    private SimpleAccommodationAdapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager, mdLayoutManager;

    public ScrollView sv;
    private FloatingActionButton fab;

    private AutoScrollViewPager asvpAds;
    private ArrayList<Integer> adImageList = new ArrayList<>();
    private AutoScrollViewPagerAdapter asvpAdapter;
    private TextView tvVpIndicator;

    private List<Accommodation> accommodations = new ArrayList<>();

    private ArrayList<Integer> adMdImageList = new ArrayList<>();
    private int overallScrollX = 0;

    public HomeFragment() {
    }

    public static HomeFragment newInstance() {
        return new HomeFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

//        임의 숙박 data 생성
        if (accommodations.isEmpty()){
            for (int i = 0; i < 15; i++) {
                Accommodation accommodation = new Accommodation(getString(R.string.sampleAccommodationName),
                        null, 170000, 0.65f);

                accommodation.setRating(4.5f);
                accommodation.setReviews(922);
                accommodation.setHourCheckIn(15);
                accommodation.setMinuteCheckIn(0);

                accommodations.add(accommodation);
            }
        }

//        광고 viewPager image 생성
        if (adImageList.isEmpty()){
            for (int i = 1; i <= 5; i++) {
                int imageResId = getResources().getIdentifier("ad_vp_"+i, "drawable"
                        , getContext().getPackageName());

                adImageList.add(imageResId);
            }

            asvpAdapter = new AutoScrollViewPagerAdapter(getContext(), adImageList);
        }

        if (adMdImageList.isEmpty()){
            for (int i = 0; i < 8; i++) {
                int imageResId = getResources().getIdentifier("ad_md_"+i, "drawable"
                        , getContext().getPackageName());

                adMdImageList.add(imageResId);
            }
        }

    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        view.findViewById(R.id.rl_search).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getActivity().startActivity(new Intent(getContext(), SearchActivity.class));
            }
        });

        rvHomeSimpleAccommodations = view.findViewById(R.id.rv_home_simple_accommodations);
        fab = view.findViewById(R.id.fab_home);
        sv = view.findViewById(R.id.sv_home);
        asvpAds = view.findViewById(R.id.asvp_home_ads);
        tvVpIndicator = view.findViewById(R.id.tv_home_vp_indicator);
        llCollapsing = view.findViewById(R.id.ll_home_md_collapsing);
        rvMd = view.findViewById(R.id.rv_home_md);

        setListeners();

        return view;
    }

    private void setListeners(){
        mLayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
        rvHomeSimpleAccommodations.setLayoutManager(mLayoutManager);
        mAdapter = new SimpleAccommodationAdapter(getContext(), accommodations);
        rvHomeSimpleAccommodations.setAdapter(mAdapter);
        rvHomeSimpleAccommodations.setOverScrollMode(View.OVER_SCROLL_NEVER);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sv.smoothScrollTo(0,0);
            }
        });
        fab.hide();

        sv.setOverScrollMode(View.OVER_SCROLL_NEVER);
        sv.getViewTreeObserver().addOnScrollChangedListener(new ViewTreeObserver.OnScrollChangedListener() {
            @Override
            public void onScrollChanged() {
                int scrollY = sv.getScrollY();
                if (scrollY < 100)
                    fab.hide();
                else
                    fab.show();
            }
        });

        if (asvpAds.getAdapter() == null){
            asvpAds.setAdapter(asvpAdapter);
            asvpAds.setInterval(5000);
            asvpAds.setCycle(true);
            asvpAds.startAutoScroll();

            asvpAds.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
                @Override
                public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

                }

                @Override
                public void onPageSelected(int position) {
                    tvVpIndicator.setText(position+1+"/"+asvpAdapter.getCount());
                }

                @Override
                public void onPageScrollStateChanged(int state) {

                }
            });
        }

        mdLayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
        rvMd.setLayoutManager(mdLayoutManager);

        if (rvMd.getAdapter() == null){
            AdMdAdapter adMdAdapter = new AdMdAdapter(getContext(), adMdImageList);
            rvMd.setAdapter(adMdAdapter);
        }

        llCollapsing.setAlpha(getAlphaForCollapsingLayout(overallScrollX));

        rvMd.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                overallScrollX += dx;
                llCollapsing.setAlpha(getAlphaForCollapsingLayout(overallScrollX));
            }
        });
    }

    public void notYet(View view){
        goYetActivity();
    }

    private float getAlphaForCollapsingLayout(int scrollX) {
        float minAlpha = 0.0f,maxAlpha = 1.f;
        float alpha = maxAlpha;
        if (scrollX > 0){
            alpha = scrollX/500.f <= 1 ? alpha - (scrollX/500.f) : minAlpha;
        }
        return alpha;
    }

    @Override
    public void onStop() {
        super.onStop();
        asvpAds.stopAutoScroll();
    }
}