package com.example.yanolkka.src.activities.sign_in.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

import com.example.yanolkka.R;

public class ComfortableSignInFragment extends Fragment {

    public ComfortableSignInFragment() {
    }

    public static ComfortableSignInFragment newInstance() {
        return new ComfortableSignInFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_comfortable_sign_in, container, false);
    }
}