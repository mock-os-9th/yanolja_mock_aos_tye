package com.example.yanolkka.src.activities.main.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import androidx.fragment.app.Fragment;

import com.example.yanolkka.R;
import com.example.yanolkka.src.common.adapters.RegionAdapter;

public class RegionFragment extends Fragment {

    private ListView lvRegion, lvRegionDetails;
    private String[] regions, detailedRegions;

    public RegionFragment() {
    }

    public static RegionFragment newInstance() {
        return new RegionFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        regions = getResources().getStringArray(R.array.cities);
        detailedRegions = getResources().getStringArray(R.array.regions_서울);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_location_region, container, false);

        lvRegion = view.findViewById(R.id.lv_region);

        RegionAdapter mRegionAdapter = new RegionAdapter(regions);
        lvRegion.setAdapter(mRegionAdapter);

        lvRegion.setOverScrollMode(View.OVER_SCROLL_NEVER);

        lvRegion.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                view.setSelected(true);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        lvRegionDetails = view.findViewById(R.id.lv_region_details);

        return view;
    }
}