package com.example.yanolkka.src;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.yanolkka.R;

public class BaseFragment extends Fragment {

    //    Fragment 바꾸기
    public void replaceFragment(FragmentManager manager, Fragment fragment, int layoutId) {
        FragmentTransaction fragmentTransaction = manager.beginTransaction();
        fragmentTransaction.replace(layoutId, fragment).commit();
    }
}
