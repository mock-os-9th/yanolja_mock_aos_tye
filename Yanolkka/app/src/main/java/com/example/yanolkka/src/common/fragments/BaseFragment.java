package com.example.yanolkka.src.common.fragments;

import android.content.Intent;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.yanolkka.src.common.activities.YetActivity;

public class BaseFragment extends Fragment {

    //    Fragment 바꾸기
    public void replaceFragment(FragmentManager manager, Fragment fragment, int layoutId) {
        FragmentTransaction fragmentTransaction = manager.beginTransaction();
        fragmentTransaction.replace(layoutId, fragment).commit();
    }

    //미개발 기능/페이지
    public void goYetActivity(){
        startActivity(new Intent(getContext(), YetActivity.class));
    }
}
