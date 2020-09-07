package com.example.yanolkka.src.activities.search.fragments;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.yanolkka.R;
import com.example.yanolkka.src.activities.num_people.NumPeopleActivity;
import com.example.yanolkka.src.activities.search_result.SearchResultActivity;

import java.util.Calendar;

import static android.app.Activity.RESULT_OK;

public class SearchDomesticFragment extends Fragment implements View.OnClickListener {
    static final int REQUEST_NUM_PEOPLE = 1, REQUEST_LENGTH = 0;

    private EditText etSearch;
    private TextView tvLength, tvNumPeople;
    private ImageView ivBtnSearch;

    private int numAdult = 2, numKid = 0;
    private int checkInMonth, checkInDate, checkOutMonth, checkOutDate;

    public SearchDomesticFragment() {
    }

    public static SearchDomesticFragment newInstance() {
        return new SearchDomesticFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_search_domestic, container, false);

        etSearch = view.findViewById(R.id.et_search_domestic);
        tvLength = view.findViewById(R.id.tv_search_domestic_length);
        tvNumPeople = view.findViewById(R.id.tv_search_domestic_person);
        ivBtnSearch = view.findViewById(R.id.iv_search_domestic_btn_search);

        Calendar today = Calendar.getInstance();
        Calendar tomorrow = Calendar.getInstance();

        Bundle bundle = getArguments();
        if (bundle != null){
            numAdult = bundle.getInt("numAdult", 2);
            numKid = bundle.getInt("numKid", 0);
            checkInMonth = bundle.getInt("checkInMonth",today.get(Calendar.MONTH)+1);
            checkInDate = bundle.getInt("checkInDate", today.get(Calendar.DATE));
            tomorrow.add(Calendar.DATE, 1);
            checkOutMonth = bundle.getInt("checkOutMonth", tomorrow.get(Calendar.MONTH)+1);
            checkOutDate = bundle.getInt("checkOutDate", tomorrow.get(Calendar.DATE));
        }

        Calendar checkIn = Calendar.getInstance();
        checkIn.set(Calendar.MONTH, checkInMonth);
        checkIn.set(Calendar.DATE, checkInDate);
        Calendar checkOut = Calendar.getInstance();
        checkOut.set(Calendar.MONTH, checkOutMonth);
        checkOut.set(Calendar.DATE, checkOutDate);

        long diff = checkOut.getTimeInMillis() - checkIn.getTimeInMillis();
        long nights = diff / (1000*60*60*24);

        String length = checkInMonth+"."+checkInDate+" ~ "+checkOutMonth+"."+checkOutDate+", "+nights+"박";
        tvLength.setText(length);

        tvNumPeople.setText(getString(R.string.adult)+" "+numAdult+", "+getString(R.string.kid)+" "+numKid);
        tvNumPeople.setOnClickListener(this);

        tvLength.setOnClickListener(this);
        tvNumPeople.setOnClickListener(this);
        ivBtnSearch.setOnClickListener(this);

        InputMethodManager imm = (InputMethodManager)getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.showSoftInput(etSearch, 0);

        etSearch.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View view, int i, KeyEvent keyEvent) {
                if(keyEvent.getAction() == KeyEvent.ACTION_DOWN && keyEvent.getKeyCode() == KeyEvent.KEYCODE_ENTER){
                    String searchingStr = etSearch.getText().toString();
                    Intent intent = new Intent(getContext(), SearchResultActivity.class);
                    intent.putExtra("searchingStr", searchingStr);
                    startActivity(intent);
                }
                return false;
            }
        });

        return view;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.tv_search_domestic_length:
                //기간 정하기
                break;
            case R.id.tv_search_domestic_person:
                //인원수 정하기
                Intent intent = new Intent(getContext(), NumPeopleActivity.class);
                intent.putExtra("numAdult", numAdult);
                intent.putExtra("numKid", numKid);
                startActivityForResult(intent, REQUEST_NUM_PEOPLE);
                break;
            case R.id.iv_search_domestic_btn_search:
                //검색 결과 가져오기
                break;
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK && data != null){
            if (requestCode == REQUEST_NUM_PEOPLE){
                numAdult = data.getIntExtra("numAdult", 2);
                numKid = data.getIntExtra("numKid", 0);

                tvNumPeople.setText(getString(R.string.adult)+" "+numAdult+", "+getString(R.string.kid)+" "+numKid);
            }
        }
    }
}