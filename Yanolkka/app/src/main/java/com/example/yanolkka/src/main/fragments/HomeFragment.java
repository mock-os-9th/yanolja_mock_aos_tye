package com.example.yanolkka.src.main.fragments;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.RelativeLayout;
import android.widget.ScrollView;

import com.example.yanolkka.R;
import com.example.yanolkka.src.objects.Accommodation;
import com.example.yanolkka.src.search.SearchActivity;
import com.example.yanolkka.src.views.SimpleAccommodationAdapter;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment {

    private RelativeLayout rlBtnGoSearch;
    private RecyclerView rvHomeSimpleAccommodations;
    private SimpleAccommodationAdapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    private ScrollView sv;
    private FloatingActionButton fab;

    private List<Accommodation> accommodations = new ArrayList<>();

    public HomeFragment() {
    }

    public static HomeFragment newInstance() {
        return new HomeFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

//        임의 숙박 data 생성
        for (int i = 0; i < 15; i++) {
            Accommodation accommodation = new Accommodation(getString(R.string.sampleAccommodationName),
                    null, 170000, 0.65f);

            accommodation.setRating(4.5f);
            accommodation.setReviews(916);

            accommodations.add(accommodation);
        }
    }

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

        mLayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
        rvHomeSimpleAccommodations.setLayoutManager(mLayoutManager);

        mAdapter = new SimpleAccommodationAdapter(accommodations);

        rvHomeSimpleAccommodations.setAdapter(mAdapter);

        rvHomeSimpleAccommodations.setOverScrollMode(View.OVER_SCROLL_NEVER);

        fab = view.findViewById(R.id.fab_home);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sv.smoothScrollTo(0,0);
            }
        });
        fab.hide();

        sv = view.findViewById(R.id.sv_home);
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

        return view;
    }
}