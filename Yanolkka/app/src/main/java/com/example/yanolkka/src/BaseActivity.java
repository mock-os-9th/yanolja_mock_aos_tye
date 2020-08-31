package com.example.yanolkka.src;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.yanolkka.R;

@SuppressLint("Registered")
public class BaseActivity extends AppCompatActivity {
    public ProgressDialog mProgressDialog;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
    }

    public void showCustomToast(final String message) {
        Toast.makeText(getApplicationContext(), message, Toast.LENGTH_LONG).show();
    }

    public void showProgressDialog() {
        if (mProgressDialog == null) {
            mProgressDialog = new ProgressDialog(this);
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

    @Override
    public void onStop() {
        super.onStop();
        hideProgressDialog();
    }
}
