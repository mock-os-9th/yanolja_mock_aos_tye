package com.example.yanolkka.src.common.views;

import android.content.Context;
import android.os.Build;
import android.view.LayoutInflater;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.RequiresApi;

import com.example.yanolkka.R;

public class KidView extends LinearLayout{
    private Context mContext;

    private int index;

    private TextView tvKid, tvAge;

    @RequiresApi(api = Build.VERSION_CODES.M)
    public KidView(Context context, int index){
        super(context);
        this.mContext = context;
        this.index = index;
        init();
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    private void init(){
        LayoutInflater.from(mContext).inflate(R.layout.view_kid, this, true);

        tvKid = findViewById(R.id.tv_kid_idx);
        tvAge = findViewById(R.id.tv_kid_age);

        tvKid.setText(mContext.getString(R.string.kid)+" "+index);
    }
}
