package com.example.yanolkka.src.main.fragments.like.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

import com.example.yanolkka.R;

public class OverseasFragment extends Fragment {

    public OverseasFragment() {
    }

    public static OverseasFragment newInstance() {
        return new OverseasFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_overseas, container, false);
    }
}