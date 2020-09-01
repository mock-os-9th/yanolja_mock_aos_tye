package com.example.yanolkka.src.activities.search_result.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.yanolkka.R;
import com.example.yanolkka.src.adapters.ExpandedAccommodationAdapter;
import com.example.yanolkka.src.objects.Accommodation;
import com.example.yanolkka.src.objects.Motel;

import java.util.ArrayList;
import java.util.List;

public class ResultMotelFragment extends Fragment {

    private List<Accommodation> accommodations = new ArrayList<>();

    public ResultMotelFragment() {
    }

    public static ResultMotelFragment newInstance() {
        return new ResultMotelFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //Test
        accommodations.add(null);
        for (int i = 0; i < 30; i++) {
            Motel motel = new Motel(getString(R.string.sampleMotelName),
                    null, 70000, 0.5f);

            motel.setRating(4.7f);
            motel.setReviews(8512);
            motel.setDiscountRental(0.16f);
            motel.setRentalLength(4);
            motel.setOriginalRentalPrice(30000);
            motel.setHourCheckIn(20);
            motel.setMinuteCheckIn(0);

            accommodations.add(motel);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
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