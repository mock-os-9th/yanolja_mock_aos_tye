package com.example.yanolkka.src.activities.search;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.yanolkka.R;
import com.example.yanolkka.src.common.base.BaseActivity;
import com.example.yanolkka.src.common.yet.YetFragment;
import com.example.yanolkka.src.activities.search.fragments.SearchDomesticFragment;
import com.google.android.material.tabs.TabLayout;

import java.util.Calendar;

public class SearchActivity extends BaseActivity implements TabLayout.OnTabSelectedListener {

    private TabLayout tlSearch;
    private FragmentManager fragmentManager;

    private Intent fromIntent;
    private Fragment fragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragment = SearchDomesticFragment.newInstance();
        fromIntent = getIntent();

        if (fromIntent != null){
            Bundle bundle = new Bundle();
            Calendar today = Calendar.getInstance();
            Calendar tomorrow = Calendar.getInstance();
            tomorrow.add(Calendar.DATE, 1);

            bundle.putInt("numAdult",fromIntent.getIntExtra("numAdult", 2));
            bundle.putInt("numKid",fromIntent.getIntExtra("numKid", 0));
            bundle.putInt("checkInMonth",fromIntent.getIntExtra("checkInMonth", today.get(Calendar.MONTH)+1));
            bundle.putInt("checkInDate",fromIntent.getIntExtra("checkInDate", today.get(Calendar.DATE)));
            bundle.putInt("checkOutMonth",fromIntent.getIntExtra("checkOutMonth", tomorrow.get(Calendar.MONTH)+1));
            bundle.putInt("checkOutDate",fromIntent.getIntExtra("checkOutDate", tomorrow.get(Calendar.DATE)));

            fragment.setArguments(bundle);
        }

        fragmentTransaction.add(R.id.fl_search_fragments, fragment).commit();

        tlSearch = findViewById(R.id.tab_search);
        tlSearch.addTab(tlSearch.newTab().setText(getString(R.string.domestic)));
        tlSearch.addTab(tlSearch.newTab().setText(getString(R.string.overseas)));
        tlSearch.addTab(tlSearch.newTab().setText(R.string.leisureAndTicket));
        tlSearch.addOnTabSelectedListener(this);

        findViewById(R.id.iv_search_back).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    @Override
    public void onTabSelected(TabLayout.Tab tab) {
        int layoutId = R.id.fl_search_fragments;
        switch (tab.getPosition()){
            case 0:
                fragment = SearchDomesticFragment.newInstance();
                replaceFragment(fragmentManager, fragment, layoutId);
                break;
            case 1:
            case 2:
                replaceFragment(fragmentManager, YetFragment.newInstance(), layoutId);
                break;
        }
    }

    @Override
    public void onTabUnselected(TabLayout.Tab tab) {

    }

    @Override
    public void onTabReselected(TabLayout.Tab tab) {

    }
}