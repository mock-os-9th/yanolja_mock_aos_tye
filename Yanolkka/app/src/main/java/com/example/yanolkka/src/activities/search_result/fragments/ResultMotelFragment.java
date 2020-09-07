package com.example.yanolkka.src.activities.search_result.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.yanolkka.R;
import com.example.yanolkka.src.activities.main.MainActivity;
import com.example.yanolkka.src.activities.main.fragments.NearbyFragment;
import com.example.yanolkka.src.activities.search_result.SearchResultService;
import com.example.yanolkka.src.activities.search_result.interfaces.SearchResultActivityView;
import com.example.yanolkka.src.activities.search_result.models.LocationInfo;
import com.example.yanolkka.src.activities.search_result.models.NearByHotel;
import com.example.yanolkka.src.activities.search_result.models.NearByMotel;
import com.example.yanolkka.src.common.adapters.ExpandedAccommodationAdapter;
import com.example.yanolkka.src.common.base.BaseFragment;
import com.example.yanolkka.src.common.objects.Accommodation;
import com.example.yanolkka.src.common.objects.Motel;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import static com.example.yanolkka.src.ApplicationClass.BASE_LATITUDE;
import static com.example.yanolkka.src.ApplicationClass.BASE_LONGITUDE;
import static com.example.yanolkka.src.ApplicationClass.DATE_FORMAT;

public class ResultMotelFragment extends BaseFragment implements SearchResultActivityView {

    private List<Accommodation> accommodations = new ArrayList<>();
    private ExpandedAccommodationAdapter mAdapter;

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

        if (!(getActivity() instanceof MainActivity)){
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
                searchResultService.searchNearByMotels(info);
            }
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
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
    public void validateSuccess(String text) {}

    @Override
    public void validateFailure(String message) {
        hideProgressDialog();
        showCustomToast(message == null || message.isEmpty() ? getString(R.string.networkError) : message);
    }

    @Override
    public void getMotels(List<NearByMotel> motels) {
        accommodations.clear();

        accommodations.add(null);
        for (NearByMotel motelInfo : motels){
            Motel motel = new Motel(motelInfo.getAccomName()
                    ,null, motelInfo.getAllDayPrice(), 0.0f);

            motel.setDiscountRental(0.0f);
            motel.setOriginalRentalPrice(motelInfo.getPartTimePrice());
            motel.setRating((float)motelInfo.getOverallRating());
            motel.setReviews(motelInfo.getReviewCount());

            String strCheckIn = motelInfo.getAllDayTime();
            String[] arrCheckIn = strCheckIn.split(":");
            int hourCheckIn = Integer.parseInt(arrCheckIn[0]);
            int minuteCheckIn = Integer.parseInt(arrCheckIn[1]);
            motel.setHourCheckIn(hourCheckIn);
            motel.setMinuteCheckIn(minuteCheckIn);

            String strRentalTime = motelInfo.getPartTime();
            String[] arrRentalTime = strRentalTime.split(":");
            int rentalTime = Integer.parseInt(arrRentalTime[0]);
            motel.setRentalLength(rentalTime);
            motel.setHourRentalStartAt(15);
            motel.setMinuteRentalStartAt(0);

            accommodations.add(motel);
        }

        mAdapter.notifyDataSetChanged();
        hideProgressDialog();
    }

    @Override
    public void getHotels(List<NearByHotel> hotels) {
    }
}