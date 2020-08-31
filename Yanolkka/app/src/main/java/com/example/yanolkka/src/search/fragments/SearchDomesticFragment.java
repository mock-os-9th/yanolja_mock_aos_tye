package com.example.yanolkka.src.search.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.example.yanolkka.R;

import java.util.Calendar;

public class SearchDomesticFragment extends Fragment implements View.OnClickListener {

    private EditText etSearch;
    private TextView tvLength, tvPerson;
    private ImageView ivBtnSearch;

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
        tvPerson = view.findViewById(R.id.tv_search_domestic_person);
        ivBtnSearch = view.findViewById(R.id.iv_search_domestic_btn_search);

        Calendar today = Calendar.getInstance();
        int monthToday = today.get(Calendar.MONTH)+1;
        int dateToday = today.get(Calendar.DATE);

        Calendar tomorrow = Calendar.getInstance();
        tomorrow.add(Calendar.DATE, 1);
        int monthTomorrow = tomorrow.get(Calendar.MONTH)+1;
        int dateTomorrow = tomorrow.get(Calendar.DATE);

        String length = monthToday+"."+dateToday+" ~ "+monthTomorrow+"."+dateTomorrow+", 1박";
        tvLength.setText(length);

        tvPerson.setText("성인 1, 아동 0");

        tvLength.setOnClickListener(this);
        tvPerson.setOnClickListener(this);
        ivBtnSearch.setOnClickListener(this);

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
                break;
            case R.id.iv_search_domestic_btn_search:
                //검색 결과 가져오기
                break;
        }
    }
}