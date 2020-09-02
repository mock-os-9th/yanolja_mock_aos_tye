package com.example.yanolkka.src.activities.main.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.yanolkka.R;
import com.example.yanolkka.src.common.fragments.BaseFragment;
import com.example.yanolkka.src.activities.search.SearchActivity;

public class LocationFragment extends BaseFragment implements View.OnClickListener {
    private FragmentManager fragmentManager;

    public LocationFragment() {
    }

    public static LocationFragment newInstance() {
        return new LocationFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_location, container, false);

        fragmentManager = getActivity().getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.add(R.id.fl_location_fragments, RegionFragment.newInstance()).commit();

        view.findViewById(R.id.iv_btn_location_go_search).setOnClickListener(this);
        return view;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.iv_btn_location_go_search:
                startActivity(new Intent(getContext(), SearchActivity.class));
                break;
        }
    }
}