package com.example.yanolkka.src.activities.main.fragments;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import androidx.fragment.app.Fragment;

import com.example.yanolkka.R;
import com.example.yanolkka.src.common.adapters.DetailedRegionAdapter;
import com.example.yanolkka.src.common.adapters.RegionAdapter;

public class RegionFragment extends Fragment {

    private ListView lvRegion, lvDetailedRegion;
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
        lvDetailedRegion = view.findViewById(R.id.lv_region_detailed);

        setListViews();

        return view;
    }

    private void setListViews(){

        final RegionAdapter mRegionAdapter = new RegionAdapter(getContext(), regions);
        lvRegion.setAdapter(mRegionAdapter);

        lvRegion.setItemChecked(0, true);

        lvRegion.setOverScrollMode(View.OVER_SCROLL_NEVER);

        lvRegion.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                mRegionAdapter.setSelectedPos(i);
                Log.d("TAGTAG","itemClicked : "+i);
            }
        });

        DetailedRegionAdapter mDetailedRegionAdapter = new DetailedRegionAdapter(getContext(), detailedRegions);
        lvDetailedRegion.setAdapter(mDetailedRegionAdapter);
        lvDetailedRegion.setOverScrollMode(View.OVER_SCROLL_NEVER);

    }
}