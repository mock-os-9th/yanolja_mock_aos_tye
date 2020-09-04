package com.example.yanolkka.src.activities.main.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.yanolkka.R;
import com.example.yanolkka.src.common.base.BaseFragment;
import com.google.android.material.tabs.TabLayout;

import java.util.Objects;

public class LikeFragment extends BaseFragment implements TabLayout.OnTabSelectedListener {

    private TabLayout mTabLayout;

    private FragmentManager fragmentManager;

    public LikeFragment() {
    }

    public static LikeFragment newInstance() {
        return new LikeFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_like, container, false);

        mTabLayout = view.findViewById(R.id.tab_like);
        mTabLayout.addTab(mTabLayout.newTab().setText(getString(R.string.domestic)));
        mTabLayout.addTab(mTabLayout.newTab().setText(getString(R.string.domLeisure)));
        mTabLayout.addTab(mTabLayout.newTab().setText(getString(R.string.overseas)));

        mTabLayout.addOnTabSelectedListener(this);

        fragmentManager = Objects.requireNonNull(getActivity()).getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.add(R.id.fl_like_fragments, LikeDomesticFragment.newInstance()).commit();

        return view;
    }

    @Override
    public void onTabSelected(TabLayout.Tab tab) {
        int layoutId = R.id.fl_like_fragments;
        switch (tab.getPosition()){
            case 0:
                replaceFragment(fragmentManager, LikeDomesticFragment.newInstance(), layoutId);
                break;
            case 1:
                replaceFragment(fragmentManager, LikeDomesticLeisureFragment.newInstance(), layoutId);
                break;
            case 2:
                replaceFragment(fragmentManager, LikeOverseasFragment.newInstance(), layoutId);
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