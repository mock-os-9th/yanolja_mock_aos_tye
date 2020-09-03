package com.example.yanolkka.src.activities.main.fragments;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.yanolkka.R;
import com.example.yanolkka.src.common.fragments.BaseFragment;
import com.example.yanolkka.src.activities.main.MainActivity;

import static com.example.yanolkka.src.ApplicationClass.sSharedPreferences;

public class MyPageFragment extends BaseFragment implements View.OnClickListener {

    public MyPageFragment() {
    }

    public static MyPageFragment newInstance() {
        return new MyPageFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        boolean signedIn = !sSharedPreferences.getBoolean("isAnonymous", true);

        View view;

        if (signedIn){
            view = inflater.inflate(R.layout.fragment_my_page, container, false);

//            view.findViewById(R.id.rl_btn_temp_sign_out).setOnClickListener(this);
        } else{
            view = inflater.inflate(R.layout.fragment_yet, container, false);
        }

        return view;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
//            case R.id.rl_btn_temp_sign_out:
//                SharedPreferences.Editor editor = sSharedPreferences.edit();
//                editor.remove("userJwt");
//                editor.remove("isAnonymous");
//                editor.apply();
//                Toast.makeText(getContext(), getString(R.string.signOut)+" 성공", Toast.LENGTH_SHORT).show();
//
//                startActivity(new Intent(getContext(), MainActivity.class));
//                getActivity().finish();
//
//                break;
        }
    }
}