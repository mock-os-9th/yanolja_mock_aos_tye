package com.example.yanolkka.src.activities.search_result.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.example.yanolkka.R;

public class ResultPensionFragment extends Fragment {

    public ResultPensionFragment() {
    }

    public static ResultPensionFragment newInstance() {
        return new ResultPensionFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_search_result, container, false);
//        TextView tvCount = view.findViewById(R.id.tv_result_count);
//        tvCount.setText(getString(R.string.pensionVilla));
        return view;
    }
}