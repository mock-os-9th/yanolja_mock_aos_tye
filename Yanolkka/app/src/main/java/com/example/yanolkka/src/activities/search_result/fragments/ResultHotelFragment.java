package com.example.yanolkka.src.activities.search_result.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.yanolkka.R;
import com.example.yanolkka.src.activities.main.MainActivity;
import com.example.yanolkka.src.activities.main.fragments.NearbyFragment;
import com.example.yanolkka.src.activities.search_result.SearchResultService;
import com.example.yanolkka.src.activities.search_result.interfaces.SearchResultActivityView;
import com.example.yanolkka.src.activities.search_result.models.LocationInfo;
import com.example.yanolkka.src.activities.search_result.models.MotelResult;
import com.example.yanolkka.src.activities.search_result.models.NearByHotel;
import com.example.yanolkka.src.activities.search_result.models.NearByMotel;
import com.example.yanolkka.src.common.adapters.ExpandedAccommodationAdapter;
import com.example.yanolkka.src.common.base.BaseFragment;
import com.example.yanolkka.src.common.objects.Accommodation;
import com.example.yanolkka.src.common.objects.Motel;

import java.util.ArrayList;
import java.util.List;

import static com.example.yanolkka.src.ApplicationClass.DATE_FORMAT;

public class ResultHotelFragment extends BaseFragment implements SearchResultActivityView {

    private List<Accommodation> accommodations = new ArrayList<>();
    private ExpandedAccommodationAdapter mAdapter;

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
        if (!(getActivity() instanceof MainActivity)){
//            for (int i = 0; i < 30; i++) {
//                Accommodation hotel = new Accommodation(getString(R.string.sampleAccommodationName),
//                        null, 170000, 0.65f);
//
//                hotel.setRating(4.5f);
//                hotel.setReviews(916);
//                hotel.setHourCheckIn(15);
//                hotel.setMinuteCheckIn(0);
//                hotel.setStars(3);
//
//                accommodations.add(hotel);
//            }
        }else{
            NearbyFragment parentFragment = (NearbyFragment) getParentFragment();
            if (parentFragment != null){
                int numAdult = parentFragment.numAdult, numKid = parentFragment.numKid;
                double lat = parentFragment.latitude, lon = parentFragment.longitude;

                String checkIn = DATE_FORMAT.format(parentFragment.checkIn.getTime());
                String checkOut = DATE_FORMAT.format(parentFragment.checkOut.getTime());

                LocationInfo info = new LocationInfo(lat, lon, checkIn, checkOut, numAdult, numKid);
                SearchResultService searchResultService = new SearchResultService(this);
                showProgressDialog();
                searchResultService.searchNearByHotels(info);
            }
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

        mAdapter = new ExpandedAccommodationAdapter(getContext(), accommodations);

        rvResult.setAdapter(mAdapter);

        rvResult.setOverScrollMode(View.OVER_SCROLL_NEVER);

        return view;
    }

    @Override
    public void validateSuccess(String text) {

    }

    @Override
    public void validateFailure(String message) {
        hideProgressDialog();
        showCustomToast(message == null || message.isEmpty() ? getString(R.string.networkError) : message);
    }

    @Override
    public void getMotels(List<NearByMotel> motels) {
    }

    @Override
    public void getHotels(List<NearByHotel> hotels) {

        accommodations.clear();

        accommodations.add(null);
        for (NearByHotel hotelInfo : hotels){
            Accommodation hotel = new Accommodation(hotelInfo.getAccomName()
                    ,null, hotelInfo.getAllDayPrice(), 0.0f);

            hotel.setIdx(hotelInfo.getAccomIdx());
            hotel.setRating((float)hotelInfo.getOverallRating());
            hotel.setReviews(hotelInfo.getReviewCount());

            String strCheckIn = hotelInfo.getAllDayTime();
            String[] arrCheckIn = strCheckIn.split(":");
            int hourCheckIn = Integer.parseInt(arrCheckIn[0]);
            int minuteCheckIn = Integer.parseInt(arrCheckIn[1]);
            hotel.setHourCheckIn(hourCheckIn);
            hotel.setMinuteCheckIn(minuteCheckIn);

            accommodations.add(hotel);
        }

        mAdapter.notifyDataSetChanged();
        hideProgressDialog();
    }

    @Override
    public void getRegionMotels(ArrayList<MotelResult> motels) {

    }
}