package com.example.yanolkka.src.signup.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

import com.example.yanolkka.R;

public class SignUp1Fragment extends Fragment {

    public SignUp1Fragment() {
    }

    public static SignUp1Fragment newInstance() {
        return new SignUp1Fragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_sign_up_1, container, false);
    }
}