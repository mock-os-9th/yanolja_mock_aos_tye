package com.example.yanolkka.src.activities.search;

import android.os.Bundle;
import android.view.View;

import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.yanolkka.R;
import com.example.yanolkka.src.BaseActivity;
import com.example.yanolkka.src.activities.search.fragments.SearchDomesticFragment;
import com.example.yanolkka.src.activities.search.fragments.SearchLeisureFragment;
import com.example.yanolkka.src.activities.search.fragments.SearchOverseasFragment;
import com.google.android.material.tabs.TabLayout;

public class SearchActivity extends BaseActivity implements TabLayout.OnTabSelectedListener {

    private TabLayout tlSearch;
    private FragmentManager fragmentManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.add(R.id.fl_search_fragments, SearchDomesticFragment.newInstance()).commit();

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
                replaceFragment(fragmentManager, SearchDomesticFragment.newInstance(), layoutId);
                break;
            case 1:
                replaceFragment(fragmentManager, SearchOverseasFragment.newInstance(), layoutId);
                break;
            case 2:
                replaceFragment(fragmentManager, SearchLeisureFragment.newInstance(), layoutId);
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