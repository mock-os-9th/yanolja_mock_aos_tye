package com.example.yanolkka.src.activities.search_result.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.yanolkka.R;
import com.example.yanolkka.src.adapters.ExpandedAccommodationAdapter;
import com.example.yanolkka.src.objects.Accommodation;
import com.example.yanolkka.src.objects.Motel;

import java.util.ArrayList;
import java.util.List;

public class ResultHotelFragment extends Fragment {

    private List<Accommodation> accommodations = new ArrayList<>();

    public ResultHotelFragment() {
    }

    public static ResultHotelFragment newInstance() {
        return new ResultHotelFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //Test
        accommodations.add(null);
        for (int i = 0; i < 30; i++) {
            Accommodation hotel = new Accommodation(getString(R.string.sampleAccommodationName),
                    null, 170000, 0.65f);

            hotel.setRating(4.5f);
            hotel.setReviews(916);
            hotel.setHourCheckIn(15);
            hotel.setMinuteCheckIn(0);
            hotel.setStars(3);

            accommodations.add(hotel);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_search_result, container, false);

        RecyclerView rvResult = view.findViewById(R.id.rv_results);

        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getContext());
        rvResult.setLayoutManager(mLayoutManager);

        ExpandedAccommodationAdapter mAdapter = new ExpandedAccommodationAdapter(getContext(), accommodations);

        rvResult.setAdapter(mAdapter);

        rvResult.setOverScrollMode(View.OVER_SCROLL_NEVER);

        return view;
    }
}