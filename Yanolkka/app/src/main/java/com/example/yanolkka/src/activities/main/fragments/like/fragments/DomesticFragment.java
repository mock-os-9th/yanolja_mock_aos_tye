package com.example.yanolkka.src.activities.main.fragments.like.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

import com.example.yanolkka.R;
import com.example.yanolkka.src.views.GoSignInView;

public class DomesticFragment extends Fragment {

    private boolean signedIn = false;

    public DomesticFragment() {
    }

    public static DomesticFragment newInstance() {
        return new DomesticFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_domestic, container, false);

        GoSignInView goSignInView = view.findViewById(R.id.gsiv_like_domestic);
        if (!signedIn)
            goSignInView.setVisibility(View.VISIBLE);

        return view;
    }
}