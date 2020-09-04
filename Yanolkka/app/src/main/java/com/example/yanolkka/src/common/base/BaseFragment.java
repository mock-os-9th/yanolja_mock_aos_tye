package com.example.yanolkka.src.common.base;

import android.app.ProgressDialog;
import android.content.Intent;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.yanolkka.R;
import com.example.yanolkka.src.common.yet.YetActivity;

public class BaseFragment extends Fragment {
    public ProgressDialog mProgressDialog;

    public void showCustomToast(final String message) {
        Toast.makeText(getContext(), message, Toast.LENGTH_LONG).show();
    }

    public void showProgressDialog() {
        if (mProgressDialog == null) {
            mProgressDialog = new ProgressDialog(getContext());
            mProgressDialog.setMessage(getString(R.string.loading));
            mProgressDialog.setIndeterminate(true);
        }

        mProgressDialog.show();
    }

    public void hideProgressDialog() {
        if (mProgressDialog != null && mProgressDialog.isShowing()) {
            mProgressDialog.dismiss();
        }
    }

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
