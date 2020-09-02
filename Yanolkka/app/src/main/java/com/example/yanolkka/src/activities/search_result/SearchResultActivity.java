package com.example.yanolkka.src.activities.search_result;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.viewpager.widget.ViewPager;

import com.example.yanolkka.R;
import com.example.yanolkka.src.common.activities.BaseActivity;
import com.example.yanolkka.src.activities.main.MainActivity;
import com.example.yanolkka.src.common.adapters.ContentsPagerAdapter;
import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.List;

public class SearchResultActivity extends BaseActivity implements View.OnClickListener {

    private TabLayout tlSearchResult;
    private ViewPager vpSearchResult;
    private TextView tvSearchingStr;
    private AppBarLayout abl;
    private ImageView ivBtnHomeOrMap;

    private ContentsPagerAdapter mPagerAdapter;

    private boolean collapsed = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_result);

        tvSearchingStr = findViewById(R.id.tv_search_result_title);
        Intent intent = getIntent();
        String searchingStr = intent.getStringExtra("searchingStr");
        tvSearchingStr.setText(searchingStr);

        tlSearchResult = findViewById(R.id.tab_search_result);

        List<String> titles = new ArrayList<>();
        titles.add(getString(R.string.motel));
        titles.add(getString(R.string.hotelResort));
        titles.add(getString(R.string.pensionVilla));
        titles.add(getString(R.string.guesthouse));
        titles.add(getString(R.string.infiniteCouponRoom));
        titles.add(getString(R.string.franchise));
        titles.add(getString(R.string.newRemodeled));
        titles.add(getString(R.string.superDealHotel));
        titles.add(getString(R.string.partyRoom));

        vpSearchResult = findViewById(R.id.vp_search_result);
        mPagerAdapter = new ContentsPagerAdapter(getSupportFragmentManager(), titles);
        vpSearchResult.setAdapter(mPagerAdapter);

        tlSearchResult.setupWithViewPager(vpSearchResult);

        ivBtnHomeOrMap = findViewById(R.id.iv_search_result_btn_go_home);

        abl = findViewById(R.id.appbar_search_result);
        abl.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener() {
            @Override
            public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {

                collapsed = Math.abs(verticalOffset)-appBarLayout.getTotalScrollRange() == 0;

                int resource = collapsed ? R.drawable.icon_map_accent : R.drawable.icon_home;

                ivBtnHomeOrMap.setImageDrawable(getDrawable(resource));
            }
        });
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.iv_search_result_back:
                finish();
                break;
            case R.id.ll_search_result_search:
                //재검색 화면
                break;
            case R.id.iv_search_result_btn_go_home:
                //홈으로 돌아가기 or 지도 열기
                if (!collapsed){
                    Intent intent = new Intent(this, MainActivity.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(intent);
                }
                break;
            case R.id.ll_search_result_schedule:
                //스케줄 수정 화면
                break;
            case R.id.ll_search_result_person:
                //인원수 수정 화면
                break;
            case R.id.iv_search_result_check:
                //체크박스(쿠폰 할인 가능한 숙소 제공) toggle
                break;
            case R.id.iv_search_result_map:
                //지도 열기
                break;
        }
    }
}