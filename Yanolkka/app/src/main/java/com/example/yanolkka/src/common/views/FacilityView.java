package com.example.yanolkka.src.common.views;

import android.content.Context;
import android.view.LayoutInflater;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.TextView;

import com.example.yanolkka.R;

public class FacilityView extends LinearLayout {
    private Context mContext;

    private ImageView ivFacility;
    private TextView tvFacility;

    private String facility;

    public FacilityView(Context context, String facility){
        super(context);
        this.mContext = context;
        this.facility = facility;
        init();
    }

    private void init(){
        LayoutInflater.from(mContext).inflate(R.layout.view_facility, this, true);
        ivFacility = findViewById(R.id.iv_facility);
        tvFacility = findViewById(R.id.tv_facility);

        tvFacility.setText(facility);

        switch (facility){
            case "24시간데스크":
                ivFacility.setImageResource(R.drawable.facility_24h);
                break;

            case "VOD":
                ivFacility.setImageResource(R.drawable.facility_vod);
                break;

            case "스낵바":
                ivFacility.setImageResource(R.drawable.facility_snack_bar);
                break;

            case "스파/월풀":
                ivFacility.setImageResource(R.drawable.facility_spa);
                break;

            case "와이파이":
                ivFacility.setImageResource(R.drawable.facility_wifi);
                break;

            case "주차가능":
                ivFacility.setImageResource(R.drawable.facility_park);
                break;
        }
    }
}
