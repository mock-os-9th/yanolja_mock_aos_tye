package com.example.yanolkka.src.activities.main.fragments.like.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

import com.example.yanolkka.R;
import com.example.yanolkka.src.views.GoSignInView;

import static com.example.yanolkka.src.ApplicationClass.sSharedPreferences;

public class LikeOverseasFragment extends Fragment {

    private boolean signedIn = false;

    public LikeOverseasFragment() {
    }

    public static LikeOverseasFragment newInstance() {
        return new LikeOverseasFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_like_overseas, container, false);

        GoSignInView goSignInView = view.findViewById(R.id.gsiv_like_overseas);
        signedIn = !sSharedPreferences.getBoolean("isAnonymous", false);
        if (!signedIn)
            goSignInView.setVisibility(View.VISIBLE);

        return view;
    }
}