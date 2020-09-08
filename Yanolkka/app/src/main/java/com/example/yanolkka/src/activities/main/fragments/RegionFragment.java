package com.example.yanolkka.src.activities.main.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.yanolkka.R;
import com.example.yanolkka.src.activities.main.MainService;
import com.example.yanolkka.src.activities.main.interfaces.MainActivityView;
import com.example.yanolkka.src.activities.main.models.Group;
import com.example.yanolkka.src.activities.search_region_result.SearchRegionResultActivity;
import com.example.yanolkka.src.common.adapters.DetailedRegionAdapter;
import com.example.yanolkka.src.common.adapters.RegionAdapter;
import com.example.yanolkka.src.common.base.BaseFragment;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class RegionFragment extends BaseFragment implements MainActivityView {

    private ListView lvRegion, lvDetailedRegion;
    private List<String> regions = new ArrayList<>(), detailedRegions = new ArrayList<>();
    private ArrayList<Group> groupList = new ArrayList<>();

    public RegionFragment() {
    }

    public static RegionFragment newInstance() {
        return new RegionFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        regions.addAll(Arrays.asList(getResources().getStringArray(R.array.cities)));
        detailedRegions.addAll(Arrays.asList(getResources().getStringArray(R.array.regions_서울)));
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

        MainService mainService = new MainService(this);
        mainService.getGroups();

        final RegionAdapter mRegionAdapter = new RegionAdapter(getContext(), regions);
        lvRegion.setAdapter(mRegionAdapter);
        lvRegion.setItemChecked(0, true);
        lvRegion.setOverScrollMode(View.OVER_SCROLL_NEVER);

        final DetailedRegionAdapter mDetailedRegionAdapter = new DetailedRegionAdapter(getContext(), detailedRegions);
        lvDetailedRegion.setAdapter(mDetailedRegionAdapter);
        lvDetailedRegion.setOverScrollMode(View.OVER_SCROLL_NEVER);

        lvRegion.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                mRegionAdapter.setSelectedPos(i);
                String regionName = (String)mRegionAdapter.getItem(i);
                if (i < 2){
                    detailedRegions.clear();
                    detailedRegions.addAll(Arrays.asList(getResources().getStringArray(
                            getResources().getIdentifier("regions_"+regionName, "array",
                                    getContext().getPackageName()))));

                    mDetailedRegionAdapter.notifyDataSetChanged();
                }
            }
        });

        lvDetailedRegion.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String detailedRegionName = (String)mDetailedRegionAdapter.getItem(i);

                for (Group group : groupList){
                    if (group.getGroupName().equals(detailedRegionName)){
                        Intent intent = new Intent(getContext(), SearchRegionResultActivity.class);
                        intent.putExtra("groupIdx", group.getGroupIdx());
                        intent.putExtra("groupName", group.getGroupName());
                        startActivity(intent);
                        break;
                    }
                }
            }
        });
    }

    @Override
    public void validateSuccess(String text) {

    }

    @Override
    public void validateFailure(String message) {
        showCustomToast(message == null ? getString(R.string.networkError) : message);
    }

    @Override
    public void getGroups(ArrayList<Group> gList) {
        groupList = new ArrayList<>(gList);

    }
}