package com.example.yanolkka.src.activities.main.fragments.like.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

import com.example.yanolkka.R;
import com.example.yanolkka.src.BaseFragment;
import com.example.yanolkka.src.activities.calendar.CalendarActivity;
import com.example.yanolkka.src.views.GoSignInView;

import java.util.Calendar;

public class LikeDomesticFragment extends BaseFragment implements View.OnClickListener {

    private boolean signedIn = false;

    public LikeDomesticFragment() {
    }

    public static LikeDomesticFragment newInstance() {
        return new LikeDomesticFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_like_domestic, container, false);

        GoSignInView goSignInView = view.findViewById(R.id.gsiv_like_domestic);
        if (!signedIn)
            goSignInView.setVisibility(View.VISIBLE);

        view.findViewById(R.id.ll_like_domestic_go_calendar).setOnClickListener(this);
        view.findViewById(R.id.ll_like_domestic_go_person).setOnClickListener(this);

        return view;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.ll_like_domestic_go_calendar:
                startActivity(new Intent(getContext(), CalendarActivity.class));
                break;
            case R.id.ll_like_domestic_go_person:
                goYetActivity();
                break;
        }
    }
}